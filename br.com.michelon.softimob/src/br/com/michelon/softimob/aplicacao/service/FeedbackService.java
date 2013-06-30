package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.Feedback;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.persistencia.FeedbackDAO;

public class FeedbackService extends GenericService<Feedback>{

	public FeedbackService() {
		super(FeedbackDAO.class);
	}

	@Override
	protected FeedbackDAO getRepository() {
		return (FeedbackDAO) super.getRepository(); 
	}
	
	public List<Feedback> findByImovel(Imovel imovel) {
		return getRepository().findByImovel(imovel);
	}
	
}
