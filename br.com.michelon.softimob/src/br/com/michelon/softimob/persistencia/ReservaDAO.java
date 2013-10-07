package br.com.michelon.softimob.persistencia;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Reserva;

public interface ReservaDAO extends CrudRepository<Reserva, Long>{

	List<Reserva> findByImovel(Imovel imovel);

	List<Reserva> findByResolvidoFalseAndDataVencimentoLessThan(Date dataHoje);

	List<Reserva> findByDataVencimentoAfterAndImovelEquals(Date data, Imovel imovel);

	@Query(value = "SELECT count(c) FROM Reserva c WHERE c.resolvido = false AND c.dataVencimento <= :data")
	Long findContPendencias(@Param(value = "data")Date dataHoje);
	
}
