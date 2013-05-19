package br.com.michelon.softimob.aplicacao.helper;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.modelo.Funcionario;

public class ListElementDialogHelper {
	
	public static void addListElementDialogToText(final TipoDialog tipoDialog, Text text, final WritableValue value, final String property){
		text.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.ALT){
					openDialogAndSetValue(tipoDialog, value, property);
				}
			}
		});
	}
	
	public static void addSelectionListDialogToButton(final TipoDialog tipoDialog, Button button, final WritableValue value, final String property){
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openDialogAndSetValue(tipoDialog, value, property);
			}
		});
	}
	
	private static void openDialogAndSetValue(TipoDialog tipoDialog, WritableValue value, String property) {
		//cria o dialog
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(ShellHelper.getActiveShell(), new LabelProvider(){
			@Override
			public String getText(Object element) {
				return element.toString();
			}
		}); 
		
		dialog.setTitle(tipoDialog.getTitle());
		dialog.setMessage(tipoDialog.getMessage());
		dialog.setElements(tipoDialog.getElements());
		
		if(dialog.open() == IDialogConstants.OK_ID){
			try {
				ReflectionHelper.setAtribute(value.getValue(), property, dialog.getFirstResult());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public enum TipoDialog{
		
		FUNCIONARIO("Funcionários", "Selecione um funcionário."),
		CLIENTE("Clientes", "Selecione um cliente."), 
		COMODO("Cômodos", "Selecione um cômodo"),
		IMOVEL("Imóveis", "Selecione um imóvel");
		
		private final String title;
		private final String message;

		private TipoDialog(String title, String message) {
			this.title = title;
			this.message = message;
		}
		
		public String getMessage() {
			return message;
		}
		
		public String getTitle() {
			return title;
		}
		
		public Object[] getElements(){
			if(equals(FUNCIONARIO)){
				return new Funcionario[]{new Funcionario("Maicon")};
			} else if(equals(CLIENTE)){
				return new Cliente[]{new Cliente("Maicon")};
			} else{
				return null;
			}
		}
	}
	
}