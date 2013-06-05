package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.CheckList;

public interface ModeloCheckListDAO extends CrudRepository<CheckList, Long>{

}
