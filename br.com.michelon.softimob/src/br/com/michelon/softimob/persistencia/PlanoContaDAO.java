package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.PlanoConta;

public interface PlanoContaDAO extends CrudRepository<PlanoConta, Long>{

	List<PlanoConta> findByAtivoIsTrue();
	
}
