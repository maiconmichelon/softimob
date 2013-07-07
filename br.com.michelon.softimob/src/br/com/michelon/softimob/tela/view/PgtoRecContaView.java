package br.com.michelon.softimob.tela.view;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.ContaPagarReceberEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.service.ContaPagarReceberService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.LancamentoContabil;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.tela.editor.ContaPagarReceberEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.NullStringValueFormatter;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewer;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewerColumn;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewerContentProvider;
import br.com.michelon.softimob.tela.widget.xViewer.XViewerColumnProperties;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.ralfebert.rcputils.properties.BaseValue;
import de.ralfebert.rcputils.properties.IValue;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class PgtoRecContaView extends GenericView<ContaPagarReceber> {

	private static final String PAGAR = "A PAGAR";
	private static final String RECEBER = "A RECEBER";
	private static final String TODAS = "TODAS";
	
	private Logger log = Logger.getLogger(getClass());
	
	private Text text;
	private Text txtConta;
	private Text txtDataBaixa;
	private DateTextField dtBaixa;

	private ContaPagarReceberService service = new ContaPagarReceberService();
	
	private List<ColumnProperties> atributos;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private GenericXViewer<MovimentacaoContabil> viewerMovimentacoes;
	
	private List<ContaPagarReceber> selecionados;
	
	private WritableValue value = WritableValue.withValueType(ModeloPgtoConta.class);
	
	public PgtoRecContaView() {
		super(false);
		
		this.atributos = Lists.newArrayList();
	}

	@Override
	protected ColumnViewer criarTabela(Composite parent) {
		Composite cpPrincipal = formToolkit.createComposite(parent, SWT.NONE);
		formToolkit.paintBordersFor(cpPrincipal);
		cpPrincipal.setLayout(new GridLayout(1, false));
		
		CTabFolder tabFolder = new CTabFolder(cpPrincipal, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmContas = new CTabItem(tabFolder, SWT.NONE);
		tbtmContas.setText("Contas");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmContas.setControl(composite_2);
		composite_2.setLayout(new GridLayout(3, false));
		
		Label lblFiltro = new Label(composite_2, SWT.NONE);
		lblFiltro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFiltro.setText("Filtro");
		
		text = new Text(composite_2, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		ComboViewer comboViewer = new ComboViewer(composite_2, SWT.READ_ONLY);
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
		
		Composite composite_tvb = new Composite(tabFolder, SWT.NONE);
		tbtmContas.setControl(composite_tvb);
		
		final TableViewerBuilder tvbContas = new TableViewerBuilder(composite_tvb, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		
		tvbContas.createColumn("Tipo").setPercentWidth(1).bindToProperty("tipoExtenso").format(new NullStringValueFormatter()).build();
		tvbContas.createColumn("Data").setPercentWidth(1).bindToProperty("dataConta").format(new DateStringValueFormatter()).build();
		tvbContas.createColumn("Data de Vencimento").setPercentWidth(1).bindToProperty("dataVencimento").format(new DateStringValueFormatter()).build();
		tvbContas.createColumn("Origem").setPercentWidth(1).bindToProperty("origem.nome").build();
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
		tvbContas.createColumn("Juros / Desconto").setPercentWidth(1).bindToValue(new BaseValue<ContaPagarReceber>() {
			@Override
			public Object get(ContaPagarReceber element) {
				return element.getValorJurDescTratado().abs();
			}
		}).format(FormatterHelper.getDefaultValueFormatterToMoney()).build();
		
		CTabItem tbtmLanamentos = new CTabItem(tabFolder, SWT.NONE);
		tbtmLanamentos.setText("Lançamentos");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmLanamentos.setControl(composite_3);
		composite_3.setLayout(new GridLayout(3, false));
		
		Label lblDataPagamento = new Label(composite_3, SWT.NONE);
		lblDataPagamento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataPagamento.setText("Data pagamento");
		
		dtBaixa = new DateTextField(composite_3);
		txtDataBaixa = dtBaixa.getControl();
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_1.widthHint = 79;
		txtDataBaixa.setLayoutData(gd_text_1);
		new Label(composite_3, SWT.NONE);
		
		Label lblConta = new Label(composite_3, SWT.NONE);
		lblConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblConta.setText("Conta");
		
		txtConta = new Text(composite_3, SWT.BORDER);
		txtConta.setEditable(false);
		txtConta.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar = new Button(composite_3, SWT.NONE);
		btnSelecionar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnSelecionar.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, btnSelecionar, value, "conta");
		
		Button btnGerarLanamentos = new Button(composite_3, SWT.NONE);
		btnGerarLanamentos.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnGerarLanamentos.setText("Gerar Lançamentos");
		btnGerarLanamentos.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(dtBaixa.getValue() == null){
					DialogHelper.openError("Informe a data da baixa.");
					return;
				}
				
				selecionados = getSelecionados();
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
		});
		new Label(composite_3, SWT.NONE);
		
		criarTabelaDeMovimentacoes(composite_3);
		
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
				try {
					if(selecionados == null || selecionados.isEmpty()){
						DialogHelper.openWarning("Para efetuar a baixa das contas selecionadas deverá ser gerado todas as movimentações correspondentes.");
						return;
					}
					
					service.baixarContas(selecionados);
				} catch (Exception e1) {
					log.error("Erro ao baixar contas.", e1);
				}
			}
		});
		
		tabFolder.setSelection(0);

		value.setValue(new ModeloPgtoConta());
		initDataBindings();
		
		return tvbContas.getTableViewer();
	}

	private void criarTabelaDeMovimentacoes(Composite composite_3) {
		Map<Class<?>, XViewerColumnProperties> m1 = Maps.newHashMap();
		m1.put(MovimentacaoContabil.class, new XViewerColumnProperties("id"));
		m1.put(LancamentoContabil.class, new XViewerColumnProperties("tipo"));
		GenericXViewerColumn c1 = new GenericXViewerColumn("Lote", 100, m1);
		
		Map<Class<?>, XViewerColumnProperties> m2 = Maps.newHashMap();
		m2.put(MovimentacaoContabil.class, new XViewerColumnProperties("data"));
		m2.put(LancamentoContabil.class, new XViewerColumnProperties("conta"));
		GenericXViewerColumn c2 = new GenericXViewerColumn("Data", 100, m2);
		
		Map<Class<?>, XViewerColumnProperties> m3 = Maps.newHashMap();
		m3.put(MovimentacaoContabil.class, new XViewerColumnProperties("valor"));
		m3.put(LancamentoContabil.class, new XViewerColumnProperties("valor"));
		GenericXViewerColumn c3 = new GenericXViewerColumn("Valor", 100, m3);
		
		Map<Class<?>, XViewerColumnProperties> m4 = Maps.newHashMap();
		m4.put(LancamentoContabil.class, new XViewerColumnProperties("historico"));
		GenericXViewerColumn c4 = new GenericXViewerColumn("Histórico", 300, m4);

		Map<Class<?>, XViewerColumnProperties> m5 = Maps.newHashMap();
		m5.put(LancamentoContabil.class, new XViewerColumnProperties("complemento"));
		GenericXViewerColumn c5 = new GenericXViewerColumn("Complemento", 300, m5);
		
		List<GenericXViewerColumn> columns = Arrays.asList(c1, c2, c3, c4, c5);
		
		viewerMovimentacoes = new GenericXViewer<MovimentacaoContabil>(composite_3, SWT.BORDER | SWT.FULL_SELECTION, columns, new GenericXViewerContentProvider() {
			
			@Override
			public Object[] getElements(Object inputElement) {
				if(inputElement instanceof List)
					return ((List<?>) inputElement).toArray();
				return null;
			}
			
			@Override
			public Object[] getChildren(Object parentElement) {
				if(parentElement instanceof MovimentacaoContabil){
					return ((MovimentacaoContabil)parentElement).getLancamentos().toArray();
				}
				return null;
			}
		});
		Tree tree = viewerMovimentacoes.getTree();
		GridData gd_table_1 = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
		gd_table_1.heightHint = 165;
		tree.setLayoutData(gd_table_1);
	}

	@Override
	public void setFocus() {
	}

	@Override
	protected void addTextFilter(Form frmNewForm) {
	}
	
	@Override
	protected void excluir(List<ContaPagarReceber> objetos) {
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
	protected GenericService<ContaPagarReceber> getService() {
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
