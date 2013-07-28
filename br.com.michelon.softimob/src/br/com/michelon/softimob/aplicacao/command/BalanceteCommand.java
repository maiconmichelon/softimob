package br.com.michelon.softimob.aplicacao.command;

import java.io.File;
import java.math.BigDecimal;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.FileDialog;

import br.com.michelon.softimob.aplicacao.helper.DocxHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.modelo.Venda;
import br.com.michelon.softimob.tela.dialog.BalanceteDialog;

public class BalanceteCommand extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		new BalanceteDialog().open();
		
//		FileDialog dialog = new FileDialog(ShellHelper.getActiveShell());
//		String open = dialog.open();
//		Venda object = new Venda();
//		object.setValor(BigDecimal.TEN);
//		new DocxHelper().createPartControl(new File(open), object);
		
		
		return null;
	}

}
