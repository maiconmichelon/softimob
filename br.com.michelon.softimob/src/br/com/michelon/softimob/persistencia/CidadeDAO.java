package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.modelo.Estado;

public interface CidadeDAO extends CrudRepository<Cidade, Long>{

	@Query("FROM Cidade WHERE estado = :estado")
	List<Cidade> findCidadesByEstado(Estado estado);

}
