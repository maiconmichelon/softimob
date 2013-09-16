package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Endereco;

public interface EnderecoDAO extends CrudRepository<Endereco, Long>{

	List<Endereco> findByCep(String cep);

}
