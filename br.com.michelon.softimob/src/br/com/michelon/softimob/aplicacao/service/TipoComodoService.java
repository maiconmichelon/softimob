package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.TipoComodo;
import br.com.michelon.softimob.modelo.TipoImovel;
import br.com.michelon.softimob.persistencia.TipoComodoDAO;

public class TipoComodoService extends GenericService<TipoComodo>{

	public TipoComodoService() {
		super(TipoComodoDAO.class);
	}

	@Override
	protected TipoComodoDAO getRepository() {
		return (TipoComodoDAO) super.getRepository();
	}

	@Override
	public List<TipoComodo> findAtivados() {
		return getRepository().findAtivo(true);
	}
	
	public List<TipoComodo> findByTipoComodoAndSelecionadoIsTrue(TipoImovel tipoImovel){
		return getRepository().findByTipoImovelAndPreSelecionadoIsTrue(tipoImovel);
	}
	
	public List<TipoComodo> findByTipoImovel(TipoImovel tipoImovel){
		return getRepository().findByTipoImovel(tipoImovel);
	}

}
