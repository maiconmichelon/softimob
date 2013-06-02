package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.modelo.Estado;

public interface CidadeDAO extends CrudRepository<Cidade, Long>{

	@Query(value = "SELECT c FROM Cidade c WHERE c.estado = :uf")
	public List<Cidade> findCidadesByEstado(@Param(value="uf") Estado estado);

}
