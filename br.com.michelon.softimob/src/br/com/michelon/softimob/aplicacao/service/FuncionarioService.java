package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.persistencia.FuncionarioDAO;

public class FuncionarioService extends GenericService<Funcionario>{

	public FuncionarioService() {
		super(FuncionarioDAO.class);
	}

	@Override
	protected FuncionarioDAO getRepository() {
		return (FuncionarioDAO) super.getRepository();
	}
	
	public List<Funcionario> findAtivos() {
		return getRepository().findAtivos(true);
	}
	
}
