package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.CheckList;
import br.com.michelon.softimob.persistencia.ModeloCheckListDAO;

public class ModeloCheckListService extends GenericService<CheckList>{

	public ModeloCheckListService() {
		super(ModeloCheckListDAO.class);
	}
	
}
