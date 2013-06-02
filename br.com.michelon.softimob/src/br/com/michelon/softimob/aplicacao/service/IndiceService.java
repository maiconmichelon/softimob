package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Indice;
import br.com.michelon.softimob.persistencia.IndiceDAO;

public class IndiceService extends GenericService<Indice>{

	public IndiceService() {
		super(IndiceDAO.class);
	}
	
}
