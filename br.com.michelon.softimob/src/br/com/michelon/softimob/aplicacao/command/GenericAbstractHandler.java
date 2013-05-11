package br.com.michelon.softimob.aplicacao.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

public abstract class GenericAbstractHandler extends AbstractHandler{

	protected IEditorInput editorInput;
	protected String id;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().openEditor(editorInput, id);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		return null;
	}

}
