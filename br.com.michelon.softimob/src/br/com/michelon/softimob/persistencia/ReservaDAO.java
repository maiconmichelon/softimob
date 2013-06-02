package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Reserva;

public interface ReservaDAO extends CrudRepository<Reserva, Long>{

}
