package br.com.michelon.softimob.aplicacao.helper;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.ImageRepository;
import org.eclipse.wb.swt.SWTResourceManager;

import br.com.michelon.softimob.tela.widget.SucessfulContributionItem;

public class SWTHelper {

	private static Logger log = Logger.getLogger(SWTHelper.class);

	private static final int TIME = 1000;
	private static final int INTERVAL = 50;

	public static void setSuccesfulMessageInBottomScreen(String message) {
		SucessfulContributionItem.getInstance().setSucessfulMessage(message);
	}
	
	public static void setSuccesfulMessageInBottomScreen(String message, IStatusLineManager lineManager) {
		setMessageInBottonScreen(message, false, ImageRepository.SUCCESS_16.getImage(), lineManager);
	}

	public static void setErrorMessageInBottomScreen(String message, IStatusLineManager lineManager) {
		setMessageInBottonScreen(message, false, ImageRepository.ERROR_16.getImage(), lineManager);
	}

	public static void setMessageInBottonScreen(final String message, final boolean isError, final Image img, final IStatusLineManager lineManager) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				final IProgressMonitor progressMonitor = lineManager.getProgressMonitor();
				
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						progressMonitor.beginTask("Salvando Item.", TIME/INTERVAL);
					}
				});
				
				try {
					for(int i = 0; i < TIME/INTERVAL + 10 ; i++){
						
						Display.getDefault().asyncExec(new Runnable() {
							@Override
							public void run() {
								progressMonitor.worked(1);
							}
						});
						
						Thread.sleep(TIME/(TIME/INTERVAL));
					}
				} catch (Exception e) {
					log.error("Erro ao pausar Thread que mostra mensagem na Status Line.", e);
				}
				
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						progressMonitor.done();
						
						if (isError)
							lineManager.setErrorMessage(img, message);
						else
							lineManager.setMessage(img, message);
					}
				});
				
				try {
					Thread.sleep(TIME*2);
				} catch (InterruptedException e) {
					log.error("Erro ao pausar Thread que mostra mensagem na Status Line.", e);
				}
				
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						if (isError)
							lineManager.setErrorMessage(null, "");
						else
							lineManager.setMessage(null, "");
					}
				});
			}
		}).start();
	}
	
	public static Color getYellowColor(){
		return SWTResourceManager.getColor(255, 153, 0);
	}
	
}
