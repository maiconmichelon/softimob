package br.com.michelon.softimob.aplicacao.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Reserva;
import br.com.michelon.softimob.persistencia.ReservaDAO;
import br.com.michelon.softimob.persistencia.ReservaDAOImpl;
import br.com.michelon.softimob.persistencia.SpringUtils;

public class ReservaService extends GenericService<Reserva>{

	private Logger log = Logger.getLogger(getClass());
	
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

	public List<Reserva> findPendencias(Date dataHoje){
		return getRepository().findByResolvidoFalseAndDataVencimentoLessThan(dataHoje);
	}
	
	public List<Reserva> findByDataVencimentoAfter(Date data, Imovel imovel){
		return getRepository().findByDataVencimentoAfterAndImovelEquals(data, imovel);
	}
	
	public List<Reserva> findIntersecao(Reserva reserva){
		return getDaoImpl().findIntersecao(reserva);
	}
	
	private ReservaDAOImpl getDaoImpl(){
		return SpringUtils.getContext().getBean(ReservaDAOImpl.class);
	}
	
	public void finalizarPendencia(Reserva reserva) {
		try {
			reserva.setResolvido(true);
			salvar(reserva);
		} catch (Exception e) {
			log.error("Erro ao finalizar aluguel como pendencia.", e);
		}
	}

	public Long findContPendencias(Date dataHoje) {
		return getRepository().findContPendencias(dataHoje);
	}

}
