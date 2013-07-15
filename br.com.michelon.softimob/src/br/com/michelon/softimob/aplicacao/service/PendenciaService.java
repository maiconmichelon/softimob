package br.com.michelon.softimob.aplicacao.service;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

import br.com.michelon.softimob.modelo.Pendencia;

public class PendenciaService {

	public List<Pendencia> findPendencias(Date dataVencimento){
		List<Pendencia> pendencias = Lists.newArrayList();
		
		pendencias.addAll(new ContaPagarReceberService().findByDataVencimento(dataVencimento));
		pendencias.addAll(new ContratoPrestacaoServicoService().findByDataVencimento(dataVencimento));
		
		return pendencias;
	}
	
}
