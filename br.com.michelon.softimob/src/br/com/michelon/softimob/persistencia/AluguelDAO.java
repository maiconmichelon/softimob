package br.com.michelon.softimob.persistencia;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Pendencia;

public interface AluguelDAO extends CrudRepository<Aluguel, Long>{

	@Query(value="SELECT a FROM Aluguel a WHERE a.contrato.imovel = :imovel")
	List<Aluguel> findByImovel(@Param(value = "imovel")Imovel imovel);
	
	List<Pendencia> findByDataVencimentoBeforeAndResolvidoFalse(Date dataVencimento);
	
}
