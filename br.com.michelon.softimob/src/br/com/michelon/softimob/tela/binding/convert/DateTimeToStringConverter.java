package br.com.michelon.softimob.tela.binding.convert;

import java.util.Date;

import org.eclipse.core.databinding.conversion.IConverter;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;

public class DateTimeToStringConverter implements IConverter {

	@Override
	public Object getFromType() {
		return Date.class;
	}

	@Override
	public Object getToType() {
		return String.class;
	}

	@Override
	public Object convert(Object fromObject) {
		return FormatterHelper.getSimpleDateFormatHorasMinutos().format(fromObject);
	}

}
