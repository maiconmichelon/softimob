package br.com.michelon.softimob.aplicacao.service;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.ChamadoReforma;
import br.com.michelon.softimob.persistencia.ChamadoReformaDAO;

public class ChamadoReformaService extends GenericService<ChamadoReforma>{

	private Logger log = Logger.getLogger(getClass());
	
	public ChamadoReformaService() {
		super(ChamadoReformaDAO.class);
	}

	@Override
	protected ChamadoReformaDAO getRepository() {
		return (ChamadoReformaDAO) super.getRepository();
	}
	
	public List<ChamadoReforma> findByAluguel(Aluguel aluguel) {
		return getRepository().findByAluguel(aluguel);
	}

	public Collection<ChamadoReforma> findByDataVencimento() {
		return getRepository().findByFinalizacaoIsNull();
	}

	public void abrirTela(ChamadoReforma chamado) {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(chamado.getEditorInput(), chamado.getIdEditor());
		} catch (PartInitException e) {
			log.error("Erro ao abrir tela de imóveis com chamado de reforma");
		}
	}

	public Long findContPendencias() {
		return getRepository().findContPendencias();
	}

}
