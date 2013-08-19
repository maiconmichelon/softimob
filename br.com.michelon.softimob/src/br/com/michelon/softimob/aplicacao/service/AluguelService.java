package br.com.michelon.softimob.aplicacao.service;

import java.util.Date;
import java.util.List;

import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.persistencia.AluguelDAO;
import br.com.michelon.softimob.persistencia.AluguelDAOImpl;

public class AluguelService extends GenericService<Aluguel>{

	public AluguelService() {
		super(AluguelDAO.class);
	}
	
	@Override
	protected AluguelDAO getRepository() {
		return (AluguelDAO) super.getRepository();
	}
	
	public List<Aluguel> findByImovel(Imovel imovel){
		return getRepository().findByImovel(imovel);
	}
	
	public List<Aluguel> findByDataVencimento(Date dataVencimento){
		return getDaoImpl(AluguelDAOImpl.class).findByDataVencimento(dataVencimento);
	}
	
}
