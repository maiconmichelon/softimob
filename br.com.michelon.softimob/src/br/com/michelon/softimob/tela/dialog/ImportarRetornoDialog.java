package br.com.michelon.softimob.tela.dialog;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.exception.ParametroNaoInformadoException;
import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.service.ContaPagarReceberService;
import br.com.michelon.softimob.modelo.BoletoSoftimob;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.LancamentoContabil;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.modelo.ParametrosEmpresa;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.MoneyTextField;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class ImportarRetornoDialog extends TitleAreaDialog {
	
	private WritableValue value = WritableValue.withValueType(BoletoSoftimob.class);

	private Text txtDataPagamento;
	private Text txtNossoNumero;
	private DateTextField dateFieldPagamento;
	private DateTextField dateFieldVencimento;
	private MoneyTextField txtValorPago;
	private MoneyTextField txtJuros;
	private MoneyTextField txtDesconto;
	private TableViewer tvLancamentos;
	private final List<BoletoSoftimob> boletos;
	
	/**
	 * Create the dialog.
	 * @param boleto
	 */
	public ImportarRetornoDialog(List<BoletoSoftimob> boletos) {
		super(ShellHelper.getActiveShell());
		this.boletos = boletos;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Importar arquivo de retorno.");
		setTitle("Arquivo de Retorno");
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Composite composite_1 = new Composite(container, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		criarTabelaBoleto(composite_1, boletos);
		
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		TabItem tbtmBoleto = new TabItem(tabFolder, SWT.NONE);
		tbtmBoleto.setText("Boleto");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmBoleto.setControl(composite_2);
		composite_2.setLayout(new GridLayout(4, false));
		
		Label lblData = new Label(composite_2, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data de Pagamento");
		
		dateFieldPagamento = new DateTextField(composite_2);
		dateFieldPagamento.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		txtDataPagamento = dateFieldPagamento.getControl();
		txtDataPagamento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDataDeVencimento = new Label(composite_2, SWT.NONE);
		lblDataDeVencimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeVencimento.setText("Data de Vencimento");
		
		dateFieldVencimento = new DateTextField(composite_2);
		dateFieldVencimento.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblNossoNmero = new Label(composite_2, SWT.NONE);
		lblNossoNmero.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNossoNmero.setText("Nosso número");
		
		txtNossoNumero = new Text(composite_2, SWT.BORDER);
		txtNossoNumero.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		txtNossoNumero.setEnabled(false);
		
		Label lblValorPago = new Label(composite_2, SWT.NONE);
		lblValorPago.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValorPago.setText("Valor Pago");
		
		txtValorPago = new MoneyTextField(composite_2);
		txtValorPago.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblJuros = new Label(composite_2, SWT.NONE);
		lblJuros.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblJuros.setText("Juros");
		
		txtJuros = new MoneyTextField(composite_2);
		txtJuros.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblDesconto = new Label(composite_2, SWT.NONE);
		lblDesconto.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDesconto.setText("Desconto");
		
		txtDesconto = new MoneyTextField(composite_2);
		txtDesconto.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		
		Button btnGerarContbil = new Button(composite_2, SWT.NONE);
		btnGerarContbil.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnGerarContbil.setText("Gerar Contábil");
		btnGerarContbil.setImage(ImageRepository.GERAR_CONTABIL16.getImage());
		btnGerarContbil.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if(getModelo() == null || getModelo().getConta() == null)
						return;
					
					gerarContabil();
					tvLancamentos.setInput(getModelo().getConta().getMovimentacao().getLancamentos());
				} catch (Exception e1) {
					Logger.getLogger(getClass()).error("Erro ao gerar movimentação.", e1);
				}
			}
		});
		
		Composite composite = new Composite(composite_2, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 4, 1));
		criarTabelaMovimentacao(composite);
		
		initDataBindings();
		
		return area;
	}

	protected void gerarContabil() throws Exception {
		if(getModelo() == null)
			return;
		
		BoletoSoftimob boleto = getModelo();
		ContaPagarReceber conta = boleto.getConta();
		ParametrosEmpresa param = ParametrosEmpresa.getInstance();
		
		PlanoConta caixa = param.getContaCaixa();
		if(caixa == null )
			throw new ParametroNaoInformadoException("A conta caixa parametrizada não pode ser vazia.");
		
		MovimentacaoContabil mov = new MovimentacaoContabil();
		mov.setData(getModelo().getDataPagamento());
		mov.setValor(boleto.getValor());
		
		conta.setValorJurosDesconto(boleto.getValorCobrado().subtract(conta.getValor()));
		
		conta.setDataPagamento(boleto.getDataPagamento());
		conta.setMovimentacao(mov);
		
		List<LancamentoContabil> gerarLancamentos = new ContaPagarReceberService().gerarLancamentos(conta, mov, boleto.getDataPagamento(), caixa);
		mov.setLancamentos(gerarLancamentos);
		
		tvLancamentos.refresh();
	}

	private void criarTabelaBoleto(Composite cpTabelaBoleto, List<BoletoSoftimob> boletos) {
		TableViewerBuilder tvb = new TableViewerBuilder(cpTabelaBoleto);
		
		tvb.createColumn("Pagamento").bindToProperty("dataPagamento").format(new DateStringValueFormatter()).setPercentWidth(16).build();
		tvb.createColumn("Vencimento").bindToProperty("dataVencimento").format(new DateStringValueFormatter()).setPercentWidth(16).build();
		tvb.createColumn("Nosso número").bindToProperty("nossoNumeroFormatado").setPercentWidth(25).build();
		tvb.createColumn("Valor pago").bindToProperty("valor").setPercentWidth(20).format(FormatterHelper.getDefaultValueFormatterToMoney()).build();
		tvb.createColumn("Juros").bindToProperty("mora").setPercentWidth(20).format(FormatterHelper.getDefaultValueFormatterToMoney()).build();
		tvb.createColumn("Desconto").bindToProperty("desconto").setPercentWidth(20).format(FormatterHelper.getDefaultValueFormatterToMoney()).build();
		
		tvb.getTableViewer().addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				BoletoSoftimob boleto = (BoletoSoftimob) SelectionHelper.getObject(event.getSelection());
				value.setValue(boleto);
				
				if(boleto.getConta().getMovimentacao() == null) {
					try {
						gerarContabil();
					} catch (Exception e) {
						DialogHelper.openErrorMultiStatus("Houve um erro ao gerar contábil do boleto.", e.getMessage());
						Logger.getLogger(getClass()).error("Erro ao gerar contábil de boleto.", e);
						return;
					}
				}
				
				tvLancamentos.setInput(boleto.getConta().getMovimentacao().getLancamentos());
			}
		});
		
		tvb.setInput(boletos);
	}

	public BoletoSoftimob getModelo() {
		return (BoletoSoftimob) value.getValue();
	}
	
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(900, 700);
	}
	
	public void criarTabelaMovimentacao (Composite composite) {
		TableViewerBuilder tvb = new TableViewerBuilder(composite);
		
		tvb.createColumn("Tipo").bindToProperty("tipo").setPercentWidth(10).build();
		tvb.createColumn("Conta").bindToProperty("conta.codigoDescricao").setPercentWidth(20).build();
		tvb.createColumn("Valor").bindToProperty("valor").setPercentWidth(10).format(FormatterHelper.getDecimalFormatter()).build();
		tvb.createColumn("Histórico").bindToProperty("historico").setPercentWidth(40).makeEditable().build();
		tvb.createColumn("Complemento").bindToProperty("complemento").setPercentWidth(40).makeEditable().build();
		
		tvLancamentos = tvb.getTableViewer();
		tvLancamentos.setContentProvider(ArrayContentProvider.getInstance());
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Softimob");
		super.configureShell(newShell);
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDataPagamento);
		IObservableValue valueDataPagamentoObserveDetailValue = PojoProperties.value(BoletoSoftimob.class, "dataPagamento", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueDataPagamentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextDateFieldVencimentogetControlObserveWidget = WidgetProperties.text(SWT.Modify).observe(dateFieldVencimento.getControl());
		IObservableValue valueDataVencimentoObserveDetailValue = PojoProperties.value(BoletoSoftimob.class, "dataVencimento", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextDateFieldVencimentogetControlObserveWidget, valueDataVencimentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextTxtNossoNumeroObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtNossoNumero);
		IObservableValue valueNossoNumeroObserveDetailValue = PojoProperties.value(BoletoSoftimob.class, "nossoNumeroFormatado", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtNossoNumeroObserveWidget, valueNossoNumeroObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtValorPagoObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtValorPago);
		IObservableValue valueValorPagoObserveDetailValue = PojoProperties.value(BoletoSoftimob.class, "valor", BigDecimal.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtValorPagoObserveWidget, valueValorPagoObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		//
		IObservableValue observeTextTxtJurosObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtJuros);
		IObservableValue valueValorPagoObserveDetailValue_1 = PojoProperties.value(BoletoSoftimob.class, "mora", BigDecimal.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtJurosObserveWidget, valueValorPagoObserveDetailValue_1, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		//
		IObservableValue observeTextTxtDescontoObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDesconto);
		IObservableValue valueDescontoObserveDetailValue = PojoProperties.value(BoletoSoftimob.class, "desconto", BigDecimal.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtDescontoObserveWidget, valueDescontoObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		//
		return bindingContext;
	}
}
