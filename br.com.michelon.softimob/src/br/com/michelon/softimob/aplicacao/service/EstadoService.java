package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Estado;
import br.com.michelon.softimob.persistencia.EstadoDAO;
import br.com.michelon.softimob.persistencia.SpringUtils;

public class EstadoService extends GenericService<Estado>{

	public EstadoService() {
		super(SpringUtils.getContext().getBean(EstadoDAO.class));
	}

}
