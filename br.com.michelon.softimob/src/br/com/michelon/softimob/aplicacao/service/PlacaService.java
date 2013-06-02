package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Placa;
import br.com.michelon.softimob.persistencia.PlacaDAO;

public class PlacaService extends GenericService<Placa>{

	public PlacaService() {
		super(PlacaDAO.class);
	}
	
}
