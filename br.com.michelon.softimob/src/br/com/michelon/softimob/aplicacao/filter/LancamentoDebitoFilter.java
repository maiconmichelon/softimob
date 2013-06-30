package br.com.michelon.softimob.aplicacao.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import br.com.michelon.softimob.modelo.LancamentoContabil;

public class LancamentoDebitoFilter extends ViewerFilter{

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return ((LancamentoContabil)element).isDebito();
	}

}
