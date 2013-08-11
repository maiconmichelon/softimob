package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.persistencia.ImovelDAO;
import br.com.michelon.softimob.persistencia.ImovelDAOImpl;
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

	private ImovelDAOImpl getDAOImpl() {
		return super.getDaoImpl(ImovelDAOImpl.class);
	}
	
//	public List<Imovel> findImoveis(Long codigo, BigDecimal valMin, BigDecimal valMax, 
//			Integer metroMin, Integer metroMax, Boolean isVenda, 
//			Boolean isLocacao, Funcionario angariador, Cliente proprietario,
//			TipoImovel tipoImovel, Cidade cidade, Bairro bairro, 
//			String observacoes, Boolean reservado, Boolean naoReservado){

	public List<Imovel> findImoveis(ModeloBusca m) {
		return getDAOImpl().buscaAvancada(m);
	}
	
	public Integer sizeImages(Imovel imovel){
		return getRepository().sizeImages(imovel);
	}

//	public List<Imovel> findImoveis(ModeloBusca m) {
//		return findImoveis(m.getCodigo(), m.getValMin(), m.getValMax(), m.getMetroMin(), m.getMetroMax(), m.isVenda(), m.isLocacao(), m.getAngariador(), 
//				m.getProprietario(), m.getTipoImovel(), m.getCidade(), m.getBairro(), m.getObservacoes(), m.isReservado(), m.isNaoReservado());
//	}
	
}
