package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.persistencia.FuncionarioDAO;

public class FuncionarioService extends GenericService<Funcionario>{

	public FuncionarioService() {
		super(FuncionarioDAO.class);
	}
	
}
