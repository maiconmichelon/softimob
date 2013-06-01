package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Estado;

public interface EstadoDAO extends CrudRepository<Estado, Long> {

}
