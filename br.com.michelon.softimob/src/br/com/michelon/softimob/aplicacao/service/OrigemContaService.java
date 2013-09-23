package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.OrigemConta;
import br.com.michelon.softimob.persistencia.OrigemContaDAO;

public class OrigemContaService extends GenericService<OrigemConta>{

	public OrigemContaService() {
		super(OrigemContaDAO.class);
	}

	@Override
	protected OrigemContaDAO getRepository() {
		return (OrigemContaDAO) super.getRepository();
	}
	
	@Override
	public List<OrigemConta> findAtivados() {
		return getRepository().findAtivos(true);
	}
	
}
