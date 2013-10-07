package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Proposta;

public interface PropostaDAO extends CrudRepository<Proposta, Long>{

	@Query(value="SELECT p FROM Proposta p WHERE p.imovel = :imovel AND " +
			"(SELECT pAux.id FROM Proposta pAux WHERE pAux.contraProposta = p) IS NULL")
	List<Proposta> findByImovel(@Param(value = "imovel")Imovel imovel);

	List<Proposta> findByStatusIsNull();

	@Query(value = "SELECT count(p) FROM Proposta p WHERE p.status is null")
	Long findCountPendencias();
	
}
