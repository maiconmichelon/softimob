package br.com.michelon.softimob.aplicacao.filter;

import java.util.Map;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.dialogs.FilteredList.FilterMatcher;

import br.com.michelon.softimob.modelo.Comodo;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.TipoComodo;

public class ImovelComodoFilter extends GenericFilter implements FilterMatcher{


	public ImovelComodoFilter(Map<String, TipoComodo> comodos) {
		this.comodos = comodos;
	}

	private final Map<String, TipoComodo> comodos;
	private String searchString;
	private String selection;
	
	public void setSelection(String selection){
		this.selection = selection;
	}
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		Imovel imovel = (Imovel) element;
		
		for(Comodo comodo : imovel.getComodos()){
			if(comodo.getTipoComodo().equals(comodos.get(selection))){
				if(comodo.getDescricao().matches(searchString))
					return true;
			}
		}
		
		return false;
	}

	@Override
	public void setFilter(String pattern, boolean ignoreCase,
			boolean ignoreWildCards) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean match(Object element) {
		// TODO Auto-generated method stub
		return false;
	}

}
