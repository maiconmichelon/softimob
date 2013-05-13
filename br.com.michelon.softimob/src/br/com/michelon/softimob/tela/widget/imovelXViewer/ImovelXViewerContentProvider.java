package br.com.michelon.softimob.tela.widget.imovelXViewer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import br.com.michelon.softimob.modelo.Imovel;

public class ImovelXViewerContentProvider implements ITreeContentProvider{

	private final static Object[] EMPTY_ARRAY = new Object[0];
	protected Collection<Imovel> rootSet = new HashSet<Imovel>();
	
	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Object inputElement) {
		List<Imovel> imoveis = (List<Imovel>) inputElement;
		return imoveis.toArray(new Imovel[imoveis.size()]);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Object[]) {
			return (Object[]) parentElement;
		}
		if (parentElement instanceof Collection) {
			return ((Collection) parentElement).toArray();
		}
		if(parentElement instanceof Imovel){
			Imovel imovel = (Imovel) parentElement;
			List<Object> listas = new ArrayList<Object>();

			if(imovel.getChaves().size() > 0)
				listas.add(imovel.getChaves());
			if(imovel.getFeedbacks().size() > 0)
				listas.add(imovel.getFeedbacks());
			if(imovel.getComodos().size() > 0)
				listas.add(imovel.getComodos());
			
			return listas.toArray();
		}
     return EMPTY_ARRAY;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Imovel) {
	         return ((Imovel) element).getChaves().size() > 0;
	    }
		if(element instanceof ArrayList){
			int size = ((ArrayList<?>) element).size();
			return size > 0 ? true : false;
		}
	    return false;
	}
	
	public Collection<Imovel> getRootSet() {
		return rootSet;
	}

}
