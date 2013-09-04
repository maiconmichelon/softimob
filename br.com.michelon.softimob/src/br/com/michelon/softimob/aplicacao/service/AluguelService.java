package br.com.michelon.softimob.aplicacao.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Pendencia;
import br.com.michelon.softimob.persistencia.AluguelDAO;

public class AluguelService extends GenericService<Aluguel>{

	private Logger log = Logger.getLogger(getClass());
	
	public AluguelService() {
		super(AluguelDAO.class);
	}
	
	@Override
	protected AluguelDAO getRepository() {
		return (AluguelDAO) super.getRepository();
	}
	
	public List<Aluguel> findByImovel(Imovel imovel){
		return getRepository().findByImovel(imovel);
	}
	
	public List<Pendencia> findByDataVencimento(Date dataVencimento){
		return getRepository().findByDataVencimentoBeforeAndResolvidoFalse(dataVencimento);
	}

	public void finalizarPendencia(Aluguel aluguel) {
		if (DialogHelper.openConfirmation("Tem certeza que deseja finalizar a pendencia do aluguel ?")) {
			try {
				aluguel.setResolvido(true);
				salvar(aluguel);
			} catch (Exception e) {
				log.error("Erro ao finalizar aluguel como pendencia.", e);
			}
		}
	}
	
}
