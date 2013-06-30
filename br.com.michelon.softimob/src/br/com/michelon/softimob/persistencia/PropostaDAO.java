package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Proposta;

public interface PropostaDAO extends CrudRepository<Proposta, Long>{

	List<Proposta> findByImovel(Imovel imovel);
	
}
