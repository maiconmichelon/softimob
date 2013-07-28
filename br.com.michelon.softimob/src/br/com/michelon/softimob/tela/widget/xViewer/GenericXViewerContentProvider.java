package br.com.michelon.softimob.tela.widget.xViewer;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public abstract class GenericXViewerContentProvider implements ITreeContentProvider{

	@Override
	public void dispose() {}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof List)
			return ((List<?>) inputElement).toArray();
		return null;
	}

	@Override
	public Object[] getChildren(Object inputElement){
		if(inputElement instanceof CabecalhoXViewer<?>)
			return ((CabecalhoXViewer<?>)inputElement).getSubItens();
		return getChildrenElements(inputElement);
	}

	public abstract Object[] getChildrenElements(Object inputElement);

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		Object[] children = getChildren(element);
		return !(children == null || children.length == 0 || (children.length == 1 && children[0] == null));
	}

}
