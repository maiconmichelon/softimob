package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.TipoImovelTipoComodo;
import br.com.michelon.softimob.persistencia.TipoImovelTipoComodoDAO;

public class TipoImovelTipoComodoService extends GenericService<TipoImovelTipoComodo>{

	public TipoImovelTipoComodoService() {
		super(TipoImovelTipoComodoDAO.class);
	}
	
	@Override
	protected TipoImovelTipoComodoDAO getRepository() {
		return (TipoImovelTipoComodoDAO) super.getRepository();
	}
	
}
