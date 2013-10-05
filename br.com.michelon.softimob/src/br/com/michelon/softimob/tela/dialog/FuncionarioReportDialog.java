package br.com.michelon.softimob.tela.dialog;

import java.util.Date;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.exception.ParametroNaoInformadoException;
import br.com.michelon.softimob.aplicacao.helper.DateHelper;
import br.com.michelon.softimob.tela.dialog.reports.ReportDialog;
import br.com.michelon.softimob.tela.widget.DateTextField;

import com.google.common.collect.Maps;

public class FuncionarioReportDialog extends ReportDialog{
	
	private Text text;
	private Text text_1;
	
	private DateTextField dateInicial;
	private DateTextField dateFinal;

	@Override
	protected Point getInitialSize() {
		return new Point(410, 215);
	}

	@Override
	protected Map<String, Object> getParametros() throws ParametroNaoInformadoException {
		if(dateInicial.getValue() == null || dateFinal.getValue() == null)
			throw new ParametroNaoInformadoException();
		
		Map<String, Object> parameters = Maps.newHashMap();
		parameters.put("dataInicio", dateInicial.getValue());
		parameters.put("dataFinal", dateFinal.getValue());
		
		return parameters;
	}

	@Override
	protected String getCaminhoRelatorio() {
		return "reports/Funcionarios.jasper";
	}

	@Override
	protected void criarComponentes(Composite area) {
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		Label lblDataInicial = new Label(composite, SWT.NONE);
		lblDataInicial.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataInicial.setText("Data Inicial ");
		
		dateInicial = new DateTextField(composite);
		text = dateInicial.getControl();
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		dateInicial.setValue(DateHelper.getPrimeiroDiaMes());
		
		Label lblDataFinal = new Label(composite, SWT.NONE);
		lblDataFinal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataFinal.setText("Data Final");
		
		dateFinal = new DateTextField(composite);
		text_1 = dateFinal.getControl();
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		dateFinal.setValue(new Date());
		dateFinal.setValue(DateHelper.getUltimoDiaMes());
	}

	public String getMessage() {
		return "Informe o periodo.";
	}
	
	protected String getTitleDialog(){
		return "Desempenho dos Funcionários";
	}
	
}
