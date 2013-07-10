package br.com.michelon.softimob.tela.dialog;

import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.OkListElementDialogListener;
import br.com.michelon.softimob.aplicacao.others.ReportGen;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.tela.view.ReportView;
import br.com.michelon.softimob.tela.widget.DateTextField;

import com.google.common.collect.Maps;

public class RazaoDialog extends TitleAreaDialog{
	
	private Text text;
	private Text text_1;
	private Text text_2;
	private PlanoConta planoConta;
	private DateTextField dtInicial;
	private DateTextField dtFinal;
	
	public RazaoDialog() {
		super(ShellHelper.getActiveShell());
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("Razão");
		setMessage("Informe o periodo e a conta");
		
		Composite area = (Composite) super.createDialogArea(parent);
		
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		Label lblDataInicial = new Label(composite, SWT.NONE);
		lblDataInicial.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataInicial.setText("Data Inicial ");
		
		dtInicial = new DateTextField(composite);
		text = dtInicial.getControl();
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		Label lblDataFinal = new Label(composite, SWT.NONE);
		lblDataFinal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataFinal.setText("Data Final");
		
		dtFinal = new DateTextField(composite);
		text_1 = dtFinal.getControl();
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		Label lblPlanoDeConta = new Label(composite, SWT.NONE);
		lblPlanoDeConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPlanoDeConta.setText("Plano de Conta");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(composite, SWT.NONE);
		button.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button, new OkListElementDialogListener() {
			@Override
			public void ok(Object obj) {
				setPlanoConta((PlanoConta) obj);
				text_2.setText(obj.toString());
			}
		});
		
		return area;
	}
	
	public void setPlanoConta(PlanoConta planoConta) {
		this.planoConta = planoConta;
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void okPressed() {
		if(planoConta == null){
			DialogHelper.openWarning("Um plano de contas deve ser informado");
			return;
		}
		
		Map parameters = Maps.newHashMap();
		parameters.put("dataInicio", dtInicial.getValue());
		parameters.put("dataFinal", dtFinal.getValue());
		parameters.put("plano_conta_id", planoConta.getId());
		
		JasperPrint jprint = ReportGen.generateReport(parameters, "reports/razao.jasper");
		
		try {
			ReportView showView = (ReportView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ReportView.ID);
			showView.getReportViewer().setDocument(jprint);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		super.okPressed();
	}
	
}
