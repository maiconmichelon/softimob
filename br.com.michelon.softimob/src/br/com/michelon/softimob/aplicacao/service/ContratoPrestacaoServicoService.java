package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.persistencia.ContratoPrestacaoServicoDAO;


public class ContratoPrestacaoServicoService extends GenericService<ContratoPrestacaoServico>{

	public ContratoPrestacaoServicoService() {
		super(ContratoPrestacaoServicoDAO.class);
	}
	
}
