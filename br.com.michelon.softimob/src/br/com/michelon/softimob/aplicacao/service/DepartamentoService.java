package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.Departamento;
import br.com.michelon.softimob.persistencia.DepartamentoDAO;

public class DepartamentoService extends GenericService<Departamento>{

	public DepartamentoService() {
		super(DepartamentoDAO.class);
	}
	
	@Override
	protected DepartamentoDAO getRepository() {
		return (DepartamentoDAO) super.getRepository();
	}
	
	@Override
	public List<Departamento> findAll() {
		return super.findAll();
	}
	
	@Override
	public List<Departamento> findAtivados() {
		return getRepository().findByAtivoIsTrue();
	}
	
}
