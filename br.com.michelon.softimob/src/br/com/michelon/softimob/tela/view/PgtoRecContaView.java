package br.com.michelon.softimob.tela.view;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.ContaPagarReceberEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.filter.PropertyFilter;
import br.com.michelon.softimob.aplicacao.helper.BoletoHelper;
import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.service.ContaPagarReceberService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.tela.editor.ContaPagarReceberEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.NullStringValueFormatter;
import br.com.michelon.softimob.tela.widget.movimentacaoXViewer.MovimentacaoGenericXViewer;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewer;

import com.google.common.collect.Lists;

import de.ralfebert.rcputils.properties.BaseValue;
import de.ralfebert.rcputils.properties.IValue;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class PgtoRecContaView extends GenericView<ContaPagarReceber> {

	public static final String ID = "br.com.michelon.softimob.tela.view.PgtoRecContaView";

	private static final String PAGAR = "A pagar";
	private static final String RECEBER = "A receber";
	private static final String TODAS = "Todas";
	
	private Logger log = Logger.getLogger(getClass());
	
	private Text txtFiltro;
	private Text txtConta;
	private Text txtDataBaixa;
	private DateTextField dtBaixa;

	private ContaPagarReceberService service = new ContaPagarReceberService();
	
	private List<ColumnProperties> atributos = Lists.newArrayList();
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private GenericXViewer<MovimentacaoContabil> viewerMovimentacoes;
	private PropertyFilter propertyFilter;
	
	private List<ContaPagarReceber> selecionados;
	
	private WritableValue value = WritableValue.withValueType(ModeloPgtoConta.class);
	private Text text_1;
	private Text text_2;
	private TableViewerBuilder tvbEstorno;
	private TableViewerBuilder tvbContaPgto;
	private DateTextField dtFinal;
	private DateTextField dtInicial;
	private CTabFolder tfPrincipal;
	private CTabFolder tfPagamento;
	
	public PgtoRecContaView() {
		super(false);
	
		propertyFilter = new PropertyFilter("origem.nome", "valor", "valorPagoParcial", "valorJurosDesconto", "dataConta", "dataVencimento", "dataPagamento", "tipoExtenso");
	}
	
	@Override
	protected ColumnViewer criarTabela(Composite parent) {
		Composite cpPrincipal = formToolkit.createComposite(parent, SWT.NONE);
		formToolkit.paintBordersFor(cpPrincipal);
		cpPrincipal.setLayout(new GridLayout(1, false));
		
		Composite composite_4 = new Composite(cpPrincipal, SWT.NONE);
		composite_4.setLayout(new GridLayout(6, false));
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		formToolkit.adapt(composite_4);
		formToolkit.paintBordersFor(composite_4);
		
		Label lblPerodo = new Label(composite_4, SWT.NONE);
		lblPerodo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(lblPerodo, true, true);
		lblPerodo.setText("Período de");
		
		dtInicial = new DateTextField(composite_4);
		text_1 = dtInicial.getControl();
		GridData gd_text_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_text_1.widthHint = 88;
		text_1.setLayoutData(gd_text_1);
		
		Label lblAt = new Label(composite_4, SWT.NONE);
		lblAt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(lblAt, true, true);
		lblAt.setText("até");
		
		dtFinal = new DateTextField(composite_4);
		text_2 = dtFinal.getControl();
		GridData gd_text_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_2.widthHint = 95;
		text_2.setLayoutData(gd_text_2);
		
		setarDatas();
		
		Button btnBuscar = new Button(composite_4, SWT.NONE);
		btnBuscar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(btnBuscar, true, true);
		btnBuscar.setText("Buscar");
		btnBuscar.setImage(ImageRepository.SEARCH_16.getImage());
		btnBuscar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				buscar();
			}
		});
		new Label(composite_4, SWT.NONE);
		
		formToolkit.createLabel(composite_4, "Filtro", SWT.NONE);
		
		txtFiltro = new Text(composite_4, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, false, false, 4, 1);
		gd_text.widthHint = 384;
		txtFiltro.setLayoutData(gd_text);
		txtFiltro.setMessage("Filtro...");
		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				propertyFilter.setSearchText(((Text)e.widget).getText());
				
				tvbContaPgto.getTableViewer().refresh();
				tvbEstorno.getTableViewer().refresh();
			}
		});
		
		ComboViewer comboViewer = new ComboViewer(composite_4, SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		comboViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return element.toString();
			}
		});
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setInput(Arrays.asList(TODAS, PAGAR, RECEBER));
		comboViewer.setSelection(new StructuredSelection(TODAS));
		
		tfPrincipal = new CTabFolder(cpPrincipal, SWT.BORDER);
		tfPrincipal.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.adapt(tfPrincipal);
		formToolkit.paintBordersFor(tfPrincipal);
		tfPrincipal.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmPagamento = new CTabItem(tfPrincipal, SWT.NONE);
		tbtmPagamento.setText("Pagamento");
		
		tfPagamento = new CTabFolder(tfPrincipal, SWT.BORDER);
		tbtmPagamento.setControl(tfPagamento);
		tfPagamento.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmContas = new CTabItem(tfPagamento, SWT.NONE);
		tbtmContas.setText("Contas");
		
		Composite composite_tvb = new Composite(tfPagamento, SWT.NONE);
		tbtmContas.setControl(composite_tvb);
		
		tvbContaPgto = criarTabelaContas(true, composite_tvb);
		
		CTabItem tbtmLanamentos = new CTabItem(tfPagamento, SWT.NONE);
		tbtmLanamentos.setText("Lançamentos");
		
		Composite composite_3 = new Composite(tfPagamento, SWT.NONE);
		tbtmLanamentos.setControl(composite_3);
		composite_3.setLayout(new GridLayout(4, false));
		
		Label lblDataPagamento = new Label(composite_3, SWT.NONE);
		lblDataPagamento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataPagamento.setText("Data pagamento");
		
		dtBaixa = new DateTextField(composite_3);
		txtDataBaixa = dtBaixa.getControl();
		GridData gd_text_11 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_11.widthHint = 79;
		txtDataBaixa.setLayoutData(gd_text_11);
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);
		
		Label lblConta = new Label(composite_3, SWT.NONE);
		lblConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblConta.setText("Conta");
		
		txtConta = new Text(composite_3, SWT.BORDER);
		txtConta.setEditable(false);
		txtConta.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar = new Button(composite_3, SWT.NONE);
		btnSelecionar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		btnSelecionar.setImage(ImageRepository.SEARCH_16.getImage());
		
		Button btnRemoverConta = new Button(composite_3, SWT.NONE);
		btnRemoverConta.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnRemoverConta.setImage(ImageRepository.REMOVE_16.getImage());
		
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, btnSelecionar, btnRemoverConta, value, "conta");
		
		Button btnGerarLanamentos = new Button(composite_3, SWT.NONE);
		btnGerarLanamentos.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnGerarLanamentos.setText("Gerar Lançamentos");
		btnGerarLanamentos.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				gerarLancamentos();
			}
		});
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);
		
		Composite composite = new Composite(composite_3, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		formToolkit.adapt(composite);
		formToolkit.paintBordersFor(composite);
		
		criarTabelaDeMovimentacoes(composite);
		
		CTabItem tbtmEstorno = new CTabItem(tfPrincipal, SWT.NONE);
		tbtmEstorno.setText("Estorno");
		
		Composite composite_1 = new Composite(tfPrincipal, SWT.NONE);
		tbtmEstorno.setControl(composite_1);
		formToolkit.paintBordersFor(composite_1);
		
		tvbEstorno = criarTabelaContas(false, composite_1);
		
		Button btnBaixarConta = new Button(cpPrincipal, SWT.NONE);
		btnBaixarConta.setImage(ImageRepository.FINALIZAR_16.getImage());
		GridData gd_btnBaixarConta = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnBaixarConta.widthHint = 120;
		gd_btnBaixarConta.heightHint = 38;
		btnBaixarConta.setLayoutData(gd_btnBaixarConta);
		btnBaixarConta.setText("Baixar / Estornar");
		btnBaixarConta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isEstorno()){
					List<ContaPagarReceber> objects = SelectionHelper.getObjects(tvbEstorno.getTableViewer());
					estornar(objects, service);
				} else {
					efetuarBaixa(selecionados, service);
					tfPagamento.setSelection(0);
				}
				
				buscar();
			}
		});

		value.setValue(new ModeloPgtoConta());
		initDataBindings();
		
		{
			tfPagamento.setSelection(0);
			tfPrincipal.setSelection(0);
		}
		
		return tvbContaPgto.getTableViewer();
	}

	protected void estornar(List<ContaPagarReceber> selecionados, ContaPagarReceberService service) {
		try{
			service.estornarContas(selecionados);
			
			DialogHelper.openInformation("Estorno da(s) conta(s) efetuada com sucesso.");
		}catch(Exception e){
			log.error("Erro ao estornar conta.", e);
		}
	}

	private boolean isEstorno() {
		return tfPrincipal.getSelectionIndex() == 1;
	}

	@SuppressWarnings("unused")
	private boolean isPagamento() {
		return tfPrincipal.getSelectionIndex() == 0;
	}
	
	private TableViewerBuilder criarTabelaContas(boolean isPagamento, Composite composite) {
		TableViewerBuilder tvbContas = new TableViewerBuilder(composite);
		
		tvbContas.createColumn("Tipo").setPercentWidth(1).bindToProperty("tipoExtenso").format(new NullStringValueFormatter()).build();
		tvbContas.createColumn("Data").setPercentWidth(1).bindToProperty("dataConta").format(new DateStringValueFormatter()).build();
		
		if(!isPagamento){
			tvbContas.createColumn("Data de Pagamento").setPercentWidth(1).bindToProperty("dataPagamento").format(new DateStringValueFormatter()).build();
		}
		
		tvbContas.createColumn("Data de Vencimento").setPercentWidth(1).bindToProperty("dataVencimento").format(new DateStringValueFormatter()).build();
		tvbContas.createColumn("Origem").setPercentWidth(1).bindToProperty("origem.nome").build();
		
		if(isPagamento){
			tvbContas.createColumn("Valor").setPercentWidth(1).bindToProperty("valor").format(FormatterHelper.getDefaultValueFormatterToMoney()).build();
			
			tvbContas.createColumn("Valor Parcial").setPercentWidth(1).bindToValue(new IValue() {
				@Override
				public void setValue(Object element, Object value) {
					ContaPagarReceber c = (ContaPagarReceber) element;
					BigDecimal valorPagoParcial = new BigDecimal(value.toString().replace(',', '.'));
					
					if(valorPagoParcial.compareTo(c.getValor()) < 1)
						c.setValorPagoParcial(valorPagoParcial);
				}
				
				@Override
				public Object getValue(Object element) {
					ContaPagarReceber c = (ContaPagarReceber) element;
					return FormatterHelper.getDefaultValueFormatterToMoney().format(c.getValorParcialOuValorCheio());
				}
			}).makeEditable().build();
			
			tvbContas.createColumn("Valor com Juros e Descontos").setPercentWidth(1).bindToValue(new IValue() {
				
				@Override
				public void setValue(Object element, Object value) {
					ContaPagarReceber c = (ContaPagarReceber) element;
					BigDecimal valorComJurosDesconto = new BigDecimal(value.toString().replace(',', '.'));
					c.setValorJurosDesconto(valorComJurosDesconto.subtract(c.getValorParcialOuValorCheio()));
				}
				
				@Override
				public Object getValue(Object element) {
					ContaPagarReceber c = (ContaPagarReceber) element;
					return FormatterHelper.getDecimalFormat().format(c.getValorParcialOuValorCheio().add(c.getValorJurDescTratado()));
				}
			}).makeEditable().build();
			
		} else {
			tvbContas.createColumn("Valor").setPercentWidth(1).bindToProperty("valorPagoParcial").format(FormatterHelper.getDefaultValueFormatterToMoney()).build();
		}
		
		tvbContas.createColumn("Juros / Desconto").setPercentWidth(1).bindToValue(new BaseValue<ContaPagarReceber>() {
			@Override
			public Object get(ContaPagarReceber element) {
				return element.getValorJurDescTratado().abs();
			}
		}).format(FormatterHelper.getDefaultValueFormatterToMoney()).build();
		
		tvbContas.getTableViewer().addFilter(propertyFilter);
		
		if(isPagamento){
			tvbContas.getTableViewer().addFilter(new ViewerFilter() {
				@Override
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					return ((ContaPagarReceber)element).getDataPagamento() == null;
				}
			});
		} else {
			tvbContas.getTableViewer().addFilter(new ViewerFilter() {
				@Override
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					return ((ContaPagarReceber)element).getDataPagamento() != null;
				}
			});
		}
		
		return tvbContas;
	}

	private void criarTabelaDeMovimentacoes(Composite composite_3) {
		viewerMovimentacoes = MovimentacaoGenericXViewer.createXviewer(composite_3);
	}

	private void setarDatas(){
		Calendar c = Calendar.getInstance();

		dtFinal.setValue(c.getTime());

		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 3);
		
		dtInicial.setValue(c.getTime());
	}
	
	@Override
	public void setFocus() {
		buscar();
	}

	@Override
	protected void addTextFilter(Form frmNewForm) {
	}
	
	@Override
	protected String getTitleView() {
		return "Contas a Pagar/Receber";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.CONTA_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(ContaPagarReceber t) {
		return new ContaPagarReceberEditorInput();
	}

	@Override
	protected String getEditorId(ContaPagarReceber t) {
		return ContaPagarReceberEditor.ID;
	}

	@Override
	protected List<ContaPagarReceber> getInput() {
		return service.findAll();
	}

	@Override
	protected void createMenuItens(Menu menu) {
		super.createMenuItens(menu);
		
		MenuItem miGerarBoleto = new MenuItem(menu, SWT.BORDER);
		
		miGerarBoleto.setText("Gerar Boleto");
		miGerarBoleto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BoletoHelper.gerarBoleto(getSelecionado());
			}
		});
		miGerarBoleto.setImage(ImageRepository.BOLETO_16.getImage());
	}
	
	@Override
	protected GenericService<ContaPagarReceber> getService(Object obj) {
		return service;
	}	
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDataBaixa);
		IObservableValue valueDataBaixaObserveDetailValue = PojoProperties.value(ModeloPgtoConta.class, "dataBaixa", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueDataBaixaObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtContaObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtConta);
		IObservableValue valueContaObserveDetailValue = PojoProperties.value(ModeloPgtoConta.class, "conta", PlanoConta.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtContaObserveWidget, valueContaObserveDetailValue, null, null);
		//
		return bindingContext;
	}
	
	private void buscar() {
		List<ContaPagarReceber> contas = service.buscarContas(dtInicial.getValue(), dtFinal.getValue());
		
		tvbContaPgto.setInput(contas);
		tvbEstorno.setInput(contas);
	}

	private void efetuarBaixa(List<ContaPagarReceber> selecionados, ContaPagarReceberService service) {
		try {
			if(selecionados == null || selecionados.isEmpty()){
				DialogHelper.openWarning("Para efetuar a baixa das contas selecionadas deverá ser gerado todas as movimentações correspondentes.");
				return;
			}
			
			service.baixarContas(selecionados);
			DialogHelper.openInformation("Baixa de conta(s) efetuada com sucesso.");
			
			selecionados.clear();
			viewerMovimentacoes.setInput(null);
			viewerMovimentacoes.refresh();
			
			
		} catch (Exception e1) {
			log.error("Erro ao baixar contas.", e1);
		}
	}

	private void gerarLancamentos() {
		if(dtBaixa.getValue() == null){
			DialogHelper.openError("Informe a data da baixa.");
			return;
		}
		
		selecionados = Lists.newArrayList(getSelecionados());
		List<MovimentacaoContabil> movs = Lists.newArrayList();
		ContaPagarReceberService service = new ContaPagarReceberService();
		
		try{
			Date dataBaixa = (Date) dtBaixa.getValue();

			for(ContaPagarReceber c : selecionados){
				MovimentacaoContabil geraMovimentacao = service.geraMovimentacao(c, (ModeloPgtoConta) value.getValue());
				
				c.setMovimentacao(geraMovimentacao);
				c.setDataPagamento(dataBaixa);
				
				movs.add(geraMovimentacao);
			}
		}catch(Exception e1){
			log.error("Erro ao gerar movimentação.", e1);
			DialogHelper.openError("Erro ao gerar movimentação.\n"+e1.getMessage());
		}
		
		viewerMovimentacoes.setInput(movs);
	}

	public class ModeloPgtoConta{
		
		private PlanoConta conta;
		
		private Date dataBaixa;
		
		public PlanoConta getConta() {
			return conta;
		}
		
		public Date getDataBaixa() {
			return dataBaixa;
		}
		
		public void setConta(PlanoConta conta) {
			this.conta = conta;
		}
		
		public void setDataBaixa(Date dataBaixa) {
			this.dataBaixa = dataBaixa;
		}
		
	}
}
