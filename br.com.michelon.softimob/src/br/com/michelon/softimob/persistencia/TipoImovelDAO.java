package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.TipoImovel;

public interface TipoImovelDAO extends CrudRepository<TipoImovel, Long>{

}
