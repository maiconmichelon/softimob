package br.com.michelon.softimob.tela.view;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.nebula.widgets.xviewer.XViewerColumn.SortDataType;
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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.ContaPagarReceberEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.service.ContaPagarReceberService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.LancamentoContabil;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.modelo.OrigemConta;
import br.com.michelon.softimob.tela.editor.ContaPagarReceberEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.NullStringValueFormatter;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewer;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewerColumn;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewerContentProvider;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.ralfebert.rcputils.properties.PropertyEditingSupport;
import de.ralfebert.rcputils.tables.ColumnBuilder;
import de.ralfebert.rcputils.tables.TableViewerBuilder;
import de.ralfebert.rcputils.tables.format.Formatter;

import org.eclipse.ui.forms.widgets.FormToolkit;

public class PgtoRecContaView extends GenericView<ContaPagarReceber> {

	private static final String PAGAR = "A PAGAR";
	private static final String RECEBER = "A RECEBER";
	private static final String TODAS = "TODAS";
	
	private Text text;
	private Table table;
	private Text txtConta;
	private Table tbLancamentos;

	private MovimentacaoContabil movimentacao;
	
	private ContaPagarReceberService service = new ContaPagarReceberService();
	
	private List<ColumnProperties> atributos;
	private Text text_1;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private GenericXViewer<MovimentacaoContabil> viewerMovimentacoes;
	private DateTextField dtBaixa;
	
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
//		composite_tvb.setLayout(new GridLayout(3, false));
		
