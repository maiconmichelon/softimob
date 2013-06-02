package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Feedback;

public interface FeedbackDAO extends CrudRepository<Feedback, Long>{

}
