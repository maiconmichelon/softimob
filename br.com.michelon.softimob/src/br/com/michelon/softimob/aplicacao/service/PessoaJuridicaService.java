package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.PessoaJuridica;
import br.com.michelon.softimob.persistencia.PessoaJuridicaDAO;

public class PessoaJuridicaService extends GenericService<PessoaJuridica>{

	public PessoaJuridicaService() {
		super(PessoaJuridicaDAO.class);
	}

}
