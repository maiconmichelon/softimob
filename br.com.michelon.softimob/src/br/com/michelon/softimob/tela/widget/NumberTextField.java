package br.com.michelon.softimob.tela.widget;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.NumberFormatter;
import org.eclipse.swt.widgets.Composite;

public class NumberTextField extends FormattedText{

	public NumberTextField(Composite parent) {
		super(parent);
		this.setFormatter(new NumberFormatter("#########"));
	}
	
}
