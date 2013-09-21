package br.com.michelon.softimob.tela.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class MoneyTextField {

	private Text control;
	
	public MoneyTextField(Composite parent) {
		control = new Text(parent, SWT.BORDER);
		
		control.addKeyListener(new KeyAdapter() {
			
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

				Text txt = (Text) e.widget;
				String v = txt.getText();
				int indexOf = v.indexOf(",");
				
				int s = txt.getSelection().y;
				
				if(indexOf != -1 && (v.length() - indexOf) > (3 - (s <= indexOf ? 0 : 1 ))){
					e.doit = false;
					return;
				}
				
				String vgl = String.valueOf(e.character);
				if (!(vgl.equals(",")) && !Character.isDigit(e.character))
					e.doit = false;
				else
					return;
			}
		});
	}
	
	public Text getControl() {
		return control;
	}
	
}
