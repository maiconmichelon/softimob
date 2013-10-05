package br.com.michelon.softimob.aplicacao.helper;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import br.com.michelon.softimob.aplicacao.main.Activator;
import br.com.michelon.softimob.modelo.Arquivo;
import br.com.michelon.softimob.modelo.ArquivoBytes;

import com.google.common.collect.Lists;

public class DialogHelper {

//	public static void openError(String message){
//		MessageDialog.openError(ShellHelper.getActiveShell(), "Softimob", message);
//	}
	
	public static void openWarning(String message){
		MessageDialog.openWarning(ShellHelper.getActiveShell(), "Softimob", message);
	}
	
	public static void openInformation(String message){
		MessageDialog.openInformation(ShellHelper.getActiveShell(), "Softimob", message);
	}
	
	public static boolean openConfirmation(String message){
		return MessageDialog.openConfirm(ShellHelper.getActiveShell(), "Softimob", message);
	}
	
	public static List<Arquivo> openPhotoDialog(){
		return openFileDialog(new String[]{"*.png;*.jpg"});
	}
	
	public static List<Arquivo> openDocDialog(){
		return openFileDialog(new String[]{"*.docx"});
	}
	
	public static List<Arquivo> openFileDialog(String[] filters){
		FileDialog dialog = new FileDialog(ShellHelper.getActiveShell(), SWT.MULTI);
		
		if(filters != null && filters.length > 0)
			dialog.setFilterExtensions(filters);
		
		String caminho = dialog.open();
		
		if(caminho == null)
			return null;
		
		List<Arquivo> arquivos = Lists.newArrayList();
		for(String arq : dialog.getFileNames()){
			File file = new File(dialog.getFilterPath() + "/" + arq);

			Arquivo arquivo = new Arquivo();
			arquivo.setNome(file.getName());
			arquivo.setArquivo(new ArquivoBytes(FileHelper.getBytes(file)));
			arquivos.add(arquivo);
		}
		
		return arquivos;
	}
	
	public static IStatus createStatus(int status, String mensagem){
		return new Status(status, Activator.PLUGIN_ID, mensagem);
	}
	
	public static MultiStatus createMultiStatus(String mensagem, int codigoStatus, String... mensagens) {
		MultiStatus status = new MultiStatus (Activator.PLUGIN_ID, 1, mensagem, null);
		
		for (String msg : mensagens) {
			status.add(createStatus(codigoStatus, msg));
		}
		
		return status;
	}

	public static void openMultiStatus(int codigoStatus, String mensagem, String... mensagens) {
		ErrorDialog.openError(ShellHelper.getActiveShell(), "Softimob", null, createMultiStatus(mensagem, codigoStatus, mensagens));
	}
	
	public static void openErrorMultiStatus(String mensagem, String... mensagens){
		openMultiStatus(Status.ERROR, mensagem, mensagens);
	}

	public static void openWarningMultiStatus(String mensagem, String... mensagens){
		openMultiStatus(Status.WARNING, mensagem, mensagens);
	}
	
}
