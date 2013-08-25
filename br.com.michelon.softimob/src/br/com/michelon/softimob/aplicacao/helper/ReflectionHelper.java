package br.com.michelon.softimob.aplicacao.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.com.michelon.softimob.aplicacao.annotation.DeactivateOnDelete;
import br.com.michelon.softimob.aplicacao.annotation.Log;

import com.google.common.collect.Lists;

public class ReflectionHelper {

	private static Logger log = Logger.getLogger(ReflectionHelper.class);
	
	public static Object getAtribute(Object obj, String atributo) throws Exception {
		try {
			obj = obj.getClass().getMethod("get"+ StringUtils.capitalize(StringUtils.substringBefore(atributo, "."))).invoke(obj);
		} catch (Exception e) {
			log.error(String.format("Erro ao usar pegar atributo %s da classe %s", atributo, obj.getClass().getName()), e);
			throw e;
		}
		
		if(obj == null)
			return null;
		
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
			
			if(dado == null)
				return false;
			if(dado instanceof Date) 
				dado = FormatterHelper.getSimpleDateFormat().format(dado);
			if(!(dado instanceof String))
				dado = dado.toString();
			if(((String) dado).toLowerCase().matches(palavra))
				return true;
		}
		
		return false;
	}
	
	public static String getAtributoID(Object obj){
		Class<?> clazz = obj.getClass();
		do{
			String atributoID = getAtributoID(clazz);
			if(atributoID != null)
				return atributoID;
			clazz = clazz.getSuperclass(); 
		}while(!clazz.equals(Object.class));
		
		return null;
	}

	private static String getAtributoID(Class<?> clazz){
		for(Field f : clazz.getDeclaredFields()){
			for(Annotation a : f.getDeclaredAnnotations()){
				if(a.annotationType().equals(Id.class))
					return f.getName();
			}
		}
		
		return null;
	}
	
	public static List<Field> getAtributoAtivoDesativado(Class<?> clazz){
		return getFieldByAnnotation(clazz, DeactivateOnDelete.class);
	}
	
	public static List<Field> getAtributoLog(Class<?> clazz){
		return getFieldByAnnotation(clazz, Log.class);
	}
	
	public static List<Field> getFieldByAnnotation(Class<?> clazz, Class<? extends Annotation> clazzAnnotation){
		List<Field> fields = Lists.newArrayList();
		
		for(Field f : clazz.getDeclaredFields()){
			if(f.getAnnotation(clazzAnnotation) != null)
				fields.add(f);
		}
		
		if(!clazz.getSuperclass().equals(Object.class))
			fields.addAll(getAtributoAtivoDesativado(clazz.getSuperclass()));
		
		return fields;
	}
	
}