		final TableViewerBuilder tvbContas = new TableViewerBuilder(composite_tvb, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		
		tvbContas.createColumn("Tipo").bindToProperty("tipo").format(new NullStringValueFormatter()).build();
		tvbContas.createColumn("Data de Vencimento").bindToProperty("dataVencimento").format(Formatter.forDate(FormatterHelper.getSimpleDateFormat())).build();
		tvbContas.createColumn("Origem").bindToProperty("origem.nome").build();
		tvbContas.createColumn("Valor").bindToProperty("valor").build();
		tvbContas.createColumn("Valor Juros e Descontos").bindToProperty("valorJurosDesconto").makeEditable().build();
		tvbContas.createColumn("Valor Parcial").bindToProperty("valorPagoParcial").build();
		
//		final TableViewer tvContas = new TableViewer(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
//		table = tvContas.getTable();
//		table.setLinesVisible(true);
//		table.setHeaderVisible(true);
//		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
//		
//		TableViewerColumn tvcTipo = new TableViewerColumn(tvContas, SWT.NONE);
//		TableColumn tblclmnTipo = tvcTipo.getColumn();
//		tblclmnTipo.setWidth(100);
//		tblclmnTipo.setText("Tipo");
//		tvcTipo.setLabelProvider(new ColumnLabelProvider(){
//			@Override
//			public String getText(Object element) {
//				int tipo = ((ContaPagarReceber)element).getTipo();
//				return tipo == ContaPagarReceber.PAGAR ? PAGAR : RECEBER;
//			}
//		});
//		
//		TableViewerColumn tvcDataVencimento = new TableViewerColumn(tvContas, SWT.NONE);
//		TableColumn tblclmnDataVencimento = tvcDataVencimento.getColumn();
//		tblclmnDataVencimento.setWidth(100);
//		tblclmnDataVencimento.setText("Data Vencimento");
//		tvcDataVencimento.setLabelProvider(new ColumnLabelProvider(){
//			@Override
//			public String getText(Object element) {
//				Date dataVencimento = ((ContaPagarReceber)element).getDataVencimento();
//				if(dataVencimento != null)
//					return FormatterHelper.getSimpleDateFormat().format(dataVencimento);
//				return StringUtils.EMPTY;
//			}
//		});
//		
//		TableViewerColumn tvcOrigem = new TableViewerColumn(tvContas, SWT.NONE);
//		TableColumn tblclmnOrigem = tvcOrigem.getColumn();
//		tblclmnOrigem.setWidth(100);
//		tblclmnOrigem.setText("Origem");
//		tvcOrigem.setLabelProvider(new ColumnLabelProvider(){
//			@Override
//			public String getText(Object element) {
//				OrigemConta origem = ((ContaPagarReceber) element).getOrigem();
//				if(origem != null)
//					return origem.getNome();
//				return StringUtils.EMPTY;
//			}
//		});	
//		
//		TableViewerColumn tvcValor = new TableViewerColumn(tvContas, SWT.NONE);
//		TableColumn tblclmnValor = tvcValor.getColumn();
//		tblclmnValor.setWidth(100);
//		tblclmnValor.setText("Valor");
//		tvcValor.setLabelProvider(new ColumnLabelProvider(){
//			@Override
//			public String getText(Object element) {
//				BigDecimal valor = ((ContaPagarReceber)element).getValor();
//				if(valor != null)
//					return valor.toString();
//				return StringUtils.EMPTY;
//			}
//		});
//		
//		TableViewerColumn tvcValorJurDescontos = new TableViewerColumn(tvContas, SWT.NONE);
//		TableColumn tblclmnValorComJuros = tvcValorJurDescontos.getColumn();
//		tblclmnValorComJuros.setWidth(122);
//		tblclmnValorComJuros.setText("Valor Juros e Desconto");
//		tvcValorJurDescontos.setLabelProvider(new ColumnLabelProvider(){
//			@Override
//			public String getText(Object element) {
//				BigDecimal valorJurosDescontos = ((ContaPagarReceber)element).getValorJurosDesconto();
//				if(valorJurosDescontos != null)
//					return valorJurosDescontos.toString();
//				return StringUtils.EMPTY;
//			}
//		});
//		tvcValorJurDescontos.setEditingSupport(new PropertyEditingSupport(tvContas, "valorJurosDesconto", new TextCellEditor()));
//		
//		TableViewerColumn tableViewerColumn = new TableViewerColumn(tvContas, SWT.NONE);
//		TableColumn tblclmnJuros = tableViewerColumn.getColumn();
//		tblclmnJuros.setWidth(122);
//		tblclmnJuros.setText("Juros");
//		tableViewerColumn.setLabelProvider(new ColumnLabelProvider(){
//			@Override
//			public String getText(Object element) {
//				return "";
//			}
//		});
//		
//		TableViewerColumn tableViewerColumnParcial = new TableViewerColumn(tvContas, SWT.NONE);
//		TableColumn tblclmnJurosParcial = tableViewerColumn.getColumn();
//		tblclmnJurosParcial.setWidth(122);
//		tblclmnJurosParcial.setText("Valor Parcial");
//		tableViewerColumnParcial.setLabelProvider(new ColumnLabelProvider(){
//			public String getText(Object element) {
//				return "";
//			};
//		});
		
		CTabItem tbtmLanamentos = new CTabItem(tabFolder, SWT.NONE);
		tbtmLanamentos.setText("Lançamentos");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmLanamentos.setControl(composite_3);
		composite_3.setLayout(new GridLayout(3, false));
		
		Label lblDataPagamento = new Label(composite_3, SWT.NONE);
		lblDataPagamento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataPagamento.setText("Data pagamento");
		
		dtBaixa = new DateTextField(composite_3);
		text_1 = dtBaixa.getControl();
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_1.widthHint = 79;
		text_1.setLayoutData(gd_text_1);
		new Label(composite_3, SWT.NONE);
		
		Label lblConta = new Label(composite_3, SWT.NONE);
		lblConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblConta.setText("Conta");
		
		txtConta = new Text(composite_3, SWT.BORDER);
		txtConta.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar = new Button(composite_3, SWT.NONE);
		btnSelecionar.setText("Selecionar");
		
		Button btnGerarLanamentos = new Button(composite_3, SWT.NONE);
		btnGerarLanamentos.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnGerarLanamentos.setText("Gerar Lançamentos");
		btnGerarLanamentos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<ContaPagarReceber> selecionados = getSelecionados();
				List<MovimentacaoContabil> movs = Lists.newArrayList();
				ContaPagarReceberService service = new ContaPagarReceberService();
				
				for(ContaPagarReceber c : selecionados){
					movs.add(service.geraMovimentacao(c, (Date) dtBaixa.getValue()));
				}
				
				viewerMovimentacoes.setInput(movs);
			}
		});
		new Label(composite_3, SWT.NONE);
		
		criarTabelaDeMovimentacoes(composite_3);
		
