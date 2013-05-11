package br.com.michelon.softimob.tela.widget;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.NumberFormatter;
import org.eclipse.swt.widgets.Composite;

public class CPFTextField extends FormattedText{

	public CPFTextField(Composite parent) {
		super(parent);
		
		setFormatter(new NumberFormatter("###.###.###-##"));
	}
	
}
