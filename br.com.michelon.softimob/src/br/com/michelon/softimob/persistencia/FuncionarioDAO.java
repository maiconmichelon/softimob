package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Funcionario;

public interface FuncionarioDAO extends CrudRepository<Funcionario, Long>{

}
