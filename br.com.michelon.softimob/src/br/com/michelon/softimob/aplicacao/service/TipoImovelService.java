package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.TipoImovel;
import br.com.michelon.softimob.persistencia.TipoImovelDAO;

public class TipoImovelService extends GenericService<TipoImovel>{

	public TipoImovelService() {
		super(TipoImovelDAO.class);
	}
	
}
