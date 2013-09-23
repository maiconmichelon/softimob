package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Departamento;

public interface DepartamentoDAO extends CrudRepository<Departamento, Long>{

	List<Departamento> findByAtivoIsTrue();

}
