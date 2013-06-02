package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.persistencia.ClienteDAO;

public class ClienteService extends GenericService<Cliente>{

	public ClienteService() {
		super(ClienteDAO.class);
	}
	
}
