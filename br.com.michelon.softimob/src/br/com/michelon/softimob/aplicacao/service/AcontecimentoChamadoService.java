package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.AcontecimentoChamado;
import br.com.michelon.softimob.persistencia.AcontecimentoChamadoDAO;

public class AcontecimentoChamadoService extends GenericService<AcontecimentoChamado>{

	public AcontecimentoChamadoService() {
		super(AcontecimentoChamadoDAO.class);
	}

	@Override
	protected AcontecimentoChamadoDAO getRepository() {
		return (AcontecimentoChamadoDAO) super.getRepository();
	}
	
}
