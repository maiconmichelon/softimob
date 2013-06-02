package br.com.michelon.softimob.tela.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.Images;
import org.eclipse.wb.swt.SWTResourceManager;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.filter.GenericFilter;
import br.com.michelon.softimob.aplicacao.filter.PropertyFilter;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.helper.WidgetHelper;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public abstract class GenericView<T> extends ViewPart{
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	
	private Composite cpBody;
	private Action actAdd;
	private Action actRefresh;

	private ColumnViewer viewer;
	private Text txtFiltro;
	private GenericFilter filter;
	
	private boolean addGroupAtivadoDesativado;
	
	public GenericView(boolean addGroupAtivadoDesativado) {
		this.addGroupAtivadoDesativado = addGroupAtivadoDesativado;
	}
	
	/** 
	 * @return List<Action> - Lista de todas as actions a ser adicionada no form.
	 */
	private List<Action> createActions() {
		List<Action> actions = Lists.newArrayList();

		List<Action> createMoreActions = createMoreActions();
		if(createMoreActions != null)
			actions.addAll(createMoreActions);
		
		{
			actRefresh = new Action("Atualizar") {
				@Override
				public void run() {
					atualizar();
				}			};
			actRefresh.setImageDescriptor(Images.REFRESH_16.getImageDescriptor());
			actions.add(actRefresh);
		}
		
		return actions;
	}
	
	protected List<Action> createMoreActions(){
		actAdd = new Action("Cadastrar") {
			@Override
			public void run() {
				cadastrar();
			}
		};
		actAdd.setImageDescriptor(Images.ADD_16.getImageDescriptor());
		
		return Arrays.asList(actAdd);
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Form frmNewForm = formToolkit.createForm(parent);
		frmNewForm.getHead().setFont(SWTResourceManager.getFont("Sans", 16, SWT.BOLD));
		frmNewForm.setImage(getImage());
		formToolkit.paintBordersFor(frmNewForm);
		frmNewForm.setText(getTitleView());
		frmNewForm.getBody().setLayout(new GridLayout(2, false));
		
		addTextFilter(frmNewForm);
		
		cpBody = formToolkit.createComposite(frmNewForm.getBody(), SWT.NONE);
		cpBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		GridLayout gridLayout = new GridLayout(2, false);
		cpBody.setLayout(gridLayout);
		formToolkit.paintBordersFor(cpBody);
		
		viewer = criarTabela(cpBody);
		
		if(addGroupAtivadoDesativado)
			addGroupAtivadoDesativado(frmNewForm);
		
		Menu menu = new Menu(viewer.getControl());
		
		setMenuItems(menu);
		
		if(menu.getItemCount() > 0)
			viewer.getControl().setMenu(menu);
		
		List<T> input = getInput();
		if(input != null && !input.isEmpty()){
			if(viewer instanceof TableViewer)
				viewer.setContentProvider(ArrayContentProvider.getInstance());
			
			viewer.setInput(input);
		}
		
		viewer.addFilter(getFilter());
		viewer.addDoubleClickListener(getDoubleClickListener());

		for(Action action : createActions()){
			frmNewForm.getToolBarManager().add(action);
		}
		
		frmNewForm.updateToolBar();
		frmNewForm.update();
	}

	private void addGroupAtivadoDesativado(Form frmNewForm) {
		Group group = new Group(frmNewForm.getBody(), SWT.NONE);
		group.setLayout(new GridLayout(3, false));
		group.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false, 2, 1));
		formToolkit.adapt(group);
		formToolkit.paintBordersFor(group);
		
		Button btnAtivados = new Button(group, SWT.RADIO);
		btnAtivados.setSelection(true);
		formToolkit.adapt(btnAtivados, true, true);
		btnAtivados.setText("Ativados");
		
		Button btnDesativados = new Button(group, SWT.RADIO);
		formToolkit.adapt(btnDesativados, true, true);
		btnDesativados.setText("Desativados");
		
		Button btnTodos = new Button(group, SWT.RADIO);
		formToolkit.adapt(btnTodos, true, true);
		btnTodos.setText("Todos");
	}

	/**
	 * Adiciona o label e o TextField de filtro, já com um listener para atualizar o filtro 
	 * @param frmNewForm Form que terá o Text e o label
	 */
	protected void addTextFilter(Form frmNewForm) {
		Label lblFiltro = new Label(frmNewForm.getBody(), SWT.NONE);
		lblFiltro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(lblFiltro, true, true);
		lblFiltro.setText("Busca");
		
		txtFiltro = new Text(frmNewForm.getBody(), SWT.BORDER);
		txtFiltro.setMessage("Filtro...");
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 524;
		txtFiltro.setLayoutData(gd_text);
		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				getFilter().setSearchText(txtFiltro.getText());
				viewer.refresh();
			}
		});
		
		formToolkit.adapt(txtFiltro, true, true);
	}

	protected ColumnViewer criarTabela(Composite composite) {
		return WidgetHelper.createTable(composite, getColumns()).getTableViewer();
	}

	protected void atualizar() {
		viewer.setInput(getInput());
		viewer.refresh();
	}
	
	/**
	 * Abre a tela de cadastro através do editorInput e EditorID implementado
	 */
	public void cadastrar(){
//		try {
//			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(getIEditorInput(getSelecionado()), getEditorId());
//		} catch (PartInitException e) {
//			e.printStackTrace();
//		}
	}

	
	/**
	 * Adiciona os MenuItems no menu da tabela, podendo ser dado um Override, adionando mais ou ainda não adicionando os itens.
	 * @param menu que será adicionado na tabela
	 */
	protected void setMenuItems(Menu menu){
		MenuItem miAlterar = new MenuItem(menu, SWT.NONE);
		miAlterar.setText("Alterar");
		miAlterar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				try {
//					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(getIEditorInput(getSelecionado()), getEditorId());
//				} catch (PartInitException e1) {
//					e1.printStackTrace();
//				}
			}
		});
		
		MenuItem miRemover = new MenuItem(menu, SWT.NONE);
		miRemover.setText("Remover");
		miRemover.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				excluir(getSelecionados());
			}
		});
	};
	
	/**
	 * Adiciona um filter para a tabela
	 * @return Filter
	 */
	protected GenericFilter getFilter(){
		if(filter == null){
			filter = new PropertyFilter(getAttributes());
		}
		
		return filter;
	}
	
	protected ArrayList<String> getAttributes(){
		return Lists.newArrayList(Iterables.transform(getColumns(), new Function<ColumnProperties, String>(){
			@Override
			public String apply(ColumnProperties arg0) {
				return arg0.getAtributo();
			}
		}));
	}
	
	/**
	 * @return Todos os objetos selecionados da tabela
	 */
	@SuppressWarnings("unchecked")
	protected List<T> getSelecionados(){
		return (List<T>) SelectionHelper.getObjects(viewer);
	}
	
	/**
	 * @return Unico objeto selecionado da tabela
	 */
	@SuppressWarnings("unchecked")
	protected T getSelecionado(){
		return (T) SelectionHelper.getObject(viewer);
	}
	
	/**
	 * Método para a exclusão dos objetos
	 * @param objetos selecionados na tabela
	 */
	protected abstract void excluir(List<T> objetos);
	
	/**
	 * @return o título da View
	 */
	protected abstract String getTitleView();
	
	/**
	 * @return a imagem que vai ao lado do título, imagem de 32 px.
	 */
	protected abstract Image getImage();

	/**
	 * A key é o label enquanto o valor é o atributo.
	 */
	public abstract List<ColumnProperties> getColumns();
	
	/**
	 * @return o EditorInput necessário para abrir a tela de cadastro / alteração.
	 */
	protected abstract GenericEditorInput<?> getIEditorInput(T t);

	/**
	 * @return o EditorID necessário para abrir a tela de cadastro / alteração.
	 */
	protected abstract String getEditorId();
	
	/**
	 * @return todos os elementos da tabela.
	 */
	protected abstract List<T> getInput();
	
	/**
	 * Por default ele pega o elemento selecionado e passa para o editor input e abre a tela de edição
	 * @return DoubleClickListener que será adicionado na tabela
	 */
	protected IDoubleClickListener getDoubleClickListener(){
		return new IDoubleClickListener(){
			@Override
			public void doubleClick(DoubleClickEvent event) {
				T t = getSelecionado();
				if(t != null)
					return ;
				
//				GenericEditorInput<?> iEditorInput = getIEditorInput(t);
//				iEditorInput.setModelo(getElementToEdit(t));
			}
		};
	};
	
	protected Object getElementToEdit(T object){
		return object;
	}
	
	@Override
	public void setFocus() {
		if(txtFiltro != null)
			txtFiltro.forceFocus();
	}
}
