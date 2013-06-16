package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.PessoaFisica;
import br.com.michelon.softimob.persistencia.PessoaFisicaDAO;

public class PessoaFisicaService extends GenericService<PessoaFisica>{

	public PessoaFisicaService() {
		super(PessoaFisicaDAO.class);
	}

}
