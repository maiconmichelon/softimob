package br.com.michelon.softimob.tela.editor;

import java.math.BigDecimal;
import java.util.Date;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.NumberFormatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.service.CheckListService;
import br.com.michelon.softimob.aplicacao.service.ComissaoService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.VendaService;
import br.com.michelon.softimob.aplicacao.service.VistoriaService;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.modelo.Comissao;
import br.com.michelon.softimob.modelo.Comissionado;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.modelo.ItemCheckList;
import br.com.michelon.softimob.modelo.ModeloContrato;
import br.com.michelon.softimob.modelo.Venda;
import br.com.michelon.softimob.modelo.VendaAluguel;
import br.com.michelon.softimob.modelo.Vistoria;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.MoneyTextField;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class VendaEditor extends GenericEditor<Venda>{
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.VendaEditor";
	
	private WritableValue valueComissao = WritableValue.withValueType(Comissao.class);
	private WritableValue valueVistoria = WritableValue.withValueType(Vistoria.class);
	private VendaService service = new VendaService();
	
	private Text text;
	private Text text_1;
	private Text text_3;
	private Text text_2;
	private Text text_4;
	private Text text_5;
	private Text text_7;
	private Text text_6;
	private Text text_34;
	private Text text_16;
	private Text text_18;
	private Text text_19;
	private Text text_8;
	private Text txtContrato;

	private TableViewerBuilder tvbVistoria;

	private TableViewer tvComissao;
	private TableViewer tvVistoria;
	private TableViewer tvCheckListVenda;
	private TableViewer tvCheckListVistoria;

	private CTabFolder tabFolder;
	
	public VendaEditor() {
		super(Venda.class);
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(8, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Label lblImvel = new Label(parent, SWT.NONE);
		lblImvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblImvel.setText("Imóvel");
		
		text = new Text(parent, SWT.BORDER);
		text.setEditable(false);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar = new Button(parent, SWT.NONE);
		btnSelecionar.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CONTRATO_SERVICO_VENDA, btnSelecionar, value, "contrato");
		
		Button button_3 = new Button(parent, SWT.NONE);
		button_3.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(button_3, value, "contrato", ContratoPrestacaoServico.class);
		
		Label lblComprado = new Label(parent, SWT.NONE);
		lblComprado.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblComprado.setText("Comprador");
		
		text_1 = new Text(parent, SWT.BORDER);
		text_1.setEditable(false);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(parent, SWT.NONE);
		button.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, button, value, "cliente");
		
		Button button_4 = new Button(parent, SWT.NONE);
		button_4.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(button_4, value, "cliente", Cliente.class);
		
		Label lblVendedor = new Label(parent, SWT.NONE);
		lblVendedor.setText("Vendedor");
		lblVendedor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_4 = new Text(parent, SWT.BORDER);
		text_4.setEditable(false);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_1 = new Button(parent, SWT.NONE);
		button_1.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, button_1, value, "funcionario");
		
		Button button_5 = new Button(parent, SWT.NONE);
		button_5.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(button_5, value, "funcionario", Funcionario.class);
		
		Label lblModeloContrato = new Label(parent, SWT.NONE);
		lblModeloContrato.setText("Modelo de Contrato");
		lblModeloContrato.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtContrato = new Text(parent, SWT.BORDER);
		txtContrato.setEditable(false);
		txtContrato.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnAddContrato = new Button(parent, SWT.NONE);
		btnAddContrato.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.MODELO_CONTRATO, btnAddContrato, value, "modeloContrato");
		
		Button btnRemoverContrato = new Button(parent, SWT.NONE);
		btnRemoverContrato.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(btnRemoverContrato, value, "modeloContrato", ModeloContrato.class);
		
		Label lblValor_1 = new Label(parent, SWT.NONE);
		lblValor_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor_1.setText("Valor");
		
		MoneyTextField moneyTextField = new MoneyTextField(parent);
		text_2 = moneyTextField.getControl();
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label lblData = new Label(parent, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data");
		
		DateTextField dateTextField = new DateTextField(parent);
		text_3 = dateTextField.getControl();
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		tabFolder = new CTabFolder(parent, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 8, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmComisso = new CTabItem(tabFolder, SWT.NONE);
		tbtmComisso.setText("Comissão");
		
		Composite grpComisso = new Composite(tabFolder, SWT.NONE);
		tbtmComisso.setControl(grpComisso);
		grpComisso.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(grpComisso, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		criarTabelaComissao(composite);
		
		Composite composite_1 = new Composite(grpComisso, SWT.NONE);
		composite_1.setLayout(new GridLayout(4, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblComissionado = new Label(composite_1, SWT.NONE);
		lblComissionado.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblComissionado.setText("Comissionado");
		
		text_5 = new Text(composite_1, SWT.BORDER);
		text_5.setEditable(false);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_2 = new Button(composite_1, SWT.NONE);
		button_2.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.COMISSIONADO, button_2, valueComissao, "comissionado");
		
		Button button_6 = new Button(composite_1, SWT.NONE);
		button_6.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_6.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(button_6, valueComissao, "comissionado", Comissionado.class);
		
		Label lblDataDeVencimento = new Label(composite_1, SWT.NONE);
		lblDataDeVencimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeVencimento.setText("Data de Vencimento");
		
		DateTextField dateTextField_1 = new DateTextField(composite_1);
		text_8 = dateTextField_1.getControl();
		text_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label lblValor = new Label(composite_1, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Valor");
		
		MoneyTextField moneyTextField_1 = new MoneyTextField(composite_1);
		text_6 = moneyTextField_1.getControl();
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label lblPorcentagem = new Label(composite_1, SWT.NONE);
		lblPorcentagem.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPorcentagem.setText("Porcentagem");
		
		text_7 = new Text(composite_1, SWT.BORDER);
		new FormattedText(text_7).setFormatter(new NumberFormatter("###"));
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		createButtonAddItem(composite_1, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addComissao(valueComissao);
			}
		});
		
		CTabItem tbtmVistoria = new CTabItem(tabFolder, SWT.NONE);
		tbtmVistoria.setText("Vistorias");
		
		Composite composite_8 = new Composite(tabFolder, SWT.NONE);
		tbtmVistoria.setControl(composite_8);
		composite_8.setLayout(new GridLayout(1, false));
		
		Composite cpVistoria = new Composite(composite_8, SWT.NONE);
		cpVistoria.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		criarTabelaVistoria(cpVistoria);
		
		CTabFolder tabFolder_1 = new CTabFolder(composite_8, SWT.BORDER);
		tabFolder_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		tabFolder_1.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmVistoria_1 = new CTabItem(tabFolder_1, SWT.NONE);
		tbtmVistoria_1.setText("Vistoria");
		
		Composite cpAddVistoria = new Composite(tabFolder_1, SWT.NONE);
		tbtmVistoria_1.setControl(cpAddVistoria);
		GridLayout gl_grpVistoria = new GridLayout(4, false);
		cpAddVistoria.setLayout(gl_grpVistoria);
		
		Label lblData_2 = new Label(cpAddVistoria, SWT.NONE);
		lblData_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_2.setText("Data");
		
		DateTextField dateTextField_2 = new DateTextField(cpAddVistoria);
		text_34 = dateTextField_2.getControl();
		GridData gd_text_34 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_34.widthHint = 50;
		text_34.setLayoutData(gd_text_34);
		new Label(cpAddVistoria, SWT.NONE);
		new Label(cpAddVistoria, SWT.NONE);
		
		Label lblFuncion = new Label(cpAddVistoria, SWT.NONE);
		lblFuncion.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncion.setText("Funcionário");
		
		text_16 = new Text(cpAddVistoria, SWT.BORDER);
		text_16.setEditable(false);
		text_16.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionarFuncionarioVistoria = new Button(cpAddVistoria, SWT.NONE);
		btnSelecionarFuncionarioVistoria.setImage(ImageRepository.SEARCH_16.getImage()); 
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecionarFuncionarioVistoria, valueVistoria, "funcionario");
		
		Button button_7 = new Button(cpAddVistoria, SWT.NONE);
		button_7.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_7.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(button_7, valueVistoria, "funcionario", Funcionario.class);
		
		Label lblArquivo = new Label(cpAddVistoria, SWT.NONE);
		lblArquivo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblArquivo.setText("Arquivo");
		
		text_18 = new Text(cpAddVistoria, SWT.BORDER);
		text_18.setEditable(false);
		text_18.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_123 = new Button(cpAddVistoria, SWT.NONE);
		button_123.setImage(ImageRepository.SEARCH_16.getImage());
		
		Button button_8 = new Button(cpAddVistoria, SWT.NONE);
		button_8.setImage(ImageRepository.REMOVE_16.getImage());
		
		Label lblObservaes_1 = new Label(cpAddVistoria, SWT.NONE);
		lblObservaes_1.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes_1.setText("Observações");
		
		text_19 = new Text(cpAddVistoria, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		GridData gd_text_19 = new GridData(SWT.FILL, SWT.CENTER, true, true, 3, 1);
		gd_text_19.heightHint = 40;
		text_19.setLayoutData(gd_text_19);
		
		CTabItem tbtmCheckList = new CTabItem(tabFolder_1, SWT.NONE);
		tbtmCheckList.setText("Check List");
		
		Composite composite_2 = new Composite(tabFolder_1, SWT.NONE);
		tbtmCheckList.setControl(composite_2);
		tvCheckListVistoria = criarTabelaCheckList(composite_2);
		
		createButtonAddItem(composite_8, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addVistoria(valueVistoria);
			}
		});
		
		CTabItem tbtmCheckList_1 = new CTabItem(tabFolder, SWT.NONE);
		tbtmCheckList_1.setText("Check List");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmCheckList_1.setControl(composite_3);
		tvCheckListVenda = criarTabelaCheckList(composite_3);
		
		tabFolder.setSelection(0);
		tabFolder_1.setSelection(0);
	}

	@Override
	protected void afterSetIObservableValue(Venda venda) {
		valueComissao.setValue(new Comissao(getCurrentObject()));
		valueVistoria.setValue(new Vistoria(getCurrentObject()));
	}
	
	private void addComissao(WritableValue valueComissao) {
		addItens(new ComissaoService(), valueComissao, tvComissao, getCurrentObject().getComissoes());
	}
	
	protected void addVistoria(WritableValue valueVistoria) {
		addItens(new VistoriaService(), valueVistoria, tvVistoria, getCurrentObject().getVistorias());
	}
	
	@Override
	protected void beforeSetIObservableValue(Venda obj) {
		limparTabelas(obj);
	}
	
	private void limparTabelas(Venda obj) {
		if(tvbVistoria == null)
			return;
		
		tvbVistoria.getTable().removeAll();
		tvComissao.getTable().removeAll();
	}

	private void criarTabelaComissao(Composite composite){
		TableViewerBuilder tvbComissao = new TableViewerBuilder(composite);
		
		tvbComissao.createColumn("Nome").bindToProperty("comissionado.nome").build();
//		tvbComissao.createColumn("Valor ( % )").bindToValue(new IValue() {
//			//TODO AQUI TEM QUE RECALCULAR VALOR			
//			@Override
//			public void setValue(Object arg0, Object arg1) {
//				// TODO Auto-generated method stub
//			}
//			
//			@Override
//			public Object getValue(Object arg0) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		}).makeEditable().build();
		tvbComissao.createColumn("Valor (R$)").bindToProperty("valor").build();
		tvbComissao.createColumn("Data de Vencimento").bindToProperty("dataVencimento").format(new DateStringValueFormatter()).build();
		
		tvbComissao.setInput(getCurrentObject().getComissoes());
		
		tvComissao = tvbComissao.getTableViewer();
	}

	private void criarTabelaVistoria(Composite composite){
		tvbVistoria = new TableViewerBuilder(composite);
		
		tvbVistoria.createColumn("Data da Vistoria").bindToProperty("data").format(new DateStringValueFormatter()).build();
		tvbVistoria.createColumn("Funcionário").bindToProperty("funcionario.nome").build();
		tvbVistoria.createColumn("Observações").setPercentWidth(60).bindToProperty("observacoes").build();
		
		tvbVistoria.setInput(getCurrentObject().getVistorias());
		
		tvVistoria = tvbVistoria.getTableViewer();
	}
	
	private TableViewer criarTabelaCheckList(Composite composite){
		return new CheckListService().criarTabela(composite);
	}
	
	@Override
	public GenericService<Venda> getService() {
		return service;
	}	
	
	private DataBindingContext bindTables(DataBindingContext bindingContext){
		//
		tvCheckListVenda.setContentProvider(new ObservableListContentProvider());
		tvCheckListVenda.setInput(PojoProperties.list(VendaAluguel.class, "itensCheckList", ItemCheckList.class).observeDetail(value));
		//
		tvCheckListVistoria.setContentProvider(new ObservableListContentProvider());
		tvCheckListVistoria.setInput(PojoProperties.list(Vistoria.class, "itensCheckList", ItemCheckList.class).observeDetail(valueVistoria));
		//
		return bindingContext;
	}
	
	@Override
	protected DataBindingContext initBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.NONE).observe(text);
		IObservableValue valueContratonomeObserveDetailValue = PojoProperties.value(Venda.class, "contrato", ContratoPrestacaoServico.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueContratonomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_1);
		IObservableValue valueClienteObserveDetailValue = PojoProperties.value(Venda.class, "cliente.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueClienteObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_4ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_4);
		IObservableValue valueFuncionarionomeObserveDetailValue = PojoProperties.value(Venda.class, "funcionario.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_4ObserveWidget, valueFuncionarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextModeloContratoObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContrato);
		IObservableValue valueModeloContratoObserveDetailValue = PojoProperties.value(Venda.class, "modeloContrato.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextModeloContratoObserveWidget, valueModeloContratoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_2);
		IObservableValue valueValorObserveDetailValue = PojoProperties.value(Venda.class, "valor", BigDecimal.class).observeDetail(value);
		Binding bindValue = bindingContext.bindValue(observeTextText_2ObserveWidget, valueValorObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		ControlDecorationSupport.create(bindValue, SWT.LEFT | SWT.TOP);
		//
		IObservableValue observeTextText_3ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_3);
		IObservableValue valueDataAssinaturaContratoObserveDetailValue = PojoProperties.value(Venda.class, "dataAssinaturaContrato", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_3ObserveWidget, valueDataAssinaturaContratoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_5ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_5);
		IObservableValue valueComissaoComissionadonomeObserveDetailValue = PojoProperties.value(Comissao.class, "comissionado.nome", String.class).observeDetail(valueComissao);
		bindingContext.bindValue(observeTextText_5ObserveWidget, valueComissaoComissionadonomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_6ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_6);
		IObservableValue valueComissaoValorObserveDetailValue = PojoProperties.value(Comissao.class, "valor", BigDecimal.class).observeDetail(valueComissao);
		Binding bindValue2 = bindingContext.bindValue(observeTextText_6ObserveWidget, valueComissaoValorObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		ControlDecorationSupport.create(bindValue2, SWT.LEFT | SWT.TOP);
		//
		IObservableValue observeTextText_8ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_8);
		IObservableValue valueComissaoDataVencimentoObserveDetailValue = PojoProperties.value(Comissao.class, "dataVencimento", Date.class).observeDetail(valueComissao);
		bindingContext.bindValue(observeTextText_8ObserveWidget, valueComissaoDataVencimentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_34ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_34);
		IObservableValue valueVistoriaDataObserveDetailValue = PojoProperties.value(Vistoria.class, "data", Date.class).observeDetail(valueVistoria);
		bindingContext.bindValue(observeTextText_34ObserveWidget, valueVistoriaDataObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_16ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_16);
		IObservableValue valueVistoriaFuncionarionomeObserveDetailValue = PojoProperties.value(Vistoria.class, "funcionario.nome", String.class).observeDetail(valueVistoria);
		bindingContext.bindValue(observeTextText_16ObserveWidget, valueVistoriaFuncionarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_19ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_19);
		IObservableValue valueVistoriaObservacoesObserveDetailValue = PojoProperties.value(Vistoria.class, "observacoes", String.class).observeDetail(valueVistoria);
		bindingContext.bindValue(observeTextText_19ObserveWidget, valueVistoriaObservacoesObserveDetailValue, null, null);
		//
		IObservableValue observeEnabledTfImovelObserveWidget = WidgetProperties.enabled().observe(tabFolder);
		IObservableValue valuePropostaIdObserveDetailValue = PojoProperties.value(Venda.class, "id", Long.class).observeDetail(value);
		bindingContext.bindValue(observeEnabledTfImovelObserveWidget, valuePropostaIdObserveDetailValue, null, UVSHelper.uvsLongIsNull());
		//
		bindTables(bindingContext);
		//
		return bindingContext;
	}
}
