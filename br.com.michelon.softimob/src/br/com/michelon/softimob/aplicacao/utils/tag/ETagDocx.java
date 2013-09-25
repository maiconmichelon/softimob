package br.com.michelon.softimob.aplicacao.utils.tag;

import java.math.BigDecimal;
import java.util.Date;

import br.com.caelum.stella.inwords.FormatoDeReal;
import br.com.caelum.stella.inwords.NumericToWordsConverter;
import br.com.michelon.softimob.aplicacao.helper.DateHelper;
import br.com.michelon.softimob.aplicacao.helper.ReflectionHelper;

public class ETagDocx implements TagDocx{

	@Override
	public String getTagWord() {
		return "e";
	}

	@Override
	public String format(String property, Object obj) throws Exception {
		Object atribute = ReflectionHelper.getAtribute(obj, property);
		
		if(atribute instanceof BigDecimal){
			BigDecimal valor = (BigDecimal) atribute;
			NumericToWordsConverter converter = new NumericToWordsConverter(new FormatoDeReal());
			return converter.toWords(valor.doubleValue());
		} else if (atribute instanceof Boolean){
			return (Boolean) atribute ? "sim" : "não";
		} else if (atribute instanceof Date){
			return DateHelper.getDataExtenso((Date) atribute);
		}
		
		return atribute.toString();
	}
	
}
