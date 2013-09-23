package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.PessoaFisica;
import br.com.michelon.softimob.persistencia.PessoaFisicaDAO;

public class PessoaFisicaService extends GenericService<PessoaFisica>{

	public PessoaFisicaService() {
		super(PessoaFisicaDAO.class);
	}

	@Override
	protected PessoaFisicaDAO getRepository() {
		return (PessoaFisicaDAO) super.getRepository();
	}
	
	@Override
	public List<PessoaFisica> findAtivados() {
		return getRepository().findAtivos(true);
	}

}
