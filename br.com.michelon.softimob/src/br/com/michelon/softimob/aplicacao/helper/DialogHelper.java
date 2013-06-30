package br.com.michelon.softimob.aplicacao.helper;

import org.eclipse.jface.dialogs.MessageDialog;

public class DialogHelper {

	public static void openError(String message){
		MessageDialog.openError(ShellHelper.getActiveShell(), "Softimob", message);
	}
	
	public static void openInformation(String message){
		MessageDialog.openInformation(ShellHelper.getActiveShell(), "Softimob", message);
	}
	
	public static boolean openConfirmation(String message){
		return MessageDialog.openConfirm(ShellHelper.getActiveShell(), "Softimob", message);
	}
	
}
