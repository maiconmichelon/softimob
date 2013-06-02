package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.MovimentacaoContabil;

public interface MovimentacaoContabilDAO extends CrudRepository<MovimentacaoContabil, Long>{

}
