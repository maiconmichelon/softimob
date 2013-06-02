package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Comissao;

public interface ComissaoDAO extends CrudRepository<Comissao, Long>{

}
