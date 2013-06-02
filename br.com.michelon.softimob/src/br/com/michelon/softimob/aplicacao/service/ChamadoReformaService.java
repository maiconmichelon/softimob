package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.ChamadoReforma;
import br.com.michelon.softimob.persistencia.ChamadoReformaDAO;

public class ChamadoReformaService extends GenericService<ChamadoReforma>{

	public ChamadoReformaService() {
		super(ChamadoReformaDAO.class);
	}

}
