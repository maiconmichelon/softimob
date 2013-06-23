package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Comodo;
import br.com.michelon.softimob.persistencia.ComodoDAO;

public class ComodoService extends GenericService<Comodo>{

	public ComodoService() {
		super(ComodoDAO.class);
	}

}
