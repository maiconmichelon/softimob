package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.ModeloContrato;
import br.com.michelon.softimob.persistencia.ModeloContratoDAO;

public class ModeloContratoService extends GenericService<ModeloContrato>{

	public ModeloContratoService() {
		super(ModeloContratoDAO.class);
	}

	@Override
	protected ModeloContratoDAO getRepository() {
		return (ModeloContratoDAO) super.getRepository();
	}
	
	public List<ModeloContrato> findAtivos() {
		return getRepository().findAtivo(true);
	}
	
}
