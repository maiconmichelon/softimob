package br.com.michelon.softimob.tela.widget.xViewer;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public abstract class GenericXViewerContentProvider implements ITreeContentProvider{

	@Override
	public void dispose() {}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}

	@Override
	public abstract Object[] getElements(Object inputElement);

	@Override
	public abstract Object[] getChildren(Object parentElement);

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element) != null || (getChildren(element) instanceof Object[] && getChildren(element).length > 0);
	}

}
