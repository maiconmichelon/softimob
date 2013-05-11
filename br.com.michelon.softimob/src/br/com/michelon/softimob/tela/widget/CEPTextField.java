package br.com.michelon.softimob.tela.widget;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.MaskFormatter;
import org.eclipse.swt.widgets.Composite;

public class CEPTextField extends FormattedText{

	public CEPTextField(Composite parent) {
		super(parent);

		setFormatter(new MaskFormatter("#####-###"));
	}

}
