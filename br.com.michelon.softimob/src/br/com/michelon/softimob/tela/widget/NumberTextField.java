package br.com.michelon.softimob.tela.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class NumberTextField extends Text{

	public NumberTextField(Composite parent) {
		super(parent, SWT.BORDER);
		
		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				switch (e.keyCode) {
					case SWT.BS:
					case SWT.DEL:
					case SWT.HOME:
					case SWT.END:
					case SWT.ARROW_LEFT:
					case SWT.ARROW_RIGHT:
						return;
				}
				
				if (!Character.isDigit(e.character)){
					e.doit = false;
				}
			}
		});
	}
	
	public Text getControl() {
		return this;
	}

	@Override
	protected void checkSubclass() {
		return;
	}
	
}
