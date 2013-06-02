package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.Feedback;
import br.com.michelon.softimob.persistencia.FeedbackDAO;

public class FeedbackService extends GenericService<Feedback>{

	public FeedbackService() {
		super(FeedbackDAO.class);
	}
	
}
