package br.com.michelon.softimob.aplicacao.helper;

import java.io.File;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import com.google.common.collect.Lists;

import br.com.michelon.softimob.modelo.ArquivoBytes;
import br.com.michelon.softimob.modelo.Arquivo;

public class DialogHelper {

	public static void openError(String message){
		MessageDialog.openError(ShellHelper.getActiveShell(), "Softimob", message);
	}
	
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
	
}
