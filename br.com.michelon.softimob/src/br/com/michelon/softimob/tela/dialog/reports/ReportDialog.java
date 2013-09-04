package br.com.michelon.softimob.tela.dialog.reports;

import java.util.Calendar;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import br.com.michelon.softimob.aplicacao.exception.ParametroNaoInformadoException;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.reports.ReportGen;
import br.com.michelon.softimob.tela.view.ReportView;

public abstract class ReportDialog extends TitleAreaDialog {

	protected JasperPrint jprint;

	public ReportDialog(){
		this(ShellHelper.getActiveShell());
	}
	
	public ReportDialog(Shell shell) {
		super(shell);
		setShellStyle(SWT.MIN);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);

		criarComponentes(area);

		setTitle(getTitleDialog());
		setMessage(getMessage());

		return area;
	}

	protected abstract void criarComponentes(Composite area);

	protected abstract Map<String, Object> getParametros() throws ParametroNaoInformadoException;

	protected abstract String getCaminhoRelatorio();

	public abstract String getMessage();

	protected abstract String getTitleDialog();

	@Override
	protected void okPressed() {
		try{
			final Map<String, Object> parametros = getParametros();

			Job job = new Job("Gerando relatório.") {
	
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					jprint = ReportGen.generateReport(parametros, getCaminhoRelatorio());
	
					return Status.OK_STATUS;
				}
			};
	
			job.addJobChangeListener(new JobChangeAdapter() {
				@Override
				public void done(final IJobChangeEvent event) {
					Display.getDefault().syncExec(new Runnable() {
						@Override
						public void run() {
							
							if (event.getResult().equals(Status.OK_STATUS) && !jprint.getPages().isEmpty()) {
								
								try {
									IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
									ReportView showView = (ReportView) page.showView(ReportView.ID, String.valueOf(Calendar.getInstance().getTimeInMillis()), IWorkbenchPage.VIEW_CREATE);
									page.bringToTop(showView);
									showView.getReportViewer().setDocument(jprint);
								} catch (PartInitException e) {
									e.printStackTrace();
								}
								
							} else
								setErrorMessage("Nenhum registro encontrado.");
						}
					});
				}
			});
	
			job.setUser(true);
			job.schedule();
		}catch(ParametroNaoInformadoException pe){
			setErrorMessage(pe.getMessage() == null ? "Informe os parâmetros corretamente." : pe.getMessage());
		}
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Softimob");
		super.configureShell(newShell);
	}

}
