package br.com.michelon.softimob.tela.dialog.reports;

import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.others.ReportGen;
import br.com.michelon.softimob.tela.view.ReportView;

public abstract class ReportDialog extends TitleAreaDialog{

	public ReportDialog() {
		super(ShellHelper.getActiveShell());
		setShellStyle(SWT.MIN);
	}

	@Override
	protected Control createDialogArea(Composite parent){
		Composite area = (Composite) super.createDialogArea(parent);
		
		criarComponentes(area);
		
		setTitle(getTitle());
		setMessage(getMessage());
		
		return area;
	}
	
	protected abstract void criarComponentes(Composite area);
	
	protected abstract Map<String, Object> getParametros();
	
	protected abstract String getCaminhoRelatorio();
	
	public abstract String getMessage();
	
	protected abstract String getTitle();
	
	@Override
	protected void okPressed() {
		JasperPrint jprint = ReportGen.generateReport(getParametros(), getCaminhoRelatorio());
		
		try {
			if(!jprint.getPages().isEmpty()){
				ReportView showView = (ReportView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ReportView.ID);
				showView.getReportViewer().setDocument(jprint);
			} else { 
				setErrorMessage("Nenhum registro encontrado.");
				return;
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		super.okPressed();
	}
	
}
