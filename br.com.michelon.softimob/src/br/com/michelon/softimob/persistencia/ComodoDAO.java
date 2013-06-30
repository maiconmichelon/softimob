package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Comodo;
import br.com.michelon.softimob.modelo.Imovel;

public interface ComodoDAO extends CrudRepository<Comodo, Long>{

	List<Comodo> findByImovel(Imovel imovel);
	
}

