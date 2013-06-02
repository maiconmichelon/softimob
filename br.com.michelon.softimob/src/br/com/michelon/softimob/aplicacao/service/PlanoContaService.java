package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.persistencia.PlanoContaDAO;

public class PlanoContaService extends GenericService<PlanoConta>{

	public PlanoContaService() {
		super(PlanoContaDAO.class);
	}

}
