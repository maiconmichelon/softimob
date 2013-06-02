package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Placa;

public interface PlacaDAO extends CrudRepository<Placa, Long>{

}
