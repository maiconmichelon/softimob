package br.com.michelon.softimob.tela.dialog;

import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.others.ReportGen;
import br.com.michelon.softimob.tela.view.ReportView;
import br.com.michelon.softimob.tela.widget.DateTextField;

public class BalanceteDialog extends TitleAreaDialog{
	
	private Text text;
	private Text text_1;
	
	private DateTextField dateInicial;
	private DateTextField dateFinal;

	public BalanceteDialog() {
		super(ShellHelper.getActiveShell());
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("Balancete");
		setMessage("Informe o periodo");
		
		Composite area = (Composite) super.createDialogArea(parent);
		
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		Label lblDataInicial = new Label(composite, SWT.NONE);
		lblDataInicial.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataInicial.setText("Data Inicial ");
		
		dateInicial = new DateTextField(composite);
		text = dateInicial.getControl();
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDataFinal = new Label(composite, SWT.NONE);
		lblDataFinal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataFinal.setText("Data Final");
		
		dateFinal = new DateTextField(composite);
		text_1 = dateFinal.getControl();
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		return area;
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void okPressed() {
		Map parameters = Maps.newHashMap();
		parameters.put("dataInicial", dateInicial.getValue());
		parameters.put("dataFinal", dateFinal.getValue());
		
		JasperPrint jprint = ReportGen.generateReport(parameters, "reports/Balancete.jasper");
		
		try {
			ReportView showView = (ReportView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ReportView.ID);
			showView.getReportViewer().setDocument(jprint);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		super.okPressed();
	}
	
}
