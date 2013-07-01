package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.modelo.Estado;
import br.com.michelon.softimob.persistencia.CidadeDAO;

public class CidadeService extends GenericService<Cidade>{

	public CidadeService() {
		super(CidadeDAO.class);
	}

	public List<Cidade> findCidadesByEstado(Estado estado) {
		return getRepository().findCidadesByEstado(estado);
	}
	
	@Override
	protected CidadeDAO getRepository() {
		return (CidadeDAO) super.getRepository();
	}

	public Cidade findByNome(String nome) {
		return getRepository().findByNome(nome);
	}
	
}
