package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.modelo.Rua;
import br.com.michelon.softimob.persistencia.RuaDAO;

public class RuaService extends GenericService<Rua>{

	public RuaService() {
		super(RuaDAO.class);
	}
	
	@Override
	protected RuaDAO getRepository() {
		return (RuaDAO) super.getRepository();
	}

	public List<Rua> findRuasByBairro(Bairro bairro) {
		return getRepository().findRuaByBairro(bairro);
	}

	public Rua findByNome(String nome) {
		return getRepository().findByNome(nome);
	}
	
}
