package br.com.michelon.softimob.aplicacao.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

import br.com.michelon.softimob.modelo.Pendencia;

public class PendenciaService {

	public List<Pendencia> findPendencias(Date dataVencimento){
		List<Pendencia> pendencias = Lists.newArrayList();
		
		Calendar c = Calendar.getInstance();
		c.setTime(dataVencimento);
		c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
		dataVencimento = c.getTime();
		
		pendencias.addAll(new ContaPagarReceberService().findByDataVencimento(dataVencimento));
		pendencias.addAll(new ContratoPrestacaoServicoService().findByDataVencimento(dataVencimento));
		pendencias.addAll(new AluguelService().findByDataVencimento(dataVencimento));
		pendencias.addAll(new ChamadoReformaService().findByDataVencimento(dataVencimento));
		
		return pendencias;
	}
	
}
