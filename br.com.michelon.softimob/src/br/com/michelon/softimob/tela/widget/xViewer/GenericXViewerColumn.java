package br.com.michelon.softimob.tela.widget.xViewer;

import java.util.Map;

import org.eclipse.nebula.widgets.xviewer.XViewerColumn;

public class GenericXViewerColumn extends XViewerColumn{

	private final Map<Class<?>, String> properties;

	public GenericXViewerColumn(String id, String name, int width, int align, 
			boolean show, SortDataType sortDataType, boolean multiColumnEditable, String description, Map<Class<?>, String> properties) {
		
		super(ID, NAME, width, align, show, sortDataType, multiColumnEditable, description);
		
		this.properties = properties;
	}
	
	public Map<Class<?>, String> getProperties() {
		return properties;
	}
	
}
