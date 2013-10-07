package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.BoletoSoftimob;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.persistencia.BoletoSoftimobDAO;

public class BoletoSoftimobService extends GenericService<BoletoSoftimob>{

	public BoletoSoftimobService() {
		super(BoletoSoftimobDAO.class);
	}
	
	@Override
	protected BoletoSoftimobDAO getRepository() {
		return (BoletoSoftimobDAO) super.getRepository();
	}

	public BoletoSoftimob findByConta(ContaPagarReceber conta) {
		return getRepository().findByConta(conta);
	}
	
}
