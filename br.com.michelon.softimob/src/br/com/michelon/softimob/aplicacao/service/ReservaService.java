package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Reserva;
import br.com.michelon.softimob.persistencia.ReservaDAO;

public class ReservaService extends GenericService<Reserva>{

	public ReservaService() {
		super(ReservaDAO.class);
	}

}
