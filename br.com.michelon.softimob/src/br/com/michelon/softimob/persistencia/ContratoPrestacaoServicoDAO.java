package br.com.michelon.softimob.persistencia;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Pendencia;

public interface ContratoPrestacaoServicoDAO extends CrudRepository<ContratoPrestacaoServico, Long>{

	List<ContratoPrestacaoServico> findByImovel(Imovel imovel);

	List<Pendencia> findByResolvidoFalseAndDataVencimentoLessThan(Date data);

	@Query(value = "SELECT count(c) FROM ContratoPrestacaoServico c WHERE c.resolvido = false AND c.dataVencimento <= :data")
	Long findPendencia(@Param(value = "data")Date data);
	
}
