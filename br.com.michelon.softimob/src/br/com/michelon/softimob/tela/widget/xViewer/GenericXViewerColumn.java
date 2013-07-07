package br.com.michelon.softimob.tela.widget.xViewer;

import java.util.Map;

import org.eclipse.nebula.widgets.xviewer.XViewerColumn;

public class GenericXViewerColumn extends XViewerColumn{

	private final Map<Class<?>, XViewerColumnProperties> properties;

	public GenericXViewerColumn(String description, int width, Map<Class<?>, XViewerColumnProperties> properties) {
		super(description.toLowerCase(), description, width, 1, true, SortDataType.String, true, description);
		
		this.properties = properties;
	}
	
	public Map<Class<?>, XViewerColumnProperties> getProperties() {
		return properties;
	}
	
}
