package br.com.michelon.softimob.aplicacao.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import br.com.michelon.softimob.modelo.ContaPagarReceber;

public class ContaFilter extends ViewerFilter{

	public static final String PAGAR = "A pagar";
	public static final String RECEBER = "A receber";
	public static final String TODAS = "Todas";

	private String status = TODAS;
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(status.equals(TODAS))
			return true;
		
		ContaPagarReceber conta = (ContaPagarReceber) element;
		if(conta.isApagar() && status.equals(PAGAR))
			return true;
		if(conta.isAReceber() && status.equals(RECEBER))
			return true;
		
		return false;
	}

	public void setStatus(String status){
		this.status = status;
	}
	
}
