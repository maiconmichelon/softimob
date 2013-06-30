package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.Chave;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.persistencia.ChaveDAO;

public class ChaveService extends GenericService<Chave>{

	public ChaveService() {
		super(ChaveDAO.class);
	}

	@Override
	protected ChaveDAO getRepository() {
		return (ChaveDAO) super.getRepository();
	}
	
	public List<Chave> findByImovel(Imovel imovel) {
		return getRepository().findByImovel(imovel);
	}
	
}
