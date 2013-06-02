package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Departamento;
import br.com.michelon.softimob.persistencia.DepartamentoDAO;

public class DepartamentoService extends GenericService<Departamento>{

	public DepartamentoService() {
		super(DepartamentoDAO.class);
	}
	
}
