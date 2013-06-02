package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.ModeloContrato;
import br.com.michelon.softimob.persistencia.ModeloContratoDAO;

public class ModeloContratoService extends GenericService<ModeloContrato>{

	public ModeloContratoService() {
		super(ModeloContratoDAO.class);
	}
	
}
