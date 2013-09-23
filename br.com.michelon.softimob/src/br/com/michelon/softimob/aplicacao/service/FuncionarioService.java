package br.com.michelon.softimob.aplicacao.service;

import java.util.Calendar;
import java.util.Date;
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
	
	public List<Funcionario> findAniversariantes(Date data){
		Calendar c = Calendar.getInstance();
		return getRepository().findAniversariantes(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1);
	}
	
	public List<Funcionario> findAniversariantes(){
		return findAniversariantes(Calendar.getInstance().getTime());
	}
	
	public List<Funcionario> findByNome(String nome){
		return getRepository().findByNome(nome);
	}
	
	@Override
	public List<Funcionario> findAtivados() {
		return getRepository().findByAtivoIsTrue();
	}
	
}
