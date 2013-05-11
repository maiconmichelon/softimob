package br.com.michelon.softimob.tela.widget;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.TimeFormatter;
import org.eclipse.swt.widgets.Composite;

public class TimeTextField extends FormattedText{

	public TimeTextField(Composite parent) {
		super(parent);

		setFormatter(new TimeFormatter());
	}

}
