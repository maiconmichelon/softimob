package br.com.michelon.softimob.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.michelon.softimob.modelo.ContaPagarReceber;

@Repository
public class ContaPagarReceberDAOImpl {

	@PersistenceContext
	private EntityManager em;
	
	public void estornar(List<ContaPagarReceber> contasToDelete, List<ContaPagarReceber> contasToUpdate){
		if(contasToDelete != null)
			for (ContaPagarReceber c : contasToDelete)
				em.remove(c);
		
		if(contasToUpdate != null)
			for(ContaPagarReceber c : contasToUpdate)
				em.merge(c);
		
	}
	
}
