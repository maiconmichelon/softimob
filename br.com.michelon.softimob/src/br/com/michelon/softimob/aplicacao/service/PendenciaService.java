package br.com.michelon.softimob.aplicacao.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.ChamadoReforma;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.Pendencia;
import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.modelo.Reserva;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
		
		return pendencias;
	}

	public Map<Class<? extends Pendencia>, Long> findQuantidadePendencias(){
		Map<Class<? extends Pendencia>, Long> quantidade = Maps.newHashMap();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		Date dataHoje = calendar.getTime();
		
		quantidade.put(ContaPagarReceber.class, new ContaPagarReceberService().findPendencia(dataHoje));
		quantidade.put(ContratoPrestacaoServico.class, new ContratoPrestacaoServicoService().findPendencia(dataHoje));
		quantidade.put(Aluguel.class, new AluguelService().findContPendencias(dataHoje));
		quantidade.put(ChamadoReforma.class, new ChamadoReformaService().findContPendencias());
		quantidade.put(Proposta.class, new PropostaService().findContPendencias());
		quantidade.put(Reserva.class, new ReservaService().findContPendencias(dataHoje));
		
		return quantidade;
	}
	
}
