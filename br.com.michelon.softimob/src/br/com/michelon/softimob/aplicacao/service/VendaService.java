package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Venda;
import br.com.michelon.softimob.persistencia.VendaDAO;

public class VendaService extends GenericService<Venda>{

	public VendaService() {
		super(VendaDAO.class);
	}
	
}
