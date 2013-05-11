package br.com.michelon.softimob.aplicacao.helper;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class ShellHelper {

	public static Shell getActiveShell(){
		return PlatformUI.getWorkbench().getDisplay().getActiveShell();
	}
	
}
