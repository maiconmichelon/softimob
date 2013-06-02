package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Proposta;

public interface PropostaDAO extends CrudRepository<Proposta, Long>{

}
