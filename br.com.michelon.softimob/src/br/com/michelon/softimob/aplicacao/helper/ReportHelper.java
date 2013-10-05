package br.com.michelon.softimob.aplicacao.helper;

import java.util.Calendar;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import br.com.michelon.softimob.aplicacao.listener.OnNenhumRegistroEncontrado;
import br.com.michelon.softimob.aplicacao.listener.OnSuccessfulListener;
import br.com.michelon.softimob.aplicacao.reports.ReportGen;
import br.com.michelon.softimob.tela.view.ReportView;

public class ReportHelper {

	private static JasperPrint jprint;
	
	
	
	public static void gerarRelatorio(Map<String,Object> params, final String caminho){
		gerarRelatorio(params, caminho, new OnNenhumRegistroEncontrado() {
			@Override
			public void onError(String message) {
				DialogHelper.openWarning("Nenhum registro encontrado.");
			}
		}, new OnSuccessfulListener() {
			// Só para não aparecer o dialog chato :D
			@Override
			public void onSucessful(String message) {}
		});
	}
	
	public static void gerarRelatorio(Map<String,Object> params, final String caminho, final OnNenhumRegistroEncontrado errorListener, final OnSuccessfulListener onSuccessfulListener){
		final Map<String, Object> parametros = params;
		Job job = new Job("Gerando relatório.") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Gerando relatórios...", -1);
				
				jprint = ReportGen.generateReport(parametros, caminho);
				
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
								onSuccessfulListener.onSucessful("Relatatório gerado com sucesso!"); // Aqui acho qeu só vai servir pro ReportDialog para sobrepor a mensagem de erro do TitleDialog
							} catch (PartInitException e) {
								Logger.getLogger(getClass()).error("Erro ao abrir tela de visualização de relatório.", e);
							}
							
						} else
							errorListener.onError("Nenhum registro encontrado.");
					}
				});
			}
		});
		job.setUser(true);
		job.schedule();
	}
	
}
