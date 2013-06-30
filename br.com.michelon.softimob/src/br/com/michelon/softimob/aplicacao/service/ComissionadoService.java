package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Comissionado;
import br.com.michelon.softimob.persistencia.ComissionadoDAO;

public class ComissionadoService extends GenericService<Comissionado>{

	public ComissionadoService() {
		super(ComissionadoDAO.class);
	}

}
