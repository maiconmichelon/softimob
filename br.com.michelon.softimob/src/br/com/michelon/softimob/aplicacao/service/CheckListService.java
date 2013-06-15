package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.CheckList;
import br.com.michelon.softimob.persistencia.ModeloCheckListDAO;

public class CheckListService extends GenericService<CheckList>{

	public CheckListService() {
		super(ModeloCheckListDAO.class);
	}
	
}
