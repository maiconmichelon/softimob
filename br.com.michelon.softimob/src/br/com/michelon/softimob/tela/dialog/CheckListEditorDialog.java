package br.com.michelon.softimob.tela.dialog;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.helper.ValidatorHelper;
import br.com.michelon.softimob.aplicacao.service.CheckListService;
import br.com.michelon.softimob.modelo.CheckList;
import br.com.michelon.softimob.modelo.Item;
import br.com.michelon.softimob.modelo.ItemCheckList;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class CheckListEditorDialog extends TitleAreaDialog{
	
	private Text txtCheckList;
	private Text txtNome;
	
	private Logger log = Logger.getLogger(getClass());
	private TableViewer tvItens;
	
	private WritableValue checkListValue = WritableValue.withValueType(CheckList.class);
	private WritableValue itemValue = WritableValue.withValueType(Item.class);
	private Button btnObrigatrio;
	
	public CheckListEditorDialog(Shell parentShell, CheckList checkList) {
		super(parentShell);
		checkListValue.setValue(checkList == null ? new CheckList() : checkList);
		itemValue.setValue(new Item());
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Adicionar itens a Checklist");
		setTitle("Checklist");

		Composite area = (Composite) super.createDialogArea(parent);
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label lblCheckList = new Label(composite, SWT.NONE);
		lblCheckList.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCheckList.setText("Checklist");
		
		txtCheckList = new Text(composite, SWT.BORDER);
		txtCheckList.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite cpTabela = new Composite(composite, SWT.NONE);
		cpTabela.setLayout(new GridLayout(1, false));
		cpTabela.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		tvItens = criarTabelaIndices(cpTabela).getTableViewer();
		
		Group grpItem = new Group(composite, SWT.NONE);
		grpItem.setLayout(new GridLayout(2, false));
		grpItem.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		grpItem.setText("Item");
		
		Label lblNome = new Label(grpItem, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome");
		
		txtNome = new Text(grpItem, SWT.BORDER);
		txtNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpItem, SWT.NONE);
		
		btnObrigatrio = new Button(grpItem, SWT.CHECK);
		btnObrigatrio.setText("Obrigatório");
		
		return area;
	}
	
	private TableViewerBuilder criarTabelaIndices(Composite cpTabela) {
		TableViewerBuilder tvb = new TableViewerBuilder(cpTabela);
		
		tvb.createColumn("Nome").bindToProperty("nome").setPercentWidth(70).build();
		tvb.createColumn("Obrigatório").bindToProperty("obrigatorioFormatado").build();
		
		Menu menu = new Menu(tvb.getTable());
		tvb.getTable().setMenu(menu);
		
		MenuItem miRemover = new MenuItem(menu, SWT.BORDER);
		miRemover.setImage(ImageRepository.REMOVE_16.getImage());
		miRemover.setText("Remover");
		miRemover.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getCurrent().getItens().remove(SelectionHelper.getObject(tvItens.getSelection()));
				tvItens.refresh();
			}
		});
		
		tvb.getTableViewer().addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				itemValue.setValue(SelectionHelper.getObject(event.getSelection()));
			}
		});
		
		
		return tvb;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setLayout(new GridLayout(3, false));
		
		Button btnRegistrar = new Button(parent, SWT.PUSH);
		btnRegistrar.setText("Registrar");
		btnRegistrar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true));
		btnRegistrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				List<Item> itens = getCurrent().getItens();
				Item item = (Item) itemValue.getValue();
				
				if(!itens.contains(item)) {
					itens.add(item);
				}
				
				itemValue.setValue(new Item());
				tvItens.refresh();
				txtNome.forceFocus();
			}
		});
		btnRegistrar.setImage(ImageRepository.SAVE_16.getImage());
		
		Button btnNovo = new Button(parent, SWT.PUSH);
		btnNovo.setText("Novo");
		btnNovo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				itemValue.setValue(new Item());
			}
		});
		btnNovo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true));
		btnNovo.setImage(ImageRepository.NOVO_16.getImage());
		
		Button btnFinalizar = new Button(parent, SWT.PUSH);
		btnFinalizar.setText("Finalizar");
		btnFinalizar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true));
		btnFinalizar.setImage(ImageRepository.FINALIZAR_16.getImage());
		btnFinalizar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				salvar();
			}
		});
		
		initDataBindings();
	}

	@Override
	protected Point getInitialSize() {
		return new Point(350, 400);
	}

	private CheckList getCurrent(){
		return (CheckList) checkListValue.getValue();
	}
	
	private void salvar() {
		try {
			CheckList chk = (CheckList) checkListValue.getValue();
			if(ValidatorHelper.validarComMensagem(chk)){
				new CheckListService().salvar(chk);
				DialogHelper.openInformation("Registro salvo com sucesso.");
				close();
			}
		} catch (Exception e1) {
			new ValidationErrorDialog(ShellHelper.getActiveShell(), e1.getMessage());
			log.error("Erro ao salvar checklist", e1);
		}
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Checklist");
		
		super.configureShell(newShell);
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTxtCheckListObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtCheckList);
		IObservableValue checkListValueNomeObserveDetailValue = PojoProperties.value(CheckList.class, "nome", String.class).observeDetail(checkListValue);
		bindingContext.bindValue(observeTextTxtCheckListObserveWidget, checkListValueNomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtNomeObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtNome);
		IObservableValue itemValueNomeObserveDetailValue = PojoProperties.value(Item.class, "nome", String.class).observeDetail(itemValue);
		bindingContext.bindValue(observeTextTxtNomeObserveWidget, itemValueNomeObserveDetailValue, null, null);
		//
		IObservableValue observeSelectionBtnObrigatrioObserveWidget = WidgetProperties.selection().observe(btnObrigatrio);
		IObservableValue itemValueObrigatorioObserveDetailValue = PojoProperties.value(Item.class, "obrigatorio", Boolean.class).observeDetail(itemValue);
		bindingContext.bindValue(observeSelectionBtnObrigatrioObserveWidget, itemValueObrigatorioObserveDetailValue, null, null);
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		tvItens.setContentProvider(listContentProvider);
		IObservableList checkListValueItensObserveDetailList = PojoProperties.list(CheckList.class, "itens", ItemCheckList.class).observeDetail(checkListValue);
		tvItens.setInput(checkListValueItensObserveDetailList);
		//
		return bindingContext;
	}
}
