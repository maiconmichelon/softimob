package br.com.michelon.softimob.tela.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.jface.viewer.radiogroup.RadioGroupViewer;
import org.eclipse.nebula.widgets.radiogroup.RadioGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ImageRepository;
import org.eclipse.wb.swt.SWTResourceManager;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.filter.AtivadoDesativadoFilter;
import br.com.michelon.softimob.aplicacao.filter.AtivadoDesativadoFilter.AtivadoDesativado;
import br.com.michelon.softimob.aplicacao.filter.GenericFilter;
import br.com.michelon.softimob.aplicacao.filter.PropertyFilter;
import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.ReflectionHelper;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.helper.WidgetHelper;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public abstract class GenericView<T> extends ViewPart{
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	
	protected Logger log = Logger.getLogger(getClass());
	
	private Composite cpBody;
	private Action actAdd;
	private Action actRefresh;

	private ColumnViewer viewer;
	private Text txtFiltro;
	private GenericFilter filter;
	
	private boolean addGroupAtivadoDesativado;

	private AtivadoDesativadoFilter ativadoDesativadoFilter;
	private RadioGroup radioGroup;
	private RadioGroupViewer radioGroupViewer;
	
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
			actRefresh.setImageDescriptor(ImageRepository.REFRESH_16.getImageDescriptor());
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
		actAdd.setImageDescriptor(ImageRepository.ADD_16.getImageDescriptor());
		
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
		cpBody.setLayout(new FillLayout(SWT.HORIZONTAL));
		cpBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		formToolkit.paintBordersFor(cpBody);
		
		viewer = criarTabela(cpBody);
		new Label(frmNewForm.getBody(), SWT.NONE);
		
		if(addGroupAtivadoDesativado){
			ativadoDesativadoFilter = new AtivadoDesativadoFilter();
			viewer.addFilter(ativadoDesativadoFilter);
			addGroupAtivadoDesativado(frmNewForm);
		}
		
		Menu menu = new Menu(viewer.getControl());
		
		createMenuItens(menu);
		
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
		radioGroupViewer = new RadioGroupViewer(frmNewForm.getBody(), SWT.NONE);
		radioGroup = radioGroupViewer.getRadioGroup();
		radioGroup.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false, 1, 1));
		formToolkit.paintBordersFor(radioGroup);
		formToolkit.adapt(radioGroup);
		radioGroupViewer.setContentProvider(ArrayContentProvider.getInstance());
		radioGroupViewer.setInput(AtivadoDesativadoFilter.AtivadoDesativado.values());
		radioGroupViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				AtivadoDesativado selecao = SelectionHelper.getObject(radioGroupViewer);
				if(selecao != null)
					ativadoDesativadoFilter.setEstado(selecao);
				
				if(!viewer.getControl().isDisposed())
					viewer.refresh();
			}
		});
		radioGroupViewer.setSelection(new StructuredSelection(AtivadoDesativado.ATIVADOS));
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
		if(viewer.getContentProvider() == null)
			viewer.setContentProvider(ArrayContentProvider.getInstance());
		
		viewer.setInput(getInput());
		viewer.refresh();
	}
	
	/**
	 * Abre a tela de cadastro através do editorInput e EditorID implementado
	 */
	public void cadastrar(){
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(getIEditorInput(null), getEditorId(null));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Adiciona os MenuItems no menu da tabela, podendo ser dado um Override, adionando mais ou ainda não adicionando os itens.
	 * @param menu que será adicionado na tabela
	 */
	protected void createMenuItens(Menu menu){
		WidgetHelper.createMenuItemAlterar(menu, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				alterar(getSelecionado());
			}
		});
		
		MenuItem miRemover = WidgetHelper.createMenuItemRemover(menu, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				excluirDesativarAtivar(getSelecionado());
				
				atualizar();
			}
		});
		miRemover.setText(addGroupAtivadoDesativado ? "Desativar / Reativar" : "Remover");
		miRemover.setImage(addGroupAtivadoDesativado ? null : ImageRepository.REMOVE_16.getImage());
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
	protected void excluirDesativarAtivar(T objeto){
		try {
			getService(objeto).removerAtivarOuDesativar(objeto);
			
			DialogHelper.openInformation((String.format("Registro %s com sucesso.", addGroupAtivadoDesativado ? "desativado / reativado" : "removido")));
		} catch (Exception e) {
			DialogHelper.openError("Houveram erros ao remover registro.");
			log.error("Erro ao remover registro.", e);
		}
	}
	
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
	 * @param t TODO
	 * @return o EditorID necessário para abrir a tela de cadastro / alteração.
	 */
	protected abstract String getEditorId(T t);
	
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
				if(t == null)
					return ;
				
				alterar(t);
			}
		};
	};
	
	protected abstract GenericService<T> getService(Object obj);
	
	@Override
	public void setFocus() {
		if(txtFiltro != null)
			txtFiltro.forceFocus();
	}

	protected void alterar(T element) {
		try {
			refresh(element);
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(getEditorInputWithModel(element), getEditorId(element));
		} catch (PartInitException e1) {
			log.error("Erro ao abrir tela de alteração.", e1);
		} catch (Exception e) {
			log.error("Erro ao alterar elemento.", e);
		}
	}
	
	public Object refresh(Object obj) throws Exception{
		String atributoID = ReflectionHelper.getAtributoID(obj);
		Object atribute = ReflectionHelper.getAtribute(obj, atributoID);
		return getService(obj).findOne((Serializable) atribute);
	}
	
	protected GenericEditorInput<?> getEditorInputWithModel(T element){
		GenericEditorInput<?> iEditorInput = getIEditorInput(element);
		iEditorInput.setModelo(getModelOfEditorInput(element));
		return iEditorInput;
	}
	
	protected Object getModelOfEditorInput(T element) {
		return element;
	}
	
}
