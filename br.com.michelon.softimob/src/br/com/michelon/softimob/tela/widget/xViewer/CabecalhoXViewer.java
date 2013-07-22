package br.com.michelon.softimob.tela.widget.xViewer;

import java.util.List;

public abstract class CabecalhoXViewer<T> {
	
	private final Class<T> clazz;
	private final List<T> subElements;
	private final String[] columns;

	public CabecalhoXViewer(Class<T> clazz, List<T> subElements, String... columns) {
		this.clazz = clazz;
		this.subElements = subElements;
		this.columns = columns;
	}

	public Class<T> getClazz() {
		return clazz;
	}
	
	public String[] getColumns(){
		return columns;
	}
	
	public Object[] getSubItens(){
		return subElements.toArray();
	}
	
}
