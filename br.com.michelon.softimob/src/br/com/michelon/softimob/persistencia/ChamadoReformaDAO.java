package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.ChamadoReforma;

public interface ChamadoReformaDAO extends CrudRepository<ChamadoReforma, Long>{

	List<ChamadoReforma> findByAluguel(Aluguel aluguel);

	@Query(value = "SELECT distinct(c) FROM ChamadoReforma c WHERE c.finalizacao is null")
	List<ChamadoReforma> findByFinalizacaoIsNull();

	@Query(value = "SELECT count(distinct(c)) FROM ChamadoReforma c WHERE c.finalizacao is null")
	Long findContPendencias();
	
}
