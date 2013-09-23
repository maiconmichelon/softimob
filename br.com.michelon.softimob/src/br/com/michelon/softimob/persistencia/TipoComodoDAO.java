package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.TipoComodo;
import br.com.michelon.softimob.modelo.TipoImovel;

public interface TipoComodoDAO extends CrudRepository<TipoComodo, Long>{

	@Query("SELECT r FROM TipoComodo r WHERE r.ativo = :ativo")
	List<TipoComodo> findAtivo(@Param(value = "ativo") Boolean ativo);

	@Query(value="SELECT distinct(tc) " +
			"FROM TipoComodo tc, IN(tc.tipoImovelTipoComodo) titc " +
			"WHERE titc.preSelecionado = true AND titc.tipoImovel = :tipoImovel AND tc.ativo = true")
	List<TipoComodo> findByTipoImovelAndPreSelecionadoIsTrue(@Param(value = "tipoImovel")TipoImovel tipoImovel);
	
	@Query(value="SELECT distinct(tc) " +
			"FROM TipoComodo tc, IN(tc.tipoImovelTipoComodo) titc " +
			"WHERE titc.tipoImovel = :tipoImovel AND tc.ativo = true")
	List<TipoComodo> findByTipoImovel(@Param(value = "tipoImovel")TipoImovel tipoImovel);
	
}
