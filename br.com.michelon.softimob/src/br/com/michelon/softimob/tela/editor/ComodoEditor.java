package br.com.michelon.softimob.tela.editor;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.wb.swt.Images;
import org.eclipse.wb.swt.ResourceManager;

import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.modelo.TipoComodo;
import br.com.michelon.softimob.modelo.TipoImovel;
import br.com.michelon.softimob.modelo.TipoImovelTipoComodo;
import br.com.michelon.softimob.persistencia.TipoComodoDAO;

public class ComodoEditor extends GenericEditor{

	public static final String ID = "br.com.michelon.softimob.tela.editor.ComodoEditor"; //$NON-NLS-1$
	
	private DataBindingContext m_bindingContext;
	private Text text;
	private WritableValue value = WritableValue.withValueType(TipoComodo.class);
	private TableViewer tvComodo;
	private TipoComodoDAO tipoComodoDAO;
	
	public ComodoEditor() {
	}
	
	@Override
	protected void salvar() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.verticalSpacing = 10;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblDescrio = new Label(composite, SWT.NONE);
		lblDescrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescrio.setText("Descrição");
		
		text = new Text(composite, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 160;
		text.setLayoutData(gd_text);
		new Label(composite, SWT.NONE);
		
		Group gpTipoImovel = new Group(composite, SWT.NONE);
		gpTipoImovel.setText("Tipos de imóvel");
		gpTipoImovel.setLayout(new GridLayout(2, false));
		GridData gd_gpTipoImovel = new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1);
		gd_gpTipoImovel.heightHint = 127;
		gpTipoImovel.setLayoutData(gd_gpTipoImovel);
		
		Composite cpTipoImovel = new Composite(gpTipoImovel, SWT.NONE);
		cpTipoImovel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		cpTipoImovel.setLayout(new GridLayout(1, false));
		
		criarTipoImovel(cpTipoImovel);	
		
		Button btnAdicionar = new Button(gpTipoImovel, SWT.NONE);
		btnAdicionar.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		btnAdicionar.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 1, 1));
		btnAdicionar.setText("Adicionar");
		btnAdicionar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ElementListSelectionDialog dialog = new ElementListSelectionDialog(ShellHelper.getActiveShell(), new LabelProvider(){
					@Override
					public String getText(Object element) {
						return ((TipoImovel)element).getDescricao();
					}
				});
				dialog.setTitle("Tipos de imóvel");
				dialog.setMessage("Selecione um tipo de imóvel");
				dialog.setElements(new TipoImovel[]{new TipoImovel("tipo1"), new TipoImovel("tipo2")});
				
				if(dialog.open() == IDialogConstants.OK_ID){
					TipoImovelTipoComodo tipoImovelTipoComodo = new TipoImovelTipoComodo();
					tipoImovelTipoComodo.setTipoImovel((TipoImovel) dialog.getFirstResult());
					tipoImovelTipoComodo.setPreSelecionado(true);
					
					((TipoComodo)value.getValue()).getTipoImovelTipoComodo().add(tipoImovelTipoComodo);
					tvComodo.refresh();
				}
			}
		});
		
		Button btnRemover = new Button(gpTipoImovel, SWT.NONE);
		btnRemover.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/delete/delete16.png"));
		btnRemover.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, true, 1, 1));
		btnRemover.setText("Remover");
		btnRemover.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TipoComodo tipo = (TipoComodo) value.getValue();
				tipo.getTipoImovelTipoComodo().remove(SelectionHelper.getObject(tvComodo));
				
				tvComodo.refresh();
			}
		});
		
		value.setValue(new TipoComodo());
		
		m_bindingContext = initDataBindings();
	}

	private void criarTipoImovel(Composite cpTipoImovel) {
		
		tvComodo = new TableViewer(cpTipoImovel, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		Table table = tvComodo.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableViewerColumn tvcNomeTipoImovel = new TableViewerColumn(tvComodo, SWT.NONE);
		TableColumn tblclmnNome = tvcNomeTipoImovel.getColumn();
		tblclmnNome.setWidth(200);
		tblclmnNome.setText("Nome");
		tvcNomeTipoImovel.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((TipoImovelTipoComodo)element).getTipoImovel().getDescricao();
			}
		});
		
		TableViewerColumn tvcPreselecionado = new TableViewerColumn(tvComodo, SWT.NONE);
		TableColumn tblclmnPrselecionado = tvcPreselecionado.getColumn();
		tblclmnPrselecionado.setWidth(100);
		tblclmnPrselecionado.setText("Pré-selecionado");
		tvcPreselecionado.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return null;
			}
			
			@Override
			public Image getImage(Object element) {
				return ((TipoImovelTipoComodo)element).getPreSelecionado() ? Images.CHECKED.getImage() : Images.UNCHECKED.getImage();
			}
		});
		tvcPreselecionado.setEditingSupport(new EditingSupport(tvComodo) {
			
			@Override
			protected void setValue(Object element, Object value) {
				((TipoImovelTipoComodo)element).setPreSelecionado((Boolean)value);
				getViewer().refresh();
			}
			
			@Override
			protected Object getValue(Object element) {
				return ((TipoImovelTipoComodo)element).getPreSelecionado();
			}
			
			@Override
			protected CellEditor getCellEditor(Object element) {
				return new CheckboxCellEditor(null, SWT.CHECK | SWT.READ_ONLY);
			}
			
			@Override
			protected boolean canEdit(Object element) {
				return true;
			}
		});
		
		tvComodo.setContentProvider(ArrayContentProvider.getInstance());
		
		TipoImovelTipoComodo tipoComodo = new TipoImovelTipoComodo();
		tipoComodo.setPreSelecionado(true);
		tipoComodo.setTipoImovel(new TipoImovel("HEUHEUHUEH"));

		tvComodo.setInput(Arrays.asList(tipoComodo));
		
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueNomeObserveDetailValue = PojoProperties.value(TipoComodo.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueNomeObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionTvComodo = ViewerProperties.input().observe(tvComodo);
		IObservableValue valueTipoImovelTipoComodoObserveDetailValue = PojoProperties.value(TipoComodo.class, "tipoImovelTipoComodo", List.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionTvComodo, valueTipoImovelTipoComodoObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
