package br.com.michelon.softimob.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.michelon.softimob.modelo.Reserva;

@Repository
public class ReservaDAOImpl {

	@PersistenceContext
	private EntityManager em;

	public List<Reserva> findIntersecao(Reserva reserva){
		TypedQuery<Reserva> query = em.createQuery("SELECT r FROM Reserva r " +
				"WHERE NOT (r.dataReserva > :dataVencimento OR r.dataVencimento < :data) AND r.imovel = :imovel", Reserva.class);
		
		query.setParameter("data", reserva.getDataReserva());
		query.setParameter("dataVencimento", reserva.getDataVencimento());
		query.setParameter("imovel", reserva.getImovel());
		
		return query.getResultList();
	}
	
}
