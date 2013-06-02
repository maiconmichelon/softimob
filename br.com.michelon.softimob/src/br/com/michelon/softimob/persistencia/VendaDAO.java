package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Venda;

public interface VendaDAO extends CrudRepository<Venda, Long>{

}
