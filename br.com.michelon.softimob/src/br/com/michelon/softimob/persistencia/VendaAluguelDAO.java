package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.VendaAluguel;

public interface VendaAluguelDAO extends CrudRepository<VendaAluguel, Long>{

	@Query(value = "SELECT v FROM VendaAluguel v WHERE v.contrato = :contrato")
	List<VendaAluguel> findByContrato(@Param(value = "contrato")ContratoPrestacaoServico contrato);

}
