package br.com.michelon.softimob.persistencia;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.modelo.Pendencia;

public interface ContaPagarReceberDAO extends CrudRepository<ContaPagarReceber, Long>{

	@Query(value="" +
			" SELECT c" +
			" FROM ContaPagarReceber c" +
			" WHERE" +
				" (:dataInicio is null OR :dataInicio <= c.dataConta) " +
				" AND (:dataFinal is null OR :dataFinal >= c.dataConta) " +
				" AND (c.dataPagamento is null AND c.movimentacao is null) ")
	List<ContaPagarReceber> findContaPendentes(@Param(value="dataInicio") Date dataInicio, @Param(value="dataFinal") Date dataFinal);
	
	@Query(value="" +
			" SELECT c" +
			" FROM ContaPagarReceber c" +
			" WHERE " +
				" (:dataInicio is null OR :dataInicio <= c.dataConta) " +
				" AND (:dataFinal is null OR :dataFinal >= c.dataConta) " +
				" AND (c.dataPagamento is not null AND c.movimentacao is not null) ")
	List<ContaPagarReceber> findContaParaEstornar(@Param(value="dataInicio") Date dataInicio, @Param(value="dataFinal") Date dataFinal);

	List<Pendencia> findByDataPagamentoIsNullAndDataVencimentoLessThan(Date data);

	@Query(value = "SELECT count(c) FROM ContaPagarReceber c WHERE c.dataPagamento is null AND c.dataVencimento <= :data")
	Long findPendencia(@Param(value = "data")Date data);

	@Query(value = "" +
			"SELECT c " +
			"FROM ContaPagarReceber c " +
			"WHERE c.dataVencimento >= :dataInicio AND c.dataVencimento <= :dataFinal " +
			"ORDER BY c.dataConta")
	List<ContaPagarReceber> buscarContas(@Param(value = "dataInicio")Date dataInicio, @Param(value = "dataFinal")Date dataFinal);
	
	ContaPagarReceber findByMovimentacao(MovimentacaoContabil mov);
	
}
