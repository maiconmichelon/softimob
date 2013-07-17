package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.Comissionado;

public interface ComissionadoDAO extends CrudRepository<Comissionado, Long>{

	@Query("SELECT r FROM Comissionado r WHERE r.ativo = :ativo")
	List<Comissionado> findAtivos(@Param(value="ativo")Boolean ativo);

}
