package br.com.michelon.softimob.tela.dialog;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.exception.ParametroNaoInformadoException;
import br.com.michelon.softimob.aplicacao.helper.DateHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.OkListElementDialogListener;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.tela.dialog.reports.ReportDialog;
import br.com.michelon.softimob.tela.widget.DateTextField;

import com.google.common.collect.Maps;

public class RazaoDialog extends ReportDialog{
	
	public RazaoDialog() {
	}
	
	private Text text;
	private Text text_1;
	private Text text_2;
	private PlanoConta planoConta;
	private DateTextField dtInicial;
	private DateTextField dtFinal;
	
	public void setPlanoConta(PlanoConta planoConta) {
		this.planoConta = planoConta;
	}
	
	@Override
	protected void criarComponentes(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(4, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		Label lblDataInicial = new Label(composite, SWT.NONE);
		lblDataInicial.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataInicial.setText("Data Inicial ");
		
		dtInicial = new DateTextField(composite);
		text = dtInicial.getControl();
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		dtInicial.setValue(DateHelper.getPrimeiroDiaMes());
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblDataFinal = new Label(composite, SWT.NONE);
		lblDataFinal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataFinal.setText("Data Final");
		
		dtFinal = new DateTextField(composite);
		text_1 = dtFinal.getControl();
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		dtFinal.setValue(DateHelper.getUltimoDiaMes());
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblPlanoDeConta = new Label(composite, SWT.NONE);
		lblPlanoDeConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPlanoDeConta.setText("Plano de Conta");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setEditable(false);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(composite, SWT.NONE);
		button.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button, new OkListElementDialogListener() {
			@Override
			public void ok(Object obj) {
				setPlanoConta((PlanoConta) obj);
				text_2.setText(obj.toString());
			}
		});
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setImage(ImageRepository.REMOVE_16.getImage());
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setPlanoConta(null);
				text_2.setText("");
			}
		});
	}

	@Override
	protected Map<String, Object> getParametros() throws ParametroNaoInformadoException {
		if(dtInicial.getValue() == null || dtFinal.getValue() == null)
			throw new ParametroNaoInformadoException("É necessário que as datas sejam informadas.");
		if(planoConta == null)
			throw new ParametroNaoInformadoException("Informe o plano de contas.");

		Map<String, Object> parameters = Maps.newHashMap();
		parameters.put("dataInicio", dtInicial.getValue());
		parameters.put("dataFinal", dtFinal.getValue());
		parameters.put("plano_conta_id", planoConta.getId());
		
		
		return parameters;
	}

	@Override
	protected String getCaminhoRelatorio() {
		return "reports/razao.jasper";
	}

	@Override
	public String getMessage() {
		return "Informe o periodo e a conta.";
	}

	@Override
	protected String getTitleDialog() {
		return "Razão";
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(400, 250);
	}
	
}
