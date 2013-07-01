package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.AcontecimentoChamado;
import br.com.michelon.softimob.modelo.ChamadoReforma;

public interface AcontecimentoChamadoDAO extends CrudRepository<AcontecimentoChamado, Long>{

	List<AcontecimentoChamado> findByChamadoReforma(ChamadoReforma chamadoReforma);

}
