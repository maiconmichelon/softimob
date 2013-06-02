package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Endereco;
import br.com.michelon.softimob.persistencia.EnderecoDAO;

public class EnderecoService extends GenericService<Endereco>{

	public EnderecoService() {
		super(EnderecoDAO.class);
	}
	
}
