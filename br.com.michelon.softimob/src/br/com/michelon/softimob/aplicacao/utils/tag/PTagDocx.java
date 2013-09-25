package br.com.michelon.softimob.aplicacao.utils.tag;

import java.math.BigDecimal;
import java.util.Date;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.ReflectionHelper;

public class PTagDocx implements TagDocx{

	@Override
	public String getTagWord() {
		return "p";
	}

	@Override
	public String format(String property, Object obj) throws Exception {
		Object object = ReflectionHelper.getAtribute(obj, property);
		
		if(object instanceof Boolean)
			return (Boolean) object ? "sim" : "não";
		if(object instanceof BigDecimal)
			return FormatterHelper.getDefaultValueFormatterToMoney().format((BigDecimal) object);
		if(object instanceof Date)
			return FormatterHelper.getSimpleDateFormat().format(object);
		
		return object == null ? null : object.toString();
	}

}
