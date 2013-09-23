package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.TipoImovel;
import br.com.michelon.softimob.persistencia.TipoImovelDAO;

public class TipoImovelService extends GenericService<TipoImovel>{

	public TipoImovelService() {
		super(TipoImovelDAO.class);
	}

	@Override
	protected TipoImovelDAO getRepository() {
		return (TipoImovelDAO) super.getRepository();
	}
	
	@Override
	public List<TipoImovel> findAtivados() {
		return getRepository().findAtivo(true);
	}
	
}
