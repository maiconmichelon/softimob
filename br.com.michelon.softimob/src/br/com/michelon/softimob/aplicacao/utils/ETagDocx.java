package br.com.michelon.softimob.aplicacao.utils;

import java.math.BigDecimal;

import br.com.caelum.stella.inwords.FormatoDeReal;
import br.com.caelum.stella.inwords.NumericToWordsConverter;
import br.com.michelon.softimob.aplicacao.helper.ReflectionHelper;

public class ETagDocx implements TagDocx{

	@Override
	public String getTagWord() {
		return "e";
	}

	@Override
	public String format(String property, Object obj) {
		Object atribute = ReflectionHelper.getAtribute(obj, property);
		if(atribute instanceof BigDecimal){
			BigDecimal valor = (BigDecimal) atribute;
			NumericToWordsConverter converter = new NumericToWordsConverter(new FormatoDeReal());
			return converter.toWords(valor.doubleValue());
		}
		return atribute.toString();
	}
	
}
