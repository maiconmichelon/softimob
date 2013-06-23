package br.com.michelon.softimob.aplicacao.helper;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class ReflectionHelper {

	public static Object getAtribute(Object obj, String atributo) throws Exception{
		obj = obj.getClass().getMethod("get"+ StringUtils.capitalize(StringUtils.substringBefore(atributo, "."))).invoke(obj);
		if(atributo.contains("."))
			return getAtribute(obj, StringUtils.substringAfter(atributo, "."));
		return obj;
	}

	public static Object setAtribute(Object obj, String atributo, Object parameter, Class<?> clazz) throws Exception{
		if(clazz == null)
			clazz = parameter.getClass();
		
		String nomeMetodo = "set"+ StringUtils.capitalize(StringUtils.substringBefore(atributo, "."));
		return obj.getClass().getMethod(nomeMetodo, clazz).invoke(obj, parameter);
	}
	
	public static <T> T newInstance(Class<T> c) throws Exception{
		return c.newInstance();
	}
	
	public static boolean compare(Object obj, String[] atributos, String palavra) throws Exception{
		if(palavra == null || palavra.isEmpty())
			return true;
		
		for (String atributo : atributos) {
			Object dado = getAtribute(obj, atributo);
			
			if(dado instanceof Date) 
				dado = FormatterHelper.getSimpleDateFormat().format(dado);
			if(!(dado instanceof String))
				dado = dado.toString();
			if(((String) dado).toLowerCase().matches(palavra))
				return true;
		}
		return false;
	}
	
	

}
