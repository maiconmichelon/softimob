package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Comissao;
import br.com.michelon.softimob.persistencia.ComissaoDAO;

public class ComissaoService extends GenericService<Comissao>{

	public ComissaoService() {
		super(ComissaoDAO.class);
	}
	
}
