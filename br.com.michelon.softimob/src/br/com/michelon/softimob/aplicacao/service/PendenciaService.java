package br.com.michelon.softimob.aplicacao.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.michelon.softimob.modelo.Pendencia;

import com.google.common.collect.Lists;

public class PendenciaService {

	public List<Pendencia> findPendencias(Date dataVencimento){
		List<Pendencia> pendencias = Lists.newArrayList();
		
		Calendar c = Calendar.getInstance();
		c.setTime(dataVencimento);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
		dataVencimento = c.getTime();
		
		pendencias.addAll(new ContaPagarReceberService().findByDataVencimento(dataVencimento));
		pendencias.addAll(new ContratoPrestacaoServicoService().findByDataVencimento(dataVencimento));
		pendencias.addAll(new AluguelService().findByDataVencimento(dataVencimento));
		pendencias.addAll(new ChamadoReformaService().findByDataVencimento(dataVencimento));
		pendencias.addAll(new PropostaService().findPendencias(dataVencimento));
		pendencias.addAll(new ReservaService().findPendencias(dataVencimento));
		
		return pendencias;
	}
	
	public List<Pendencia> findPendencias(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		
		return findPendencias(c.getTime());
	}

}
