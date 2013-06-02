package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.persistencia.MovimentacaoContabilDAO;

public class MovimentacaoContabilService extends GenericService<MovimentacaoContabil>{

	public MovimentacaoContabilService() {
		super(MovimentacaoContabilDAO.class);
	}
	
}
