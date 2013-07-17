package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.aplicacao.helper.listElementDialog.OkListElementDialogListener;
import br.com.michelon.softimob.modelo.Comissionado;
import br.com.michelon.softimob.persistencia.ComissionadoDAO;

public class ComissionadoService extends GenericService<Comissionado>{

	public ComissionadoService() {
		super(ComissionadoDAO.class);
	}

	@Override
	protected ComissionadoDAO getRepository() {
		return (ComissionadoDAO) super.getRepository();
	}
	
	public List<Comissionado> findAtivos() {
		return getRepository().findAtivos(true);
	}

}
