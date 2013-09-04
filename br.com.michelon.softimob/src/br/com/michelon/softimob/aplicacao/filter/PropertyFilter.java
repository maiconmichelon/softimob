package br.com.michelon.softimob.aplicacao.filter;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.Viewer;

import br.com.michelon.softimob.aplicacao.helper.ReflectionHelper;

public class PropertyFilter extends GenericFilter{

	private Logger log = Logger.getLogger(getClass());
	
	private String[] atributos;
	private Class<?> mainClass;
	
	public PropertyFilter(String... atributos) {
		this(atributos, null);
	}

	public PropertyFilter(Object[] array) {
		this(array, null);
	}

	public PropertyFilter(List<String> lista){
		this(lista.toArray());
	}
	
	public PropertyFilter(Object[] array, Class<?> mainClass) {
		this.mainClass = mainClass;
		atributos = new String[array.length];
		
		for(int i = 0; i < array.length; i++)
			atributos[i] = array[i].toString();
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(searchText == null || searchText.isEmpty())
			return true;
		
		try {
			if(mainClass != null && !mainClass.isInstance(element))
				return true;
				
			return ReflectionHelper.compare(element, atributos, searchText.toLowerCase());
		} catch (Exception e) {
			log.error("Erro ao filtrar elementos.", e);
			return false;
		}
	}

}
