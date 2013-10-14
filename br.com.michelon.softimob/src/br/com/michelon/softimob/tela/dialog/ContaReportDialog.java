package br.com.michelon.softimob.tela.dialog;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.exception.ParametroNaoInformadoException;
import br.com.michelon.softimob.aplicacao.helper.DateHelper;
import br.com.michelon.softimob.tela.dialog.reports.ReportDialog;
import br.com.michelon.softimob.tela.widget.DateTextField;

import com.google.common.collect.Maps;

public class ContaReportDialog extends ReportDialog{
	
	public ContaReportDialog() {
	}
	
	private Text text;
	private Text text_1;
	private DateTextField dtInicial;
	private DateTextField dtFinal;
	private Button btnTodasStatus;
	private Button btnPagar;
	private Button btnReceber;
	private Button btnTodasJaPagoRecebido;
	private Button btnPagarecebida;
	private Button btnAPagarreceber;
	
	@Override
	protected void criarComponentes(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		Label lblDataInicial = new Label(composite, SWT.NONE);
		lblDataInicial.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataInicial.setText("Data Inicial ");
		
		dtInicial = new DateTextField(composite);
		text = dtInicial.getControl();
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		dtInicial.setValue(DateHelper.getPrimeiroDiaMes());
		
		Label lblDataFinal = new Label(composite, SWT.NONE);
		lblDataFinal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataFinal.setText("Data Final");
		
		dtFinal = new DateTextField(composite);
		text_1 = dtFinal.getControl();
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		dtFinal.setValue(DateHelper.getUltimoDiaMes());
		
		Group grpStatus = new Group(composite, SWT.NONE);
		grpStatus.setLayout(new GridLayout(3, false));
		grpStatus.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false, 2, 1));
		grpStatus.setText("Status");
		
		btnTodasStatus = new Button(grpStatus, SWT.RADIO);
		btnTodasStatus.setText("Todas");
		btnTodasStatus.setSelection(true);

		btnPagar = new Button(grpStatus, SWT.RADIO);
		btnPagar.setText("Pagar");
		
		btnReceber = new Button(grpStatus, SWT.RADIO);
		btnReceber.setText("Receber");
		
		Group grpPagamento = new Group(composite, SWT.NONE);
		grpPagamento.setLayout(new GridLayout(3, false));
		grpPagamento.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 2, 1));
		grpPagamento.setText("Pagamento");
		
		btnTodasJaPagoRecebido = new Button(grpPagamento, SWT.RADIO);
		btnTodasJaPagoRecebido.setText("Todas");
		btnTodasJaPagoRecebido.setSelection(true);

		btnPagarecebida = new Button(grpPagamento, SWT.RADIO);
		btnPagarecebida.setText("Paga/Recebida");
		
		btnAPagarreceber = new Button(grpPagamento, SWT.RADIO);
		btnAPagarreceber.setText("A Pagar/Receber");
		
	}

	@Override
	protected Map<String, Object> getParametros() throws ParametroNaoInformadoException {
		if(dtInicial.getValue() == null || dtFinal.getValue() == null)
			throw new ParametroNaoInformadoException("É necessário que as datas sejam informadas.");

		Map<String, Object> parameters = Maps.newHashMap();
		parameters.put("dataInicio", dtInicial.getValue());
		parameters.put("dataFinal", dtFinal.getValue());
		parameters.put("jaPagoRecebido", btnTodasJaPagoRecebido.getSelection() ? null : btnPagarecebida.getSelection());
		parameters.put("pagarReceber", btnTodasStatus.getSelection() ? null : btnPagar.getSelection() ? 1 : 2);
		
		return parameters;
	}

	@Override
	protected String getCaminhoRelatorio() {
		return "reports/contapagarreceber.jasper";
	}

	@Override
	public String getMessage() {
		return "Informe o tipo e o período desejado.";
	}

	@Override
	protected String getTitleDialog() {
		return "Contas a pagar/receber";
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(400, 309);
	}
}
