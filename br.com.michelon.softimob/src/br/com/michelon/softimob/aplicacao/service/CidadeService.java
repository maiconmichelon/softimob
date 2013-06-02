package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.persistencia.CidadeDAO;

public class CidadeService extends GenericService<Cidade>{

	public CidadeService() {
		super(CidadeDAO.class);
	}
	
}
