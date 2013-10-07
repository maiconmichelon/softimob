package br.com.michelon.softimob.persistencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Pendencia;

public interface AluguelDAO extends CrudRepository<Aluguel, Long>, Serializable{

	@Query(value="SELECT a FROM Aluguel a WHERE a.contrato.imovel = :imovel")
	List<Aluguel> findByImovel(@Param(value = "imovel")Imovel imovel);
	
	List<Pendencia> findByResolvidoFalseAndDataVencimentoLessThan(Date dataHoje);

	@Query(value = "SELECT count(c) FROM Aluguel c WHERE c.resolvido = false AND c.dataVencimento <= :data")
	Long findContPendencia(@Param(value = "data")Date data);
	
}
