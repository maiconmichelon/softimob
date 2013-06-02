package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Endereco;

public interface EnderecoDAO extends CrudRepository<Endereco, Long>{

}
