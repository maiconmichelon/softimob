package br.com.michelon.softimob.tela.widget;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.NumberFormatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class NumberTextField extends FormattedText{

	public NumberTextField(Composite parent) {
		super(parent, SWT.BORDER);

		NumberFormatter formatter2 = new NumberFormatter("##########");
		formatter2.setDecimalSeparatorAlwaysShown(false);
		setFormatter(formatter2);
	}
	
}
