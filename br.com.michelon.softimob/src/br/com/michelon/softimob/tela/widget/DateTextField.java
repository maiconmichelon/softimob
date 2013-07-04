package br.com.michelon.softimob.tela.widget;

import java.text.ParseException;
import java.util.Date;

import org.eclipse.nebula.widgets.formattedtext.DateFormatter;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.swt.widgets.Composite;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;

public class DateTextField extends FormattedText{

	public DateTextField(Composite parent) {
		super(parent);
		
		this.setFormatter(new DateFormatter());
		
	}
	
	@Override
	public Date getValue() {
		try {
			return FormatterHelper.getSimpleDateFormat().parse(getControl().getText());
		} catch (ParseException e) {
			return null;
		}
	}
	
}
