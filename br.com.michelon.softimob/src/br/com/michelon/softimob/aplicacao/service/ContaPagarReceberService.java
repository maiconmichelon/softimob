package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.persistencia.ContaPagarReceberDAO;

public class ContaPagarReceberService extends GenericService<ContaPagarReceber>{

	public ContaPagarReceberService() {
		super(ContaPagarReceberDAO.class);
	}
	
}
