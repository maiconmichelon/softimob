package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.persistencia.PropostaDAO;

public class PropostaService extends GenericService<Proposta>{

	private Logger log = Logger.getLogger(getClass());
	
	public PropostaService() {
		super(PropostaDAO.class);
	}

	@Override
	protected PropostaDAO getRepository() {
		return (PropostaDAO) super.getRepository();
	}
	
	public List<Proposta> findByImovel(Imovel imovel) {
		return getRepository().findByImovel(imovel);
	}

	public List<Proposta> findPendencias() {
		return getRepository().findByStatusIsNull();
	}

	public void abrirTela(Proposta proposta) {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(proposta.getEditorInput(), proposta.getIdEditor());
		} catch (PartInitException e) {
			log.error("Erro ao abrir tela de imóveis com chamado de reforma");
		}		
	}

	public Long findContPendencias() {
		return getRepository().findCountPendencias();
	}
	
}
