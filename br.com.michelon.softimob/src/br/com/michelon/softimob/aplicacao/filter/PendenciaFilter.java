package br.com.michelon.softimob.aplicacao.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import br.com.michelon.softimob.aplicacao.helper.DateHelper;
import br.com.michelon.softimob.modelo.Pendencia;
import br.com.michelon.softimob.modelo.Pendencia.TipoPendencia;

public class PendenciaFilter extends ViewerFilter{

	public static String VENCIDA = "Vencida";
	public static String AVENCER = "A vencer";
	public static String TODAS = "Todas";

	private TipoPendencia tipo;
	private String status;
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		Pendencia p = (Pendencia) element;
		
		if(tipo != null && !tipo.getClazz().equals(element.getClass()))
			return false;
		
		if(VENCIDA.equals(status) && !DateHelper.isVencida(p.getDataVencimento()))
			return false;
		if(AVENCER.equals(status) && DateHelper.isVencida(p.getDataVencimento()))
			return false;
		
		return true;
	}
	
	public void setTipo(TipoPendencia tipo) {
		this.tipo = tipo;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
