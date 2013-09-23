package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.persistencia.PlanoContaDAO;

public class PlanoContaService extends GenericService<PlanoConta>{

	public PlanoContaService() {
		super(PlanoContaDAO.class);
	}

	@Override
	protected PlanoContaDAO getRepository() {
		return (PlanoContaDAO) super.getRepository();
	}
	
	@Override
	public List<PlanoConta> findAtivados() {
		return getRepository().findByAtivoIsTrue();
	}

}
