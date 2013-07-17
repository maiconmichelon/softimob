package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.TipoComodo;

public interface TipoComodoDAO extends CrudRepository<TipoComodo, Long>{

	@Query("SELECT r FROM TipoComodo r WHERE r.ativo = :ativo")
	List<TipoComodo> findAtivo(@Param(value = "ativo") Boolean ativo);

}
