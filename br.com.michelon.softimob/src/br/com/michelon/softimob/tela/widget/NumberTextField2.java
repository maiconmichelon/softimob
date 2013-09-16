package br.com.michelon.softimob.tela.widget;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.NumberFormatter;
import org.eclipse.swt.widgets.Composite;

public class NumberTextField2 extends FormattedText{

	public NumberTextField2(Composite parent) {
		super(parent);
		this.setFormatter(new NumberFormatter("######.##"));
	}
	
}
