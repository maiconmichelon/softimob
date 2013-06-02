package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Rua;
import br.com.michelon.softimob.persistencia.RuaDAO;

public class RuaService extends GenericService<Rua>{

	public RuaService() {
		super(RuaDAO.class);
	}
	
}
