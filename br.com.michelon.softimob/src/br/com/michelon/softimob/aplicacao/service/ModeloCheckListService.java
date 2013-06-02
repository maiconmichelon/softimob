package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.ModeloCheckList;
import br.com.michelon.softimob.persistencia.ModeloCheckListDAO;

public class ModeloCheckListService extends GenericService<ModeloCheckList>{

	public ModeloCheckListService() {
		super(ModeloCheckListDAO.class);
	}
	
}
