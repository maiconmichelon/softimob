package br.com.michelon.softimob.aplicacao.helper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;

import br.com.michelon.softimob.modelo.Arquivo;

public class ContratoHelper {

	private static Logger log = Logger.getLogger(ContratoHelper.class);
	
	public static void gerarContrato(final Arquivo modeloContrato, final Object obj){
		Job job = new Job("Gerando Contrato.") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Gerando contrato...", -1);
				
				File criarDiretorioArquivos = FileHelper.criarDiretorioArquivos(Arrays.asList(modeloContrato));
				File arq = new File(criarDiretorioArquivos, modeloContrato.getNome());
				
				try {
					new DocxHelper().gerarContrato(arq, obj);
				} catch (final Exception e) {
					
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							log.error("Erro ao alterar documento .docx .", e);
						}
					});
					return Status.CANCEL_STATUS;
				}
				
				try {
					FileHelper.openFile(criarDiretorioArquivos, modeloContrato.getNome());
				} catch (IOException e1) {
					log.error("Erro ao abrir contrato.", e1);
				}
				
				return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.schedule();
	}
	
}
