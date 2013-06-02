package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Chave;
import br.com.michelon.softimob.persistencia.ChaveDAO;

public class ChaveService extends GenericService<Chave>{

	public ChaveService() {
		super(ChaveDAO.class);
	}
	
}
