package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Reserva;
import br.com.michelon.softimob.persistencia.ReservaDAO;

public class ReservaService extends GenericService<Reserva>{

	public ReservaService() {
		super(ReservaDAO.class);
	}

	@Override
	protected ReservaDAO getRepository() {
		return (ReservaDAO) super.getRepository();
	}
	
	public List<Reserva> findByReserva(Imovel imovel) {
		return getRepository().findByImovel(imovel);
	}

}
