package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Chave;

public interface ChaveDAO extends CrudRepository<Chave, Long>{

}
