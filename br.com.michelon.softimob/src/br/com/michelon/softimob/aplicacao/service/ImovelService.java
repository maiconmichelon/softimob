package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.michelon.softimob.modelo.ContratoPrestacaoServico.TipoContrato;
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
	
	public List<Imovel> findImoveis(ModeloBusca m) {
		return getDAOImpl().buscaAvancada(m);
	}
	
	public Integer sizeImages(Imovel imovel){
		return getRepository().sizeImages(imovel);
	}

	public List<Imovel> buscarImoveisVender(){
		return getDAOImpl().buscarImoveisVender();
	}
	
	public List<Imovel> buscarImoveisAlugar(){
		return getDAOImpl().buscarImoveisAluguel();
	}
	
	public List<Imovel> buscarImoveisVenderAlugar(){
		return getDAOImpl().buscarImoveisVenderAlugar();
	}

	public List<Imovel> findByTipoContrato(TipoContrato tipoContrato) {
		return getDAOImpl().buscarImoveisPorContrato(tipoContrato);
	}
	
}
