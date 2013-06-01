package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Cliente;

public interface ClienteDAO extends CrudRepository<Cliente, Long>{

}
