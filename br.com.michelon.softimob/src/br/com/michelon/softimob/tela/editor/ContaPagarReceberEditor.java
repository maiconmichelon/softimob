package br.com.michelon.softimob.tela.editor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.LancamentoContabil;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.modelo.OrigemConta;

public class ContaPagarReceberEditor extends EditorPart {

	public static final String ID = "br.michelon.softimob.tela.editor.ContaPagarReceberEditor"; //$NON-NLS-1$
	private Text text;
	private Table table;
	private Text txtConta;
	private Table tbLancamentos;
	
	private static final String PAGAR = "A PAGAR";
	private static final String RECEBER = "A RECEBER";

	private MovimentacaoContabil movimentacao;
	
	public ContaPagarReceberEditor() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		
		Composite composite_1 = new Composite(container, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		Composite composite = new Composite(composite_1, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		CTabFolder tabFolder = new CTabFolder(composite, SWT.BORDER);
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
		comboViewer.setInput(Arrays.asList(PAGAR, RECEBER));
		
		final TableViewer tvContas = new TableViewer(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table = tvContas.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		
		TableViewerColumn tvcTipo = new TableViewerColumn(tvContas, SWT.NONE);
		TableColumn tblclmnTipo = tvcTipo.getColumn();
		tblclmnTipo.setWidth(100);
		tblclmnTipo.setText("Tipo");
		tvcTipo.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				Character tipo = ((ContaPagarReceber)element).getTipo();
				return tipo == ContaPagarReceber.PAGAR ? PAGAR : RECEBER;
			}
		});
		
		TableViewerColumn tvcDataVencimento = new TableViewerColumn(tvContas, SWT.NONE);
		TableColumn tblclmnDataVencimento = tvcDataVencimento.getColumn();
		tblclmnDataVencimento.setWidth(100);
		tblclmnDataVencimento.setText("Data Vencimento");
		tvcDataVencimento.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				Date dataVencimento = ((ContaPagarReceber)element).getDataVencimento();
				if(dataVencimento != null)
					return FormatterHelper.getSimpleDateFormat().format(dataVencimento);
				return StringUtils.EMPTY;
			}
		});
		
		TableViewerColumn tvcOrigem = new TableViewerColumn(tvContas, SWT.NONE);
		TableColumn tblclmnOrigem = tvcOrigem.getColumn();
		tblclmnOrigem.setWidth(100);
		tblclmnOrigem.setText("Origem");
		tvcOrigem.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				OrigemConta origem = ((ContaPagarReceber) element).getOrigem();
				if(origem != null)
					return origem.getDescricao();
				return StringUtils.EMPTY;
			}
		});	
		
		TableViewerColumn tvcValor = new TableViewerColumn(tvContas, SWT.NONE);
		TableColumn tblclmnValor = tvcValor.getColumn();
		tblclmnValor.setWidth(100);
		tblclmnValor.setText("Valor");
		tvcValor.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				BigDecimal valor = ((ContaPagarReceber)element).getValor();
				if(valor != null)
					return valor.toString();
				return StringUtils.EMPTY;
			}
		});
		
		TableViewerColumn tvcValorPagarParcial = new TableViewerColumn(tvContas, SWT.NONE);
		TableColumn tblclmnValorPagar = tvcValorPagarParcial.getColumn();
		tblclmnValorPagar.setWidth(100);
		tblclmnValorPagar.setText("Valor Pagar/Receber");
		tvcValorPagarParcial.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				BigDecimal valorParcial = ((ContaPagarReceber)element).getValorPagoParcial();
				if(valorParcial != null)
					return valorParcial.toString();
				return StringUtils.EMPTY;
			}
		});
		
		TableViewerColumn tvcValorJurDescontos = new TableViewerColumn(tvContas, SWT.NONE);
		TableColumn tblclmnValorComJuros = tvcValorJurDescontos.getColumn();
		tblclmnValorComJuros.setWidth(100);
		tblclmnValorComJuros.setText("Valor Juros e Desconto");
		tvcValorJurDescontos.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				BigDecimal valorJurosDescontos = ((ContaPagarReceber)element).getValorJurosDesconto();
				if(valorJurosDescontos != null)
					return valorJurosDescontos.toString();
				return StringUtils.EMPTY;
			}
		});
		
		CTabItem tbtmLanamentos = new CTabItem(tabFolder, SWT.NONE);
		tbtmLanamentos.setText("Lançamentos");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmLanamentos.setControl(composite_3);
		composite_3.setLayout(new GridLayout(3, false));
		
		Label lblDataPagamento = new Label(composite_3, SWT.NONE);
		lblDataPagamento.setText("Data pagamento");
		
		DateTime dateTime = new DateTime(composite_3, SWT.BORDER);
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
		new Label(composite_3, SWT.NONE);
		
		TableViewer tvLancamentos = new TableViewer(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		tbLancamentos = tvLancamentos.getTable();
		tbLancamentos.setHeaderVisible(true);
		tbLancamentos.setLinesVisible(true);
		GridData gd_table_1 = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
		gd_table_1.heightHint = 165;
		tbLancamentos.setLayoutData(gd_table_1);
		
		TableViewerColumn tvTipoLancamento = new TableViewerColumn(tvLancamentos, SWT.NONE);
		TableColumn tblclmnTipo_1 = tvTipoLancamento.getColumn();
		tblclmnTipo_1.setWidth(100);
		tblclmnTipo_1.setText("Tipo");
		tvTipoLancamento.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((LancamentoContabil)element).getTipoExtenso();
			}
		});
		
		TableViewerColumn tvcContaLancamento = new TableViewerColumn(tvLancamentos, SWT.NONE);
		TableColumn tblclmnConta = tvcContaLancamento.getColumn();
		tblclmnConta.setWidth(100);
		tblclmnConta.setText("Conta");
		tvcContaLancamento.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((LancamentoContabil)element).getConta().getCodigoDescricao();
			}
		});
		
		TableViewerColumn tvcValorConta = new TableViewerColumn(tvLancamentos, SWT.NONE);
		TableColumn tblclmnValor_1 = tvcValorConta.getColumn();
		tblclmnValor_1.setWidth(100);
		tblclmnValor_1.setText("Valor");
		tvcValorConta.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((LancamentoContabil)element).getValor().toString();
			}
		});
		
		TableViewerColumn tvcHistoricoLancamento = new TableViewerColumn(tvLancamentos, SWT.NONE);
		TableColumn tblclmnHistrico = tvcHistoricoLancamento.getColumn();
		tblclmnHistrico.setWidth(100);
		tblclmnHistrico.setText("Histórico");
		tvcHistoricoLancamento.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((LancamentoContabil)element).getHistorico();
			}
		});
		
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);
		
		Button btnBaixarConta = new Button(composite_3, SWT.NONE);
		btnBaixarConta.setText("Efetuar baixa");
		btnBaixarConta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Object> contas = SelectionHelper.getObjects(tvContas);
				for (Object conta : contas) {
					((ContaPagarReceber)conta).efetuarBaixa(movimentacao);
				}
			}
		});
	}

	@Override
	public void setFocus() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
}
