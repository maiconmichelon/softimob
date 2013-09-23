package br.com.michelon.softimob.tela.widget;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.MaskFormatter;
import org.eclipse.swt.widgets.Composite;

public class PhoneTextField extends FormattedText{

	public static final String FORMATTER_TELEFONE = "(##)####-####";

	public PhoneTextField(Composite parent) {
		super(parent);

		setFormatter(new MaskFormatter(FORMATTER_TELEFONE));
	}

}
