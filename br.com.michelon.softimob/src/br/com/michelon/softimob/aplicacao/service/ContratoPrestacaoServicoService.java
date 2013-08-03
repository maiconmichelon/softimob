package br.com.michelon.softimob.aplicacao.service;

import java.util.Date;
import java.util.List;

import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico.TipoContrato;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Pendencia;
import br.com.michelon.softimob.persistencia.ContratoPrestacaoServicoDAO;
import br.com.michelon.softimob.persistencia.ContratoPrestacaoServicoDAOImpl;

public class ContratoPrestacaoServicoService extends GenericService<ContratoPrestacaoServico>{

	public ContratoPrestacaoServicoService() {
		super(ContratoPrestacaoServicoDAO.class);
	}

	@Override
	protected ContratoPrestacaoServicoDAO getRepository() {
		return (ContratoPrestacaoServicoDAO) super.getRepository();
	}
	
	public List<ContratoPrestacaoServico> findByImovel(Imovel imovel){
		return getRepository().findByImovel(imovel);
	}
	
	public List<Pendencia> findByDataVencimento(Date dataVencimento){
		return getRepository().findByDataVencimento(dataVencimento);
	}
	
	public List<ContratoPrestacaoServico> findByTipo(TipoContrato tipo){
		return getDaoImpl(ContratoPrestacaoServicoDAOImpl.class).findByTipo(tipo);
	}
	
}
