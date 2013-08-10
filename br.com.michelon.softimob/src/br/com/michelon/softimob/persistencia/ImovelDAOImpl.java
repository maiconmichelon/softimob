package br.com.michelon.softimob.persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico.TipoContrato;
import br.com.michelon.softimob.tela.view.BuscaAvancadaImovelView.ModeloBusca;

@Repository
public class ImovelDAOImpl {

	@PersistenceContext
	private EntityManager em;

	public List<Imovel> buscaAvancada(ModeloBusca m){
		
		Date dataHoje = new Date();
		
		String query = "SELECT distinct i FROM Imovel i " +
				"LEFT JOIN ContratoPrestacaoServico c on c.imovel = i " +
				"LEFT JOIN Reserva r on r.imovel = i " +
				"WHERE ";
		
		if(m.getCodigo() != null)
			query += "AND (i.id = :codigo) ";
		if(m.getMetroMin() != null)
			query += "AND :metroMin <= i.metragem ";
		if(m.getMetroMax() != null)
			query += "AND :metroMax >= i.metragem ";
		if(m.getValMin() != null)
			query += "AND :valMin <= c.valor ";
		if(m.getValMax() != null)
			query += "AND :valMax >= c.valor ";
		if(m.getAngariador() != null)
			query += "AND :angariador = i.angariador ";
		if(m.getProprietario() != null)
			query += "AND i.proprietario = :proprietario ";
		if(m.getTipoImovel() != null)
			query += "AND i.tipo = :tipoImovel ";
		if(m.getBairro() != null)
			query += "AND i.endereco.rua.bairro = :bairro ";
		if(m.getCidade() != null)
			query += "AND i.endereco.rua.bairro.cidade = :cidade ";
		if(m.getObservacoes() != null)
			query += "AND i.observacoes like CONCAT('%', :observacoes, '%') ";
		if(!(m.isReservado() && m.isNaoReservado())){
			query += "AND (((:naoReservado = true AND c is null)) ";
			query += "OR ((:reservado = true AND c is not null AND c.dataVencimento >= :dataHoje))) ";
		}
		
		query+="AND ((:isTodos = true) " +
				"OR c.dataVencimento >= :dataHoje " +
				"AND ( " +
					"(:isVenda = true AND (c.tipo = :tipoVenda OR c.tipo =:tipoVendaAluguel)) " +
					"OR (:isAluguel = true AND (c.tipo = :tipoAluguel OR c.tipo =:tipoVendaAluguel)) " +
					"OR (:isVendaAluguel = true)" +
				"))";
		
		query = query.replaceAll("WHERE AND", "WHERE");
		if(query.endsWith("WHERE "))
			query = query.replaceAll("WHERE ", "");
		
		TypedQuery<Imovel> typedQuery = em.createQuery(query, Imovel.class);
		
		if(m.getCodigo() != null)
			typedQuery.setParameter("codigo", m.getCodigo());
		if(m.getMetroMin() != null)
			typedQuery.setParameter("metroMin", m.getMetroMin());
		if(m.getMetroMax() != null)
			typedQuery.setParameter("metroMax", m.getMetroMax());
		if(m.getValMin() != null)
			typedQuery.setParameter("valMin", m.getValMin());
		if(m.getValMax() != null)
			typedQuery.setParameter("valMax", m.getValMax());
		if(m.getAngariador() != null)
			typedQuery.setParameter("angariador", m.getAngariador());
		if(m.getProprietario() != null)
			typedQuery.setParameter("proprietario", m.getProprietario());
		if(m.getTipoImovel() != null)
			typedQuery.setParameter("tipoImovel", m.getTipoImovel());
		if(m.getBairro() != null)
			typedQuery.setParameter("bairro", m.getBairro());
		if(m.getCidade() != null)
			typedQuery.setParameter("cidade", m.getCidade());
		if(m.getObservacoes() != null)
			typedQuery.setParameter("observacoes", m.getObservacoes());
		if(!(m.isReservado() && m.isNaoReservado())){
			typedQuery.setParameter("naoReservado", m.isNaoReservado());
			typedQuery.setParameter("reservado", m.isReservado());
		}
		
		typedQuery.setParameter("dataHoje", dataHoje);
		typedQuery.setParameter("isAluguel", m.isLocacao());
		typedQuery.setParameter("isVenda", m.isVenda());
		typedQuery.setParameter("isVendaAluguel", m.isVendaLocacao());
		typedQuery.setParameter("isTodos", m.isTodos());
		typedQuery.setParameter("tipoVenda", TipoContrato.VENDA);
		typedQuery.setParameter("tipoAluguel", TipoContrato.LOCACAO);
		typedQuery.setParameter("tipoVendaAluguel", TipoContrato.LOCACAOVENDA);
		
		return typedQuery.getResultList();
	}

}
