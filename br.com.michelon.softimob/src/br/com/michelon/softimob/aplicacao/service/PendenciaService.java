package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import br.com.michelon.softimob.modelo.Pendencia;

import com.google.common.collect.Lists;

public class PendenciaService {

	public List<Pendencia> findPendencias(){
		List<Pendencia> pendencias = Lists.newArrayList();
		
		pendencias.addAll(new ContaPagarReceberService().findByDataVencimento());
		pendencias.addAll(new ContratoPrestacaoServicoService().findByDataVencimento());
		pendencias.addAll(new AluguelService().findByDataVencimento());
		pendencias.addAll(new ChamadoReformaService().findByDataVencimento());
		pendencias.addAll(new PropostaService().findPendencias());
		pendencias.addAll(new ReservaService().findPendencias());
		
		return pendencias;
	}

}
