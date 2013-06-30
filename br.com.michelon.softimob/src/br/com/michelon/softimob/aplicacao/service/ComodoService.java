package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.Comodo;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.persistencia.ComodoDAO;

public class ComodoService extends GenericService<Comodo>{

	public ComodoService() {
		super(ComodoDAO.class);
	}

	@Override
	protected ComodoDAO getRepository() {
		return (ComodoDAO) super.getRepository();
	}
	
	public List<Comodo> findByImovel(Imovel imovel) {
		return getRepository().findByImovel(imovel);
	}

}
