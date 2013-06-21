package br.com.michelon.softimob.tela.widget.xViewer;

import java.util.List;

import org.eclipse.nebula.widgets.xviewer.XViewerColumn;
import org.eclipse.nebula.widgets.xviewer.XViewerFactory;

public class GenericXViewerFactory extends XViewerFactory {

	public GenericXViewerFactory(List<GenericXViewerColumn> columns) {
		super("genericXViewer.");

		registerColumns((XViewerColumn[]) columns.toArray());
	}

	@Override
	public boolean isAdmin() {
		return true;
	}

	@Override
	public boolean isLoadedStatusLabelAvailable() {
		return false;
	}

	@Override
	public boolean isFilterUiAvailable() {
		return false;
	}

	@Override
	public boolean isSearchUiAvailable() {
		return false;
	}

}
