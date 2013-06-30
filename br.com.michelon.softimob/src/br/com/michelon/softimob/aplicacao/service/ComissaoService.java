package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.Comissao;
import br.com.michelon.softimob.modelo.VendaAluguel;
import br.com.michelon.softimob.persistencia.ComissaoDAO;

public class ComissaoService extends GenericService<Comissao>{

	public ComissaoService() {
		super(ComissaoDAO.class);
	}

	@Override
	protected ComissaoDAO getRepository() {
		return (ComissaoDAO) super.getRepository();
	}
	
	public List<Comissao> findByVendaAluguel(VendaAluguel vendaAluguel) {
		return getRepository().findByVendaAluguel(vendaAluguel);
	}
	
}
