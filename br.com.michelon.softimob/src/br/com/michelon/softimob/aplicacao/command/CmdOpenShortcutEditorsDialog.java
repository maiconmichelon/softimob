package br.com.michelon.softimob.aplicacao.command;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;

public class CmdOpenShortcutEditorsDialog extends AbstractHandler {

//	private static final String VIEWS_EXTENSION_POINT = "org.eclipse.ui.views";
	private static final String EDITORS_EXTENSION_POINT = "org.eclipse.ui.editors";
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		
		IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
		
		List<IConfigurationElement> editorConfigurations = Arrays.asList(extRegistry.getConfigurationElementsFor(EDITORS_EXTENSION_POINT));
//		List<IConfigurationElement> viewConfigurations = Arrays.asList(extRegistry.getConfigurationElementsFor(VIEWS_EXTENSION_POINT));
		
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(ShellHelper.getActiveShell(), new LabelProvider(){
			@Override
			public String getText(Object element) {
				return ((IConfigurationElement) element).getAttribute("name");
			}
		});
		
		dialog.setElements(editorConfigurations.toArray());
		
		if(dialog.open() == IDialogConstants.OK_ID){
			IConfigurationElement firstResult = (IConfigurationElement) dialog.getFirstResult();
			
			String id = firstResult.getAttribute("id");
			
			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new GenericEditorInput(), id);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
