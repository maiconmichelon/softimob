package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.VendaAluguel;
import br.com.michelon.softimob.modelo.Vistoria;
import br.com.michelon.softimob.persistencia.VistoriaDAO;

public class VistoriaService extends GenericService<Vistoria>{

	public VistoriaService() {
		super(VistoriaDAO.class);
	}

	@Override
	protected VistoriaDAO getRepository() {
		return (VistoriaDAO) super.getRepository();
	}
	
	public List<Vistoria> findByVendaAluguel(VendaAluguel vendaAluguel) {
		return getRepository().findByVendaAluguel(vendaAluguel);
	}
	
}
