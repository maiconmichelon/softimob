package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.persistencia.BairroDAO;

public class BairroService extends GenericService<Bairro>{

	public BairroService() {
		super(BairroDAO.class);
	}
	
}
