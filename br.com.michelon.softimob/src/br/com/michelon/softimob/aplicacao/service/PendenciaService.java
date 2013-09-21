package br.com.michelon.softimob.aplicacao.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.michelon.softimob.modelo.Pendencia;

import com.google.common.collect.Lists;

public class PendenciaService {

	public List<Pendencia> findPendencias(){
		List<Pendencia> pendencias = Lists.newArrayList();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		Date dataHoje = calendar.getTime();
		
		pendencias.addAll(new ContaPagarReceberService().findByDataVencimento(dataHoje));
		pendencias.addAll(new ContratoPrestacaoServicoService().findByDataVencimento(dataHoje));
		pendencias.addAll(new AluguelService().findPendencias(dataHoje));
		pendencias.addAll(new ChamadoReformaService().findByDataVencimento());
		pendencias.addAll(new PropostaService().findPendencias());
		pendencias.addAll(new ReservaService().findPendencias(dataHoje));
		
//		pendencias.addAll(new ContaPagarReceberService().findAll());
//		pendencias.addAll(new ContratoPrestacaoServicoService().findAll());
//		pendencias.addAll(new AluguelService().findAll());
//		pendencias.addAll(new ChamadoReformaService().findAll());
//		pendencias.addAll(new PropostaService().findAll());
//		pendencias.addAll(new ReservaService().findAll());
		
		return pendencias;
	}

}
