package br.com.michelon.softimob.aplicacao.helper;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.ImageRepository;

public class SWTHelper {

	private static Logger log = Logger.getLogger(SWTHelper.class);

	private static final int TIME = 4000;

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
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						if (isError)
							lineManager.setErrorMessage(img, message);
						else
							lineManager.setMessage(img, message);
					}
				});

				try {
					Thread.sleep(TIME);
				} catch (Exception e) {
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
}
