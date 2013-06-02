package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Aluguel;

public interface AluguelDAO extends CrudRepository<Aluguel, Long>{

}
