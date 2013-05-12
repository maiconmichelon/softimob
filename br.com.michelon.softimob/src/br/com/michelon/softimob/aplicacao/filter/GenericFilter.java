package br.com.michelon.softimob.aplicacao.filter;

import org.eclipse.jface.viewers.ViewerFilter;

public abstract class GenericFilter extends ViewerFilter {

	protected String searchText;

	public void setSearchText(String searchText){
		this.searchText = ".*" + searchText + ".*";
	}
	
}
