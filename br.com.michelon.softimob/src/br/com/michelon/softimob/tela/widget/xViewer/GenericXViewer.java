package br.com.michelon.softimob.tela.widget.xViewer;

import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.swt.widgets.Composite;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

public class GenericXViewer<T> extends XViewer {

	private Map<String, GenericXViewerColumn> cols;
	
	public GenericXViewer(Composite parent, int style, List<GenericXViewerColumn> columns, GenericXViewerContentProvider contentProvider) {
		super(parent, style, new GenericXViewerFactory(columns));

		setLabelProvider(new GenericXViewerLabelProvider(this));
		setContentProvider(contentProvider);
		
		cols = Maps.uniqueIndex(columns, new Function<GenericXViewerColumn, String>() {
			public String apply(GenericXViewerColumn from) {
				return from.getId();
			}
		});
		
		getMenuManager().dispose();
	}

	public GenericXViewerColumn getGenericXViewerColumn(String id) {
		return cols.get(id);
	}
	
}
