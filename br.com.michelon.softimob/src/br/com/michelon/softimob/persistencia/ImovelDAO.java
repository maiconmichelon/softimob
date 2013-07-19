package br.com.michelon.softimob.persistencia;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.TipoImovel;

public interface ImovelDAO extends CrudRepository<Imovel, Long>{

	@Query(value = "SELECT distinct(i) " +
			"FROM Imovel i " +
				"LEFT JOIN ContratoPrestacaoServico c on c.imovel = i " +
				"LEFT JOIN Reserva r on r.imovel = i " +
			"WHERE" +
			"(:codigo is null OR i.id = :codigo) " +
			"AND (:metroMin is null OR :metroMin <= i.metragem) " +
			"AND (:metroMax is null OR :metroMax >= i.metragem) " +
				"AND (:valMin is null OR :valMin <= c.valor) " +
				"AND (:valMax is null OR :valMax >= c.valor ) " +
//				"AND (:tipoContrato is null OR :tipoContrato = c.tipo) " +
//				"AND ((:valMin is null AND :valMax is null AND :tipoContrato is null) OR " +
//				"AND (c.dataVencimento >= :dataHoje) " +
			"AND (:angariador is null OR i.angariador = :angariador) " +
			"AND (:proprietario is null OR i.proprietario = :proprietario) " +
			"AND (i.tipo = :tipoImovel) " +
			"AND (:bairro is null OR i.endereco.rua.bairro = :bairro) " +
			"AND (:cidade is null OR i.endereco.rua.bairro.cidade = :cidade) " +
			"AND (:observacoes is null OR i.observacoes like CONCAT('%', :observacoes, '%')) " +
			"AND ((:naoReservado = true AND r is null) OR (:reservado = true AND r is not null AND r.dataVencimento >= :dataHoje) OR (:reservado = true AND :naoReservado = true))" +
			"")
	public abstract List<Imovel> buscaAvancada(
			@Param(value="dataHoje") Date dataHoje
			, @Param(value = "codigo")Long codigo
			, @Param(value = "valMin")BigDecimal valMin
			, @Param(value = "valMax")BigDecimal valMax
			, @Param(value = "metroMin")Integer metroMin
			, @Param(value = "metroMax")Integer metroMax
//			, @Param(value = "tipoContrato") TipoContrato tipoContrato SÓ O TIPO CONTRATO TA DANDO PAU, TEM Q V ISSAE =/
			, @Param(value = "angariador")Funcionario angariador
			, @Param(value = "proprietario")Cliente proprietario
			, @Param(value = "tipoImovel")TipoImovel tipoImovel
			, @Param(value = "cidade")Cidade cidade
			, @Param(value = "bairro")Bairro bairro
			, @Param(value = "observacoes")String observacoes
			, @Param(value = "reservado")Boolean reservado
			, @Param(value = "naoReservado")Boolean naoReservado
			);

	@Query("SELECT size(i.fotos) FROM Imovel i WHERE i = :imovel")
	public abstract Integer sizeImages(@Param(value = "imovel")Imovel imovel);
	
}
