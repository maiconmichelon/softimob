package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.TipoComodo;
import br.com.michelon.softimob.persistencia.TipoComodoDAO;

public class TipoComodoService extends GenericService<TipoComodo>{

	public TipoComodoService() {
		super(TipoComodoDAO.class);
	}
	
}
