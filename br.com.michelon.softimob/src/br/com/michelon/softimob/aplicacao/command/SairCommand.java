package br.com.michelon.softimob.aplicacao.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

import br.com.michelon.softimob.aplicacao.helper.DialogHelper;

public class SairCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if(DialogHelper.openConfirmation("Deseja sair do sistema ?"))
			return PlatformUI.getWorkbench().close();

		return null;
	}
	
}
