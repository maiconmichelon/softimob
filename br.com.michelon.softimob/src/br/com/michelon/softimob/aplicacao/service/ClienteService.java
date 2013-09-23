package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.persistencia.ClienteDAO;

public class ClienteService extends GenericService<Cliente>{

	public ClienteService() {
		super(ClienteDAO.class);
	}

	@Override
	protected ClienteDAO getRepository() {
		return (ClienteDAO) super.getRepository();
	}
	
	@Override
	public List<Cliente> findAtivados() {
		return getRepository().findByAtivoTrue();
	}
	
}
