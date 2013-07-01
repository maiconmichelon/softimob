package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.AcontecimentoChamado;
import br.com.michelon.softimob.modelo.ChamadoReforma;
import br.com.michelon.softimob.persistencia.AcontecimentoChamadoDAO;

public class AcontecimentoChamadoService extends GenericService<AcontecimentoChamado>{

	public AcontecimentoChamadoService() {
		super(AcontecimentoChamadoDAO.class);
	}

	@Override
	protected AcontecimentoChamadoDAO getRepository() {
		return (AcontecimentoChamadoDAO) super.getRepository();
	}
	
	public List<AcontecimentoChamado> findByChamadoReforma(ChamadoReforma chamadoReforma) {
		return getRepository().findByChamadoReforma(chamadoReforma);
	}
	
}
