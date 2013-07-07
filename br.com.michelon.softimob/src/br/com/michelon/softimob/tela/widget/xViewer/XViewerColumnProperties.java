package br.com.michelon.softimob.tela.widget.xViewer;

import org.eclipse.swt.graphics.Image;

public class XViewerColumnProperties {

	private final String property;
	private final Image image;
	private final boolean isEditable;

	public XViewerColumnProperties(String property, Image image, boolean isEditable) {
		this.property = property;
		this.image = image;
		this.isEditable = isEditable;
	}

	public XViewerColumnProperties(String property){
		this(property, null, false);
	}

	public String getProperty() {
		return property;
	}

	public Image getImage() {
		return image;
	}

	public boolean isEditable() {
		return isEditable;
	}

}
