package br.com.michelon.softimob.tela.widget;

import java.util.List;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Combo;

import br.com.michelon.softimob.aplicacao.service.GenericService;

public class LoadOnFocus {

	public static void setFocusGainedListener(final ComboViewer comboViewer, final Load load) {
		Combo combo = comboViewer.getCombo();
		
		combo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				ISelection selection = comboViewer.getSelection();
				comboViewer.setInput(load.getInput());
				comboViewer.setSelection(selection);
			}
		});
	}

	public static void setFocusGainedListener(ComboViewer comboViewer, final GenericService<?> service) {
		comboViewer.setInput(service.findAtivados());
		setFocusGainedListener(comboViewer, new Load() {
			@Override
			public List<?> getInput() {
				return service.findAtivados();
			}
		});
	}
	
	public interface Load{
		List<?> getInput();
	}
	
}
