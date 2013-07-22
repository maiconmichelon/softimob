package br.com.michelon.softimob.persistencia;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico.TipoContrato;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.TipoImovel;

@Repository
public class ImovelDAOImpl {

	@PersistenceContext
	private EntityManager em;

	public List<Imovel> buscaAvancada(Long codigo, BigDecimal valMin, BigDecimal valMax, Integer metroMin, Integer metroMax, TipoContrato tipoContrato, Funcionario angariador, Cliente proprietario, TipoImovel tipoImovel, Cidade cidade, Bairro bairro, String observacoes, Boolean reservado, Boolean naoReservado) {
		
		Date dataHoje = new Date();
		
		String query = "SELECT i FROM Imovel i " +
				"LEFT JOIN ContratoPrestacaoServico c on c.imovel = i " +
				"LEFT JOIN Reserva r on r.imovel = i " +
				"WHERE ";
		
		if(codigo != null)
			query += "AND (i.id = :codigo) ";
		if(metroMin != null)
			query += "AND :metroMin <= i.metragem ";
		if(metroMax != null)
			query += "AND :metroMax >= i.metragem ";
		if(valMin != null)
			query += "AND :valMin <= c.valor ";
		if(valMax != null)
			query += "AND :valMax >= c.valor ";
		if(tipoContrato != null)
			query += "AND :tipoContrato = c.tipo ";
		if(angariador != null)
			query += "AND :angariador = i.angariador ";
		if(proprietario != null)
			query += "AND i.proprietario = :proprietario ";
		if(tipoImovel != null)
			query += "AND i.tipo = :tipoImovel ";
		if(bairro != null)
			query += "AND i.endereco.rua.bairro = :bairro ";
		if(cidade != null)
			query += "AND i.endereco.rua.bairro.cidade = :cidade ";
		if(observacoes != null)
			query += "AND i.observacoes like CONCAT('%', :observacoes, '%') ";
		if(naoReservado != null && reservado != null){
			query += "AND (((:naoReservado = true AND c is null)) ";
			query += "OR ((:reservado = true AND c is not null AND c.dataVencimento >= :dataHoje))) ";
		}
		
		query = query.replaceAll("WHERE AND", "WHERE");
		
		TypedQuery<Imovel> typedQuery = em.createQuery(query, Imovel.class);
		
		if(codigo != null)
			typedQuery.setParameter("codigo", codigo);
		if(metroMin != null)
			typedQuery.setParameter("metroMin", metroMin);
		if(metroMax != null)
			typedQuery.setParameter("metroMax", metroMax);
		if(valMin != null)
			typedQuery.setParameter("valMin", valMin);
		if(valMax != null)
			typedQuery.setParameter("valMax", valMax);
		if(tipoContrato != null)
			typedQuery.setParameter("tipoContrato", tipoContrato);
		if(angariador != null)
			typedQuery.setParameter("angariador", angariador);
		if(proprietario != null)
			typedQuery.setParameter("proprietario", proprietario);
		if(tipoImovel != null)
			typedQuery.setParameter("tipoImovel", tipoImovel);
		if(bairro != null)
			typedQuery.setParameter("bairro", bairro);
		if(cidade != null)
			typedQuery.setParameter("cidade", cidade);
		if(observacoes != null)
			typedQuery.setParameter("observacoes", observacoes);
		if(naoReservado != null)
			typedQuery.setParameter("naoReservado", naoReservado);
		if(reservado != null)
			typedQuery.setParameter("reservado", reservado);
			
		typedQuery.setParameter("dataHoje", dataHoje);
		
		return typedQuery.getResultList();
	}

}
