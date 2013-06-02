package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.persistencia.AluguelDAO;

public class AluguelService extends GenericService<Aluguel>{

	public AluguelService() {
		super(AluguelDAO.class);
	}
	
}
