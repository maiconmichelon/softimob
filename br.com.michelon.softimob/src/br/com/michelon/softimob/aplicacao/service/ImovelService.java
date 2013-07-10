package br.com.michelon.softimob.aplicacao.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.TipoImovel;
import br.com.michelon.softimob.persistencia.ImovelDAO;
import br.com.michelon.softimob.tela.view.BuscaAvancadaImovelView.ModeloBusca;

@Repository
public class ImovelService extends GenericService<Imovel>{

	public ImovelService() {
		super(ImovelDAO.class);
	}

	@Override
	protected ImovelDAO getRepository() {
		return (ImovelDAO) super.getRepository();
	}
	
	public List<Imovel> findImoveis(Long codigo, BigDecimal valMin, BigDecimal valMax, 
			Integer metroMin, Integer metroMax, Boolean isVenda, 
			Boolean isLocacao, Funcionario angariador, Cliente proprietario,
			TipoImovel tipoImovel, Cidade cidade, Bairro bairro, 
			String observacoes, Boolean reservado, Boolean naoReservado){

		return getRepository().buscaAvancada(new Date(), codigo, valMin, valMax, metroMin, metroMax, null, null, tipoImovel, cidade, bairro, observacoes, reservado, naoReservado);
//		return getRepository().buscaAvancada(new Date(), codigo, valMin, valMax, metroMin, metroMax, isVenda, isLocacao, angariador, proprietario, tipoImovel, cidade, bairro, observacoes, reservado, naoReservado);
//		return getRepository().buscaAvancada(new Date(), null, null, null, null, null, null, null, null, null, null, null, null, null);
//		return getRepository().buscaAvancada(null, null, null, null, null, null, null, null, null, null, null, null);
//		return getRepository().buscaAvancada(null, null, null, null, null, null, null, null, null, null, null);
//		return getRepository().buscaAvancada(new Date(), null, null, null, null, null, null, null, null, null, null, null);
	}
	
	public Integer sizeImages(Imovel imovel){
		return getRepository().sizeImages(imovel);
	}

	public List<Imovel> findImoveis(ModeloBusca m) {
		return findImoveis(m.getCodigo(), m.getValMin(), m.getValMax(), m.getMetroMin(), m.getMetroMax(), m.isVenda(), m.isLocacao(), m.getAngariador(), 
				m.getProprietario(), m.getTipoImovel(), m.getCidade(), m.getBairro(), m.getObservacoes(), m.isReservado(), m.isNaoReservado());
	}
	
}
