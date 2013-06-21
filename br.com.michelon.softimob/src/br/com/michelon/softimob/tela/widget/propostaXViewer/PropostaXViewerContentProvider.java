package br.com.michelon.softimob.tela.widget.propostaXViewer;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import br.com.michelon.softimob.modelo.Proposta;

public class PropostaXViewerContentProvider implements ITreeContentProvider{

	private final static Object[] EMPTY_ARRAY = new Object[0];
	protected Collection<Proposta> rootSet = new HashSet<Proposta>();
	
	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Object inputElement) {
		List<Proposta> propostas = (List<Proposta>) inputElement;
		return propostas.toArray(new Proposta[propostas.size()]);
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
		if(parentElement instanceof Proposta){
			Proposta proposta = (Proposta) parentElement;
			return new Object[]{proposta.getContraProposta()};
		}
     return EMPTY_ARRAY;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Proposta) {
	         return ((Proposta)element).getContraProposta() != null;
	    }
	    return false;
	}
	
	public Collection<Proposta> getRootSet() {
		return rootSet;
	}

}
