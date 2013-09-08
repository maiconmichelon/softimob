package br.com.michelon.softimob.aplicacao.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import br.com.michelon.softimob.tela.dialog.FuncionarioReportDialog;

public class CmdFuncionarioReport extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		new FuncionarioReportDialog().open();
		
		return null;
	}

}
