package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Vistoria;
import br.com.michelon.softimob.persistencia.VistoriaDAO;

public class VistoriaService extends GenericService<Vistoria>{

	public VistoriaService() {
		super(VistoriaDAO.class);
	}
	
}
