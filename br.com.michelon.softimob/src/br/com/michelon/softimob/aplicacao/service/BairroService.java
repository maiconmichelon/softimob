package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.persistencia.BairroDAO;

public class BairroService extends GenericService<Bairro>{

	public BairroService() {
		super(BairroDAO.class);
	}

	public List<Bairro> findByCidade(Cidade cidade) {
		return getRepository().findBairroByCidade(cidade);
	}
	
	@Override
	protected BairroDAO getRepository() {
		return (BairroDAO) super.getRepository();
	}
	
}
