package br.com.michelon.softimob.persistencia;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.ParametrosEmpresa;

public interface ParametrosEmpresaDAO extends CrudRepository<ParametrosEmpresa, Long>{

	@Query(value="SELECT p FROM ParametrosEmpresa p")
	public ParametrosEmpresa findParametrosEmpresa();
	
}
