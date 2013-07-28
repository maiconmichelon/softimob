package br.com.michelon.softimob.tela.widget.xViewer;

import java.util.List;

import org.eclipse.swt.graphics.Image;

public abstract class CabecalhoXViewer<T> {
	
	private final Class<T> clazz;
	private final List<T> subElements;
	private final String[] columns;
	private final Image image;
	
//	public CabecalhoXViewer(Class<T> clazz, List<T> subElements, String... columns) {
//		this(clazz, subElements, null, columns);
//	}

	public CabecalhoXViewer(Class<T> clazz, List<T> subElements, Image image, String... columns) {
		this.clazz = clazz;
		this.subElements = subElements;
		this.columns = columns;
		this.image = image;
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
	
	public Image getImage() {
		return image;
	}
	
}
