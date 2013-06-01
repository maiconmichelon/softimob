package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Departamento;
import br.com.michelon.softimob.persistencia.DepartamentoDAO;
import br.com.michelon.softimob.persistencia.SpringUtils;

public class DepartamentoService extends GenericService<Departamento>{

	public DepartamentoService() {
		super(SpringUtils.getContext().getBean(DepartamentoDAO.class));
	}
	
}
