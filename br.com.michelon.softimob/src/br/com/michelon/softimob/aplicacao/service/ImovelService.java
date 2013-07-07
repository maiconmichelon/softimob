package br.com.michelon.softimob.aplicacao.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ObjectUtils.Null;

import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.TipoImovel;
import br.com.michelon.softimob.persistencia.ImovelDAO;

public class ImovelService extends GenericService<Imovel>{

	public ImovelService() {
		super(ImovelDAO.class);
	}

	@Override
	protected ImovelDAO getRepository() {
		return (ImovelDAO) super.getRepository();
	}
	
	public List<Imovel> findImoveis(Long codigo, BigDecimal valMin, BigDecimal valMax, 
			Integer metroMin, Integer metroMax, Boolean isVenda, 
			Boolean isLocacao, Funcionario angariador, Cliente proprietario,
			TipoImovel tipoImovel, Cidade cidade, Bairro bairro, 
			String observacoes, Boolean reservado, Boolean naoReservado){
		
//		return getRepository().buscaAvancada(codigo, valMin, valMax, metroMin, metroMax, isVenda, isLocacao, angariador, proprietario, tipoImovel, cidade, bairro, observacoes, reservado, naoReservado);
		return getRepository().buscaAvancada(new Date(), null, null, null, null, null, null, null, null, null, null, null, null, null);
//		return getRepository().buscaAvancada(null, null, null, null, null, null, null, null, null, null, null, null);
//		return getRepository().buscaAvancada(null, null, null, null, null, null, null, null, null, null, null);
//		return getRepository().buscaAvancada(new Date(), null, null, null, null, null, null, null, null, null, null, null);
	}
	
	public Integer sizeImages(Imovel imovel){
		return getRepository().sizeImages(imovel);
	}
	
}
