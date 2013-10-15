package br.com.michelon.softimob.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico.TipoContrato;

@Repository
public class ContratoPrestacaoServicoDAOImpl {

	@PersistenceContext
	private EntityManager em;
	
	public List<ContratoPrestacaoServico> findByTipo(TipoContrato tipo){
		TypedQuery<ContratoPrestacaoServico> query = em.createQuery("SELECT c FROM ContratoPrestacaoServico c LEFT JOIN VendaAluguel va on va.contrato = c " +
				"WHERE va.id is null AND c.tipo = :tipo1 OR c.tipo =:tipo2", ContratoPrestacaoServico.class);
		query.setParameter("tipo1", TipoContrato.LOCACAOVENDA);
		query.setParameter("tipo2", tipo);
		return query.getResultList();
	}
	
}
