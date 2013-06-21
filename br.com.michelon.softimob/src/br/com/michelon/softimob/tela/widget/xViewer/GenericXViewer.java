package br.com.michelon.softimob.tela.widget.xViewer;

import java.util.List;

import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.swt.widgets.Composite;

public class GenericXViewer<T> extends XViewer {

	public GenericXViewer(Composite parent, int style, List<GenericXViewerColumn> columns, GenericXViewerContentProvider contentProvider) {
		super(parent, style, new GenericXViewerFactory(columns));
		
		setLabelProvider(new GenericXViewerLabelProvider(this));
		setContentProvider(contentProvider);
	}

	public GenericXViewer(Composite parent, int style, List<GenericXViewerColumn> columns, 
			GenericXViewerContentProvider contentProvider, GenericXViewerLabelProvider labelProvider) {
		super(parent, style, new GenericXViewerFactory(columns));
		
		setLabelProvider(labelProvider);
		setContentProvider(contentProvider);
	}
	
}
