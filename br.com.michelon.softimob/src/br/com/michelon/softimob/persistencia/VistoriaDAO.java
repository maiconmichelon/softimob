package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Bairro;

public interface VistoriaDAO extends CrudRepository<Bairro, Long>{

}
