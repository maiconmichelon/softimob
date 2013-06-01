package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Cidade;

public interface CidadeDAO extends CrudRepository<Cidade, Long>{

}
