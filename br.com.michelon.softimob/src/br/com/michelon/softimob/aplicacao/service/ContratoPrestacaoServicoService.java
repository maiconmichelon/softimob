package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.persistencia.ContratoPrestacaoServicoDAO;


public class ContratoPrestacaoServicoService extends GenericService<ContratoPrestacaoServicoService>{

	public ContratoPrestacaoServicoService() {
		super(ContratoPrestacaoServicoDAO.class);
	}
	
}
