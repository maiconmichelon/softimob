package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Feedback;
import br.com.michelon.softimob.modelo.Imovel;

public interface FeedbackDAO extends CrudRepository<Feedback, Long>{

	List<Feedback> findByImovel(Imovel imovel);
	
}
