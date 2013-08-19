package br.com.michelon.softimob.aplicacao.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.ChamadoReforma;
import br.com.michelon.softimob.persistencia.ChamadoReformaDAO;

public class ChamadoReformaService extends GenericService<ChamadoReforma>{

	public ChamadoReformaService() {
		super(ChamadoReformaDAO.class);
	}

	@Override
	protected ChamadoReformaDAO getRepository() {
		return (ChamadoReformaDAO) super.getRepository();
	}
	
	public List<ChamadoReforma> findByAluguel(Aluguel aluguel) {
		return getRepository().findByAluguel(aluguel);
	}

	public Collection<ChamadoReforma> findByDataVencimento(Date dataVencimento) {
		return getRepository().findByDataBeforeAndFinalizacaoIsNull(dataVencimento);
	}

}
