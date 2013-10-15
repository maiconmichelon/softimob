package br.com.michelon.softimob.tela.editor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.WidgetHelper;
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
import br.com.michelon.softimob.modelo.ParametrosEmpresa;
import br.com.michelon.softimob.modelo.Venda;
import br.com.michelon.softimob.modelo.VendaAluguel;
import br.com.michelon.softimob.modelo.Vistoria;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.MoneyTextField;
import br.com.michelon.softimob.tela.widget.NumberTextField2;
import br.com.michelon.softimob.tela.widget.PhotoComposite;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class VendaEditor extends GenericEditor<Venda>{
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.VendaEditor";
	
	private WritableValue valueComissao = WritableValue.withValueType(Comissao.class);
	private WritableValue valueVistoria = WritableValue.withValueType(Vistoria.class);
	private VendaService service = new VendaService();
	
	private Logger log = Logger.getLogger(getClass());
	
	private Text txtContratoPrestacaoServico;
	private Text txtComprador;
	private Text txtDataVenda;
	private Text txtValorVenda;
	private Text txtVendedor;
	private Text text_5;
	private Text text_6;
	private Text text_34;
	private Text text_16;
	private Text text_19;
	private Text text_8;
	private Text txtContrato;

	private TableViewerBuilder tvbVistoria;

	private TableViewer tvComissao;
	private TableViewer tvVistoria;
	private TableViewer tvCheckListVenda;
	private TableViewer tvCheckListVistoria;

	private CTabFolder tabFolder;
	private Composite cpVistoria;
	private Composite cpComissao;
	
	private Text txtProprietario;

	private PhotoComposite photoComposite;

	private Button btnAddContratoPrestacaoServico;

	private Button btnRemoveContratoPrestacaoServico;

	private Button btnAddComprador;

	private Button btnRemoveComprador;

	private Button btnAddVendedor;

	private Button btnRemoveVendedor;

	private Button btnAddContrato;

	private Button btnRemoverContrato;

	private DateTextField dtVenda;

	private MoneyTextField mtfValorVenda;
	
	
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
		
		txtContratoPrestacaoServico = new Text(parent, SWT.BORDER);
		txtContratoPrestacaoServico.setEditable(false);
		txtContratoPrestacaoServico.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnAddContratoPrestacaoServico = new Button(parent, SWT.NONE);
		btnAddContratoPrestacaoServico.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CONTRATO_SERVICO_VENDA, btnAddContratoPrestacaoServico, value, "contrato");
		
		btnRemoveContratoPrestacaoServico = new Button(parent, SWT.NONE);
		btnRemoveContratoPrestacaoServico.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(btnRemoveContratoPrestacaoServico, value, "contrato", ContratoPrestacaoServico.class);
		
		Label lblComprado = new Label(parent, SWT.NONE);
		lblComprado.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblComprado.setText("Comprador");
		
		txtComprador = new Text(parent, SWT.BORDER);
		txtComprador.setEditable(false);
		txtComprador.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnAddComprador = new Button(parent, SWT.NONE);
		btnAddComprador.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, btnAddComprador, value, "cliente");
		
		btnRemoveComprador = new Button(parent, SWT.NONE);
		btnRemoveComprador.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(btnRemoveComprador, value, "cliente", Cliente.class);
		
		Label lblVendedor = new Label(parent, SWT.NONE);
		lblVendedor.setText("Vendedor");
		lblVendedor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtVendedor = new Text(parent, SWT.BORDER);
		txtVendedor.setEditable(false);
		txtVendedor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnAddVendedor = new Button(parent, SWT.NONE);
		btnAddVendedor.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnAddVendedor, value, "funcionario");
		
		btnRemoveVendedor = new Button(parent, SWT.NONE);
		btnRemoveVendedor.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(btnRemoveVendedor, value, "funcionario", Funcionario.class);
		
		Label lblProprietrio = new Label(parent, SWT.NONE);
		lblProprietrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblProprietrio.setText("Proprietário");
		
		txtProprietario = new Text(parent, SWT.BORDER);
		txtProprietario.setEditable(false);
		txtProprietario.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label lblModeloContrato = new Label(parent, SWT.NONE);
		lblModeloContrato.setText("Modelo de Contrato");
		lblModeloContrato.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtContrato = new Text(parent, SWT.BORDER);
		txtContrato.setEditable(false);
		txtContrato.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnAddContrato = new Button(parent, SWT.NONE);
		btnAddContrato.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.MODELO_CONTRATO, btnAddContrato, value, "modeloContrato");
		
		btnRemoverContrato = new Button(parent, SWT.NONE);
		btnRemoverContrato.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(btnRemoverContrato, value, "modeloContrato", ModeloContrato.class);
		
		Label lblData = new Label(parent, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data");
		
		dtVenda = new DateTextField(parent);
		txtDataVenda = dtVenda.getControl();
		txtDataVenda.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label lblValor_1 = new Label(parent, SWT.NONE);
		lblValor_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor_1.setText("Valor");
		
		mtfValorVenda = new MoneyTextField(parent);
		txtValorVenda = mtfValorVenda.getControl();
		txtValorVenda.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		tabFolder = new CTabFolder(parent, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 8, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmComisso = new CTabItem(tabFolder, SWT.NONE);
		tbtmComisso.setText("Comissão");
		
		cpComissao = new Composite(tabFolder, SWT.NONE);
		tbtmComisso.setControl(cpComissao);
		cpComissao.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(cpComissao, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		criarTabelaComissao(composite);
		
		Composite composite_1 = new Composite(cpComissao, SWT.NONE);
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
		
		NumberTextField2 numberTextField = new NumberTextField2(composite_1);
		Text txtPorcentagem = numberTextField.getControl();
		txtPorcentagem.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtPorcentagem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Text txtPorcentagem = (Text) e.widget;
				String texto = txtPorcentagem.getText();

				if(texto != null && !texto.isEmpty()){
					if(getCurrentObject().getValor() != null){
						Comissao c = (Comissao) valueComissao.getValue();
						Double p = Double.parseDouble(texto);
						
						BigDecimal valor = getCurrentObject().getValor().multiply(new BigDecimal(p)).divide(new BigDecimal(100));
						c.setValor(valor);
						
						updateTargets();
					}
				}
			}
		});
		
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
		
		cpVistoria = new Composite(tabFolder, SWT.NONE);
		tbtmVistoria.setControl(cpVistoria);
		cpVistoria.setLayout(new GridLayout(1, false));
		
		Composite cpCadVistoria = new Composite(cpVistoria, SWT.NONE);
		cpCadVistoria.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		criarTabelaVistoria(cpCadVistoria);
		
		CTabFolder tabFolder_1 = new CTabFolder(cpVistoria, SWT.BORDER);
		tabFolder_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
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
		
		Label lblObservaes_1 = new Label(cpAddVistoria, SWT.NONE);
		lblObservaes_1.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes_1.setText("Observações");
		
		text_19 = new Text(cpAddVistoria, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		GridData gd_text_19 = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
		gd_text_19.heightHint = 40;
		text_19.setLayoutData(gd_text_19);
		
		CTabItem tbtmFotos = new CTabItem(tabFolder_1, SWT.NONE);
		tbtmFotos.setText("Fotos");
		
		createPhotoComposite(tbtmFotos);
		
		CTabItem tbtmCheckList = new CTabItem(tabFolder_1, SWT.NONE);
		tbtmCheckList.setText("Checklist");
		
		Composite composite_2 = new Composite(tabFolder_1, SWT.NONE);
		tbtmCheckList.setControl(composite_2);
		tvCheckListVistoria = criarTabelaCheckList(composite_2);
		
		createButtonAddItem(cpVistoria, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addVistoria(valueVistoria);
			}
		});
		
		CTabItem tbtmCheckList_1 = new CTabItem(tabFolder, SWT.NONE);
		tbtmCheckList_1.setText("Checklist");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmCheckList_1.setControl(composite_3);
		tvCheckListVenda = criarTabelaCheckList(composite_3);
		
		tabFolder.setSelection(0);
		tabFolder_1.setSelection(0);
	}

	@Override
	public void saveCurrentObject(GenericService<Venda> service) {
		if(!validarComMensagem(getCurrentObject()))
			return;
		
		if(getCurrentObject().isOkCheckList() && !getCurrentObject().getFechado())
			if(DialogHelper.openConfirmation("Deseja finalizar o contrato de prestação de serviço e atualizar o cadastro do imóvel ?")){
				try {
					getCurrentObject().setFechado(true);
				} catch (Exception e) {
					log.error("Erro ao fechar contrato de venda.", e);
					DialogHelper.openErrorMultiStatus("Erro ao finalizar contrato.", e.getMessage());
					return;
				}
			}
		
		super.saveCurrentObject(service);
		
		Venda venda = getCurrentObject();
		value.setValue(null);
		value.setValue(venda);
	}
	
	private void createPhotoComposite(CTabItem tbtmFotos) {
		photoComposite = new PhotoComposite(tbtmFotos.getParent(), SWT.NONE, valueVistoria);
		tbtmFotos.setControl(photoComposite);
	}

	@Override
	protected void afterSetIObservableValue(Venda venda) {
		valueComissao.setValue(new Comissao(getCurrentObject()));
		valueVistoria.setValue(new Vistoria(getCurrentObject()));
	}
	
	private void addComissao(WritableValue valueComissao) {
		ParametrosEmpresa parametrosEmpresa = ParametrosEmpresa.getInstance();
		if(parametrosEmpresa.getTipoContaComissao() == null){
			DialogHelper.openWarning("Não foi possivel encontrar o Tipo da Conta de Comissão nos Parâmetros da Empresa.");
		}else{
			((Comissao)valueComissao.getValue()).setOrigem(parametrosEmpresa.getTipoContaComissao());
			addItens(new ComissaoService(), valueComissao, tvComissao, getCurrentObject().getComissoes());
		}
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
		
		tvbComissao.createColumn("Nome").bindToProperty("comissionado.nome").setPercentWidth(20).build();
		tvbComissao.createColumn("Valor (R$)").bindToProperty("valor").setPercentWidth(20).format(FormatterHelper.getDefaultValueFormatterToMoney()).build();
		tvbComissao.createColumn("Data de Vencimento").bindToProperty("dataVencimento").setPercentWidth(80).format(new DateStringValueFormatter()).build();
		
		tvbComissao.setInput(getCurrentObject().getComissoes());
		
		WidgetHelper.addMenusToTable(tvbComissao, new ComissaoService(), valueComissao);
		
		tvComissao = tvbComissao.getTableViewer();
	}

	private void criarTabelaVistoria(Composite composite){
		tvbVistoria = new TableViewerBuilder(composite);
		
		tvbVistoria.createColumn("Data da Vistoria").bindToProperty("data").format(new DateStringValueFormatter()).build();
		tvbVistoria.createColumn("Funcionário").bindToProperty("funcionario.nome").build();
		tvbVistoria.createColumn("Observações").setPercentWidth(60).bindToProperty("observacoes").build();
		
		tvbVistoria.setInput(getCurrentObject().getVistorias());
		
		WidgetHelper.addMenusToTable(tvbVistoria, new VistoriaService(), valueVistoria);
		
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
	protected Venda getNewValue() {
		Venda venda = new Venda();
		venda.carregarCheckList();
		return venda;
	}
	
	@Override
	protected DataBindingContext initBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContratoPrestacaoServico);
		IObservableValue valueContratonomeObserveDetailValue = PojoProperties.value(Venda.class, "contrato", ContratoPrestacaoServico.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueContratonomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtComprador);
		IObservableValue valueClienteObserveDetailValue = PojoProperties.value(Venda.class, "cliente.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueClienteObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_4ObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtVendedor);
		IObservableValue valueFuncionarionomeObserveDetailValue = PojoProperties.value(Venda.class, "funcionario.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_4ObserveWidget, valueFuncionarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextProprietariobserveWidget = WidgetProperties.text(SWT.NONE).observe(txtProprietario);
		IObservableValue valueProprietarionomeObserveDetailValue = PojoProperties.value(Venda.class, "proprietario.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextProprietariobserveWidget, valueProprietarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextModeloContratoObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContrato);
		IObservableValue valueModeloContratoObserveDetailValue = PojoProperties.value(Venda.class, "modeloContrato.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextModeloContratoObserveWidget, valueModeloContratoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtValorVenda);
		IObservableValue valueValorObserveDetailValue = PojoProperties.value(Venda.class, "valor", BigDecimal.class).observeDetail(value);
		Binding bindValue = bindingContext.bindValue(observeTextText_2ObserveWidget, valueValorObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		ControlDecorationSupport.create(bindValue, SWT.LEFT | SWT.TOP);
		//
		IObservableValue observeTextText_3ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDataVenda);
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
		bindEnableControl(bindingContext);
		bindTables(bindingContext);
		//
		return bindingContext;
	}
	
	private void bindEnableControl(DataBindingContext bindingContext){
		IObservableValue observeEnabledTfImovelObserveWidget = WidgetProperties.enabled().observe(cpVistoria);
		IObservableValue valuePropostaIdObserveDetailValue = PojoProperties.value(Venda.class, "id", Long.class).observeDetail(value);
		bindingContext.bindValue(observeEnabledTfImovelObserveWidget, valuePropostaIdObserveDetailValue, null, UVSHelper.uvsLongIsNull());
		//
		IObservableValue observeEnabledTfFotoObserveWidget = WidgetProperties.enabled().observe(photoComposite);
		IObservableValue valueFotoIdObserveDetailValue = PojoProperties.value(Vistoria.class, "id", Long.class).observeDetail(valueVistoria);
		bindingContext.bindValue(observeEnabledTfFotoObserveWidget, valueFotoIdObserveDetailValue, null, UVSHelper.uvsLongIsNull());
		//
		bindingContext.bindValue(WidgetProperties.enabled().observe(cpComissao), PojoProperties.value(Venda.class, "id", Long.class).observeDetail(value), null, UVSHelper.uvsLongIsNull());
		//
		List<Control> controls = Arrays.asList(btnAddComprador, btnAddContrato, btnAddContratoPrestacaoServico, btnAddVendedor, btnRemoveComprador, btnRemoveContratoPrestacaoServico, btnRemoverContrato, btnRemoveVendedor, dtVenda, txtValorVenda);
		for (Control control : controls) {
			bindingContext.bindValue(WidgetProperties.enabled().observe(control), PojoProperties.value(Venda.class, "id", Long.class).observeDetail(value), null, UVSHelper.uvsLongIsNotNull());
		}
	}
}
