package br.com.michelon.softimob.tela.binding.convert;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.databinding.conversion.Converter;

public class BigDecimalToStringConverter extends Converter{

	private DecimalFormat formatter;
	
	public BigDecimalToStringConverter() {
		super(BigDecimal.class, String.class);
		
		formatter = new DecimalFormat();
	}

	@Override
	public Object convert(Object fromObject) {
		return fromObject == null ? StringUtils.EMPTY : formatter.format(fromObject);
	}

}
