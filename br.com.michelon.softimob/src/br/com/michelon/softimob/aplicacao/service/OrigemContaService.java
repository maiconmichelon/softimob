package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.OrigemConta;
import br.com.michelon.softimob.persistencia.OrigemContaDAO;

public class OrigemContaService extends GenericService<OrigemConta>{

	public OrigemContaService() {
		super(OrigemContaDAO.class);
	}
	
}
