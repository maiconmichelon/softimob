package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.Endereco;
import br.com.michelon.softimob.persistencia.EnderecoDAO;

public class EnderecoService extends GenericService<Endereco>{

	public EnderecoService() {
		super(EnderecoDAO.class);
	}

	@Override
	public EnderecoDAO getRepository() {
		return (EnderecoDAO) super.getRepository();
	}
	
	public List<Endereco> findByCep(String cep) {
		return getRepository().findByCep(cep);
	}
	
}
