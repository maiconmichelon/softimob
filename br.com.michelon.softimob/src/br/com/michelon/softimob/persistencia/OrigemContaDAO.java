package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.OrigemConta;

public interface OrigemContaDAO extends CrudRepository<OrigemConta, Long>{

	@Query("SELECT r FROM OrigemConta r WHERE r.ativo = :ativo")
	List<OrigemConta> findAtivos(@Param(value="ativo") Boolean ativo);

}
