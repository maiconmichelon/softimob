package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.persistencia.PropostaDAO;

public class PropostaService extends GenericService<Proposta>{

	public PropostaService() {
		super(PropostaDAO.class);
	}

	@Override
	protected PropostaDAO getRepository() {
		return (PropostaDAO) super.getRepository();
	}
	
	public List<Proposta> findByImovel(Imovel imovel) {
		return getRepository().findByImovel(imovel);
	}
	
}
