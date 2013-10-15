package br.com.michelon.softimob.aplicacao.service;

import java.util.Date;
import java.util.List;

import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico.TipoContrato;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Pendencia;
import br.com.michelon.softimob.modelo.VendaAluguel;
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
	
	public List<Pendencia> findByDataVencimento(Date data){
		return getRepository().findByResolvidoFalseAndDataVencimentoLessThan(data);
	}
	
	public List<ContratoPrestacaoServico> findByTipoAndNotResolvido(TipoContrato tipo){
		return getDaoImpl(ContratoPrestacaoServicoDAOImpl.class).findByTipo(tipo);
	}
	
	@Override
	public void removerAtivarOuDesativar(ContratoPrestacaoServico registro) throws Exception {
		List<VendaAluguel> vendasAlugueis = new VendaAluguelService().findByContrato(registro);
		
		if(vendasAlugueis != null && !vendasAlugueis.isEmpty())
			throw new Exception("Existe uma venda/aluguel feito sobre este contrato, é necessário remove-lo primeiro");
		
		super.removerAtivarOuDesativar(registro);
	}

	public Long findPendencia(Date dataHoje) {
		return getRepository().findPendencia(dataHoje);
	}
	
}
