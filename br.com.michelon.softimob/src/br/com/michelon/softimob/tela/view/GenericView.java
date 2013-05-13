package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
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
import org.eclipse.wb.swt.Images;
import org.eclipse.wb.swt.SWTResourceManager;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.filter.GenericFilter;
import br.com.michelon.softimob.aplicacao.filter.PropertyFilter;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.helper.WidgetHelper;

public abstract class GenericView<T> extends ViewPart{
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	
	private Composite cpBody;
	private Action actAdd;
	private Action actRefresh;

	private ColumnViewer viewer;
	private Text txtFiltro;
	
	public GenericView() {
		createActions();
	}
	
	private void createActions() {
		{
			actAdd = new Action("Cadastrar") {
				@Override
				public void run() {
					cadastrar();
				}			};
			actAdd.setImageDescriptor(Images.ADD_16.getImageDescriptor());
		}
		{
			actRefresh = new Action("Atualizar") {
				@Override
				public void run() {
					atualizar();
				}			};
			actRefresh.setImageDescriptor(Images.REFRESH_16.getImageDescriptor());
		}
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Form frmNewForm = formToolkit.createForm(parent);		frmNewForm.getHead().setFont(SWTResourceManager.getFont("Sans", 16, SWT.BOLD));		frmNewForm.setImage(getImage());
		frmNewForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.paintBordersFor(frmNewForm);
		frmNewForm.setText(getName());
		frmNewForm.getBody().setLayout(new GridLayout(2, false));
		
		Label lblFiltro = new Label(frmNewForm.getBody(), SWT.NONE);
		lblFiltro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(lblFiltro, true, true);
		lblFiltro.setText("Filtro");
		
		txtFiltro = new Text(frmNewForm.getBody(), SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 269;
		txtFiltro.setLayoutData(gd_text);
		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				getFilter().setSearchText(txtFiltro.getText());
				viewer.refresh();
			}
		});
		formToolkit.adapt(txtFiltro, true, true);
		
		cpBody = formToolkit.createComposite(frmNewForm.getBody(), SWT.NONE);
		cpBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		cpBody.setLayout(new GridLayout(2, false));
		formToolkit.paintBordersFor(cpBody);
		
		viewer = criarTabela(cpBody);
		
		Menu menu = new Menu(viewer.getControl());
		
		setMenuItems(menu);
		
		if(menu.getItemCount() > 0)
			viewer.getControl().setMenu(menu);
		
		if(getInput() != null){
			if(viewer instanceof TableViewer)
				viewer.setContentProvider(ArrayContentProvider.getInstance());
			
			viewer.setInput(getInput());
		}
		
		viewer.addFilter(getFilter());
		
		frmNewForm.getToolBarManager().add(actAdd);
		frmNewForm.getToolBarManager().add(actRefresh);
		frmNewForm.updateToolBar();
		frmNewForm.update();
	}

	protected ColumnViewer criarTabela(Composite composite) {
		return WidgetHelper.createTableWithFilter(composite, getAttributes()).getTableViewer();
	}

	protected void atualizar() {
		viewer.refresh();
	}
	
	public void cadastrar(){
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(getIEditorInput(), getEditorId());
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	//TODO fazer os menuItem padrao, alterar e desativar...
	protected MenuItem[] setMenuItems(Menu menu){
		MenuItem miAlterar = new MenuItem(menu, SWT.NONE);
		miAlterar.setText("Alterar");
		miAlterar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(getIEditorInput(), getEditorId());
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		MenuItem miRemover = new MenuItem(menu, SWT.NONE);
		miRemover.setText("Remover");
		miRemover.addSelectionListener(new SelectionAdapter() {
			@Override
			@SuppressWarnings("unchecked")
			public void widgetSelected(SelectionEvent e) {
				List<T> selecionados = (List<T>) SelectionHelper.getObjects(viewer);
				excluir(selecionados);
			}
		});
		
		return new MenuItem[]{miAlterar, miRemover};
	};
	
	protected GenericFilter getFilter(){
		return new PropertyFilter(getAttributes().values().toArray());
	}
	
	protected abstract void excluir(List<T> objetos);
	
	protected abstract String getName();
	
	protected abstract Image getImage();

	/*
	 * A key é o label enquanto o valor é o atributo.
	 */
	public abstract Map<String, String> getAttributes();
	
	protected abstract GenericEditorInput<?> getIEditorInput();

	protected abstract String getEditorId();
	
	protected abstract List<T> getInput();
	
	@Override
	public void setFocus() {
		txtFiltro.forceFocus();
	}
	
}
