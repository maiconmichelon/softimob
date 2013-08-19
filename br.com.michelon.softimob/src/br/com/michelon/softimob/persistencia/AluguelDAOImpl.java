package br.com.michelon.softimob.persistencia;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.michelon.softimob.modelo.Aluguel;

@Repository
public class AluguelDAOImpl {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Aluguel> findByDataVencimento(Date dataVencimento) {
		Calendar c = Calendar.getInstance();
		c.setTime(dataVencimento);
		
		int dia = c.get(Calendar.DAY_OF_MONTH);
		int mes = c.get(Calendar.MONTH);
		int ano = c.get(Calendar.YEAR);
		
		Query query = em.createNativeQuery("SELECT a.*, v.*, 1 as class_ " +
				"FROM Aluguel a " +
					"JOIN VendaAluguel v on v.id = a.id " +
				"WHERE (v.dataAssinaturaContrato + interval '1 months' * a.duracao) <= '"+dia+"-"+mes+"-"+ano+"' ", Aluguel.class);
	
		return query.getResultList();
	}

}
