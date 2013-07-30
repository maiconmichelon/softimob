package br.com.michelon.softimob.tela.widget;

import java.text.ParseException;
import java.util.Date;

import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;

public class DateTextField extends DateChooserCombo{

	public DateTextField(Composite parent) {
		super(parent, SWT.BORDER);
	}

	public Date getValue() {
		try {
			return FormatterHelper.getSimpleDateFormat().parse(getControl().getText());
		} catch (ParseException e) {
			return null;
		}
	}

	public Text getControl() {
		return text;
	}

}
