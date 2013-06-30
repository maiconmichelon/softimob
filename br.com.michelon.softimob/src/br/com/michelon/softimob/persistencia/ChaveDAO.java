package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Chave;
import br.com.michelon.softimob.modelo.Imovel;

public interface ChaveDAO extends CrudRepository<Chave, Long>{

	List<Chave> findByImovel(Imovel imovel);
	
}
