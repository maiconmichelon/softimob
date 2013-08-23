package br.com.michelon.softimob.aplicacao.utils.tag;

import br.com.michelon.softimob.aplicacao.helper.ReflectionHelper;

public class PTagDocx implements TagDocx{

	@Override
	public String getTagWord() {
		return "p";
	}

	@Override
	public String format(String property, Object obj) throws Exception {
		Object object = ReflectionHelper.getAtribute(obj, property);
		return object == null ? null : object.toString();
	}

}
