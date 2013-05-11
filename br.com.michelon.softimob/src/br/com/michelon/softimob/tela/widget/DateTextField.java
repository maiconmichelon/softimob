package br.com.michelon.softimob.tela.widget;

import org.eclipse.nebula.widgets.formattedtext.DateFormatter;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.swt.widgets.Composite;

public class DateTextField extends FormattedText{

	public DateTextField(Composite parent) {
		super(parent);
		
		this.setFormatter(new DateFormatter());
		
	}
	
}
