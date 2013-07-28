package br.com.michelon.softimob.tela.widget;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.NumberFormatter;
import org.eclipse.swt.widgets.Composite;

public class MoneyTextField extends FormattedText{

	/**
	 * @wbp.parser.entryPoint
	 */
	public MoneyTextField(Composite parent) {
		super(parent);
		
		this.setFormatter(new NumberFormatter("###,###,##0.00"));
	}
}
