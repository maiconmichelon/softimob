package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.VendaAluguel;
import br.com.michelon.softimob.persistencia.VendaAluguelDAO;

public class VendaAluguelService extends GenericService<VendaAluguel>{

	public VendaAluguelService() {
		super(VendaAluguelDAO.class);
	}
	
	protected VendaAluguelDAO getRepository() {
		return (VendaAluguelDAO) super.getRepository();
	}
	
	public List<VendaAluguel> findByContrato(ContratoPrestacaoServico contrato){
		return getRepository().findByContrato(contrato);
	}
	
}
