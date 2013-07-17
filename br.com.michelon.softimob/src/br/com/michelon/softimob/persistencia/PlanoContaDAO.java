package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.PlanoConta;

public interface PlanoContaDAO extends CrudRepository<PlanoConta, Long>{

	@Query("SELECT r FROM Funcionario r WHERE r.ativo = :ativo")
	List<PlanoConta> findAtivo(@Param(value="ativo")Boolean ativo);

	
}
