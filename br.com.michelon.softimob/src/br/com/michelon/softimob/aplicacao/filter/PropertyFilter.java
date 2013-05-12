package br.com.michelon.softimob.aplicacao.filter;

import org.eclipse.jface.viewers.Viewer;

import br.com.michelon.softimob.aplicacao.helper.ReflectionHelper;

public class PropertyFilter extends GenericFilter{

	private String[] atributos;
	private String searchText;
	
	public PropertyFilter(String... atributos) {
		this.atributos = atributos;
	}

	public PropertyFilter(Object[] array) {
		atributos = new String[array.length];
		
		for(int i = 0; i < array.length; i++)
			atributos[i] = array[i].toString();
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(searchText == null || searchText.isEmpty())
			return true;
		
		try {
			return ReflectionHelper.compare(element, atributos, searchText.toLowerCase());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
