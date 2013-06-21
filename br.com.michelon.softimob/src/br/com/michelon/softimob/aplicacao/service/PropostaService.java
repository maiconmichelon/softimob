package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.persistencia.PropostaDAO;

public class PropostaService extends GenericService<Proposta>{

	public PropostaService() {
		super(PropostaDAO.class);
	}
	
}
