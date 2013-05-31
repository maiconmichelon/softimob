package br.com.michelon.softimob.tela.binding.convert;

import java.math.BigDecimal;

import org.eclipse.core.databinding.conversion.Converter;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.NumberHelper;

public class StringToBigDecimalConverter extends Converter{

	public StringToBigDecimalConverter(Object fromType, Object toType) {
		super(String.class, BigDecimal.class);
	}

	@Override
	public Object convert(Object fromObject) {
		String str = (String) fromObject;
		if(str == null || str.isEmpty())
			return null;
		
		str = NumberHelper.extractNumbers(str);
		
		if(str.contains(","))
			str = str.replace(',', '.');

		return fromObject == null ? null : FormatterHelper.getCurrencyFormatter().parse(str);
	}

}
