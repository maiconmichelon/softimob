package br.com.michelon.softimob.tela.binding.convert;

import org.eclipse.core.databinding.conversion.Converter;

import br.com.michelon.softimob.aplicacao.helper.NumberHelper;

public class ExtractNumbersConverter extends Converter{

	public ExtractNumbersConverter() {
		super(String.class, String.class);
	}

	@Override
	public Object convert(Object fromObject) {
		return NumberHelper.extractNumbers((String) fromObject);
	}

}
