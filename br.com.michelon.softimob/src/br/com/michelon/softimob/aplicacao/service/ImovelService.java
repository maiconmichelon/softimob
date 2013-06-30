package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.persistencia.ImovelDAO;

public class ImovelService extends GenericService<Imovel>{

	public ImovelService() {
		super(ImovelDAO.class);
	}

}
