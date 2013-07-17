package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.Cliente;

public interface ClienteDAO extends CrudRepository<Cliente, Long>{

	@Query("SELECT c FROM Cliente c WHERE c.ativo = :ativo")
	List<Cliente> findAtivos(@Param(value="ativo")Boolean ativo);

}
