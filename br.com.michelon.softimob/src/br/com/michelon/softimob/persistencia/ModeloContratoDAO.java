package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.ModeloContrato;

public interface ModeloContratoDAO extends CrudRepository<ModeloContrato, Long>{

	List<ModeloContrato> findByAtivoIsTrue();

}
