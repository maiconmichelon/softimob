package br.com.michelon.softimob.tela.widget;

import org.eclipse.nebula.widgets.formattedtext.MaskFormatter;

import br.com.michelon.softimob.aplicacao.helper.NumberHelper;
import de.ralfebert.rcputils.properties.IValueFormatter;

public class TelefoneValueFormatter implements IValueFormatter<String, String> {

	private MaskFormatter f = new MaskFormatter(PhoneTextField.FORMATTER_TELEFONE);

	@Override
	public String format(String arg0) {
		f.setValue(arg0 != null && arg0.isEmpty() ? null : arg0);
		return f.getDisplayString();
	}

	@Override
	public String parse(String arg0) {
		return NumberHelper.extractNumbers(arg0);
	}

}
