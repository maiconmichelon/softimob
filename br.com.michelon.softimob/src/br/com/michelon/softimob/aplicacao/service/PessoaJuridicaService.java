package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.PessoaJuridica;
import br.com.michelon.softimob.persistencia.PessoaJuridicaDAO;

public class PessoaJuridicaService extends GenericService<PessoaJuridica>{

	public PessoaJuridicaService() {
		super(PessoaJuridicaDAO.class);
	}

	@Override
	protected PessoaJuridicaDAO getRepository() {
		return (PessoaJuridicaDAO) super.getRepository();
	}
	
	public PessoaJuridica findByCnpj(String cnpj) {
		return getRepository().findByCnpj(cnpj);
	}

	public PessoaJuridica findByInscricaoEstadual(String inscrisaoEstadual) {
		return getRepository().findByInscrisaoEstadual(inscrisaoEstadual);
	}

}