//		TableViewer tvLancamentos = new TableViewer(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
//		tbLancamentos = tvLancamentos.getTable();
//		tbLancamentos.setHeaderVisible(true);
//		tbLancamentos.setLinesVisible(true);
//		GridData gd_table_1 = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
//		gd_table_1.heightHint = 165;
//		tbLancamentos.setLayoutData(gd_table_1);
//		
//		TableViewerColumn tvTipoLancamento = new TableViewerColumn(tvLancamentos, SWT.NONE);
//		TableColumn tblclmnTipo_1 = tvTipoLancamento.getColumn();
//		tblclmnTipo_1.setWidth(100);
//		tblclmnTipo_1.setText("Tipo");
//		tvTipoLancamento.setLabelProvider(new ColumnLabelProvider(){
//			@Override
//			public String getText(Object element) {
//				return ((LancamentoContabil)element).getTipo().toString();
//			}
//		});
//		
//		TableViewerColumn tvcContaLancamento = new TableViewerColumn(tvLancamentos, SWT.NONE);
//		TableColumn tblclmnConta = tvcContaLancamento.getColumn();
//		tblclmnConta.setWidth(100);
//		tblclmnConta.setText("Conta");
//		tvcContaLancamento.setLabelProvider(new ColumnLabelProvider(){
//			@Override
//			public String getText(Object element) {
//				return ((LancamentoContabil)element).getConta().toString();
//			}
//		});
//		
//		TableViewerColumn tvcValorConta = new TableViewerColumn(tvLancamentos, SWT.NONE);
//		TableColumn tblclmnValor_1 = tvcValorConta.getColumn();
//		tblclmnValor_1.setWidth(100);
//		tblclmnValor_1.setText("Valor");
//		tvcValorConta.setLabelProvider(new ColumnLabelProvider(){
//			@Override
//			public String getText(Object element) {
//				return ((LancamentoContabil)element).getValor().toString();
//			}
//		});
//		
//		TableViewerColumn tvcHistoricoLancamento = new TableViewerColumn(tvLancamentos, SWT.NONE);
//		TableColumn tblclmnHistrico = tvcHistoricoLancamento.getColumn();
//		tblclmnHistrico.setWidth(100);
//		tblclmnHistrico.setText("Histórico");
//		tvcHistoricoLancamento.setLabelProvider(new ColumnLabelProvider(){
//			@Override
//			public String getText(Object element) {
//				return ((LancamentoContabil)element).getHistorico();
//			}
//		});
		
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
				List<Object> contas = SelectionHelper.getObjects(tvbContas.getTableViewer());
				for (Object conta : contas) {
					((ContaPagarReceber)conta).efetuarBaixa(movimentacao);
				}
			}
		});
		
		tabFolder.setSelection(0);
		
		return tvbContas.getTableViewer();
	}

	private void criarTabelaDeMovimentacoes(Composite composite_3) {
		Map<Class<?>, String> m1 = Maps.newHashMap();
		m1.put(MovimentacaoContabil.class, "id");
		m1.put(LancamentoContabil.class, "tipo");
		GenericXViewerColumn c1 = new GenericXViewerColumn("Lote", 100, m1);
		
		Map<Class<?>, String> m2 = Maps.newHashMap();
		m2.put(MovimentacaoContabil.class, "data");
		m2.put(LancamentoContabil.class, "conta");
		GenericXViewerColumn c2 = new GenericXViewerColumn("Data", 100, m2);
		
		Map<Class<?>, String> m3 = Maps.newHashMap();
		m3.put(MovimentacaoContabil.class, "valor");
		m3.put(LancamentoContabil.class, "valor");
		GenericXViewerColumn c3 = new GenericXViewerColumn("Valor", 100, m3);
		
		Map<Class<?>, String> m4 = Maps.newHashMap();
		m4.put(LancamentoContabil.class, "historico");
		GenericXViewerColumn c4 = new GenericXViewerColumn("Histórico", 100, m4);

		Map<Class<?>, String> m5 = Maps.newHashMap();
		m5.put(LancamentoContabil.class, "complemento");
		GenericXViewerColumn c5 = new GenericXViewerColumn("Complemento", 100, m5);
		
		List<GenericXViewerColumn> columns = Arrays.asList(c1, c2, c3, c4, c5);
		
		viewerMovimentacoes = new GenericXViewer<MovimentacaoContabil>(composite_3, SWT.BORDER, columns, new GenericXViewerContentProvider() {
			
			@Override
			public Object[] getElements(Object inputElement) {
				if(inputElement instanceof List)
					return ((List) inputElement).toArray();
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
	
}
