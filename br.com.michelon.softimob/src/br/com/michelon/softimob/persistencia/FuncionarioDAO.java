package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.Funcionario;

public interface FuncionarioDAO extends CrudRepository<Funcionario, Long>{

	@Query("SELECT r FROM Funcionario r WHERE r.ativo = :ativo")
	List<Funcionario> findAtivos(@Param(value="ativo")Boolean ativo);

}
