package br.com.michelon.softimob.persistencia;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.ChamadoReforma;

public interface ChamadoReformaDAO extends CrudRepository<ChamadoReforma, Long>{

	List<ChamadoReforma> findByAluguel(Aluguel aluguel);

	@Query(value="SELECT c FROM ChamadoReforma c WHERE c.data < :dataVencimento AND c.finalizacao is null")
	Collection<ChamadoReforma> findByDataBeforeAndFinalizacaoIsNull(@Param(value = "dataVencimento")Date dataVencimento);

}
