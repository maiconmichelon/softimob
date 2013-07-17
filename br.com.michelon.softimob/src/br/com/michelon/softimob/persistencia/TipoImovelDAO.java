package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.TipoImovel;

public interface TipoImovelDAO extends CrudRepository<TipoImovel, Long>{

	@Query("SELECT r FROM TipoImovel r WHERE r.ativo = :ativo")
	List<TipoImovel> findAtivo(@Param(value = "ativo")Boolean ativo);

}
