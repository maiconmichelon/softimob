package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.CheckList;
import br.com.michelon.softimob.persistencia.ItemCheckListDAO;

public class ItemCheckListService extends GenericService<CheckList>{

	public ItemCheckListService() {
		super(ItemCheckListDAO.class);
	}
	
}
