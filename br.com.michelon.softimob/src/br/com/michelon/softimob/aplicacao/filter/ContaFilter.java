package br.com.michelon.softimob.aplicacao.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.OrigemConta;

public class ContaFilter extends ViewerFilter{

	public static final String PAGAR = "A pagar";
	public static final String RECEBER = "A receber";
	public static final String TODAS = "Todas";

	public static final String VENCIDA = "vencidas";
	public static final String A_VENCER = "a vencer";
	
	
	private String status = TODAS;
	private String vencidaNaoVencida = TODAS;
	public OrigemConta origemConta;
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		ContaPagarReceber conta = (ContaPagarReceber) element;
		if(conta.isAReceber() && status.equals(PAGAR))
			return false;
		if(conta.isApagar() && status.equals(RECEBER))
			return false;
		
		if(conta.isVencida() && vencidaNaoVencida.equals(A_VENCER))
			return false;
		if(!conta.isVencida() && vencidaNaoVencida.equals(VENCIDA))
			return false;
			
		if(origemConta != null && !origemConta.equals(conta.getOrigem()))
			return false;
		
		return true;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public void setVencidaNaoVencida(String vencidaNaoVencida) {
		this.vencidaNaoVencida = vencidaNaoVencida;
	}
	
	public void setOrigemConta(OrigemConta origemConta) {
		this.origemConta = origemConta;
	}
	
}
