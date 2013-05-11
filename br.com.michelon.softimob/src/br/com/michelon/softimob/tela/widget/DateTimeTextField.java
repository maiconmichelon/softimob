package br.com.michelon.softimob.tela.widget;

import org.eclipse.nebula.widgets.formattedtext.DateTimeFormatter;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.swt.widgets.Composite;

public class DateTimeTextField extends FormattedText{

	public DateTimeTextField(Composite parent) {
		super(parent);
		
		setFormatter(new DateTimeFormatter());
	}
	
}
