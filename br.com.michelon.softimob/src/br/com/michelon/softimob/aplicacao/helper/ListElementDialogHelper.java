package br.com.michelon.softimob.aplicacao.helper;

import java.util.Arrays;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import br.com.michelon.softimob.modelo.Cliente;

public class ListElementDialogHelper {

	public static void addListElementDialogToText(Text text, final WritableValue object, final String properties){
		text.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.ALT){
					
					ElementListSelectionDialog dialog = new ElementListSelectionDialog(ShellHelper.getActiveShell(), new LabelProvider(){
						@Override
						public String getText(Object element) {
							return ((Cliente) element).getNome();
						}
					});
					
					dialog.setElements(Arrays.asList(new Cliente("Adalberto"), new Cliente("Alfredo"), new Cliente("Jose"), new Cliente("Joao")).toArray());
					
					if(dialog.open() == IDialogConstants.OK_ID){
						try {
							ReflectionHelper.setAtribute(object.getValue(), properties, (Cliente)dialog.getFirstResult());
							
							((Text) e.widget).setText((String) ReflectionHelper.getAtribute(object.getValue(), properties + ".nome"));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
			
		});
	}
	
}
