package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.TipoComodo;
import br.com.michelon.softimob.persistencia.TipoComodoDAO;

public class TipoComodoService extends GenericService<TipoComodo>{

	public TipoComodoService() {
		super(TipoComodoDAO.class);
	}

	@Override
	protected TipoComodoDAO getRepository() {
		return (TipoComodoDAO) super.getRepository();
	}

	public List<TipoComodo> findAtivos() {
		return getRepository().findAtivo(true);
	}
	
}
