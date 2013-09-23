package br.com.michelon.softimob.tela.binding.convert;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.conversion.IConverter;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;

public class StringToDateTimeConverter implements IConverter {

	private Logger log = Logger.getLogger(getClass());
	
	@Override
	public Object getFromType() {
		return String.class;
	}

	@Override
	public Object getToType() {
		return Date.class;
	}

	@Override
	public Object convert(Object fromObject) {
		try {
			return FormatterHelper.getSimpleDateFormatHorasMinutos().parse(fromObject.toString());
		} catch (ParseException e) {
			log.error("Erro ao parsear data.", e);
			return null;
		}
	}

}
