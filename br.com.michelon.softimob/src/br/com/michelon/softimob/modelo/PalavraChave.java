package br.com.michelon.softimob.modelo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import br.com.michelon.softimob.aplicacao.annotation.WildCard;
import br.com.michelon.softimob.aplicacao.helper.ReflectionHelper;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class PalavraChave {
	
	private final String nome;
	private Class<?> clazz;

	public PalavraChave(String nome) {
		this.nome = nome;
	}
	
	public PalavraChave(String nome, Class<?> clazz) {
		this.nome = nome;
		this.clazz = clazz;
	}
	
	public String getNome(){
		return nome;
	}

	public Class<?> getClazz() {
		return clazz;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	public static List<PalavraChave> getPalavrasChave(Class<?> clazz){
		List<Field> fields = ReflectionHelper.getAllPersistenceFields(clazz);
		List<Method> methods = ReflectionHelper.getMethodsByAnnotation(clazz, WildCard.class);
		
		ArrayList<PalavraChave> fieldsFiltrados = Lists.newArrayList(Iterables.transform(fields, new Function<Field, PalavraChave>() {
			@Override
			public PalavraChave apply(Field arg0) {
				return new PalavraChave(arg0.getName(), arg0.getType().isAnnotationPresent(Entity.class) ? arg0.getType() : null);
			}
		}));
		fieldsFiltrados.addAll(Lists.newArrayList(Iterables.transform(methods, new Function<Method, PalavraChave>() {
			@Override
			public PalavraChave apply(Method arg0) {
				return new PalavraChave(arg0.getName().replaceAll("get", ""), arg0.getReturnType());
			}
		})));
		return fieldsFiltrados;
	}
	
}
