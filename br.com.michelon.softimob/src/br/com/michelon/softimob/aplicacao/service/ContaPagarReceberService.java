package br.com.michelon.softimob.aplicacao.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.LancamentoContabil;
import br.com.michelon.softimob.modelo.LancamentoContabil.TipoLancamento;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.modelo.ParametrosEmpresa;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.persistencia.ContaPagarReceberDAO;

public class ContaPagarReceberService extends GenericService<ContaPagarReceber>{

	public ContaPagarReceberService() {
		super(ContaPagarReceberDAO.class);
	}

	public void setarMovimentacao(ContaPagarReceber conta, Date dataPagamento) throws Exception{
//		MovimentacaoContabil mov = new MovimentacaoContabil();
//		
//		mov.setData(dataPagamento);
//		mov = gerarLancamentos(conta, mov);
//		
//		BigDecimal totalCred = BigDecimal.ZERO;
//		BigDecimal totalDeb = BigDecimal.ZERO;
//		
//		for(LancamentoContabil lcto : mov.getLancamentos()){
//			if(lcto.isCredito())
//				totalCred = totalCred.add(lcto.getValor());
//			if(lcto.isDebito())
//				totalDeb = totalDeb.add(lcto.getValor());
//		}
//		
//		if(totalCred.compareTo(totalDeb) != 0)
//			throw new Exception("O valor total de débito não pode ser diferente do valor total de crédito");
//		else
//			mov.setValor(totalCred);
//
//		conta.setMovimentacao(mov);
//		conta.setDataPagamento(dataPagamento);
	}
	
	public MovimentacaoContabil geraMovimentacao(ContaPagarReceber c, Date dataPagamento){
		MovimentacaoContabil mov = new MovimentacaoContabil();
		
		mov.setData(dataPagamento);
		mov.setValor(c.getValorMovimentacao());
		mov.setLancamentos(gerarLancamentos(c, mov));
	
		return mov;
	}
	
	/**
	 * Gera todos os lançamentos da baixa da conta, inclusive o lançamento de Juros e descontos
	 * 
	 * @param conta - Conta que sera feita a baixa;
	 * @param movimentacao - Movimentação referente a baixa
	 * @return - Retorna a mesma movimentação que foi passada em parametro
	 */
	public List<LancamentoContabil> gerarLancamentos(ContaPagarReceber conta, MovimentacaoContabil movimentacao){
		ParametrosEmpresa parametros = new ParametrosEmpresaService().findParametrosEmpresa();
		List<LancamentoContabil> lctos = Lists.newArrayList();
		
		lctos.add(LancamentoContabil.createCredito(movimentacao, conta.getOrigem().getConta(), conta.getValorCredito(), "", ""));
		lctos.add(LancamentoContabil.createDebito(movimentacao, conta.getOrigem().getContaContraPartida(), conta.getValorDebito(), "", ""));
		
		LancamentoContabil lctoJurosDesconto = gerarLancamentoJurosDesconto(conta.getValorJurosDesconto(), conta.isApagar(), parametros, movimentacao);
		if(lctoJurosDesconto != null)
			lctos.add(lctoJurosDesconto);
		
		return lctos;
	}

	private LancamentoContabil gerarLancamentoJurosDesconto(BigDecimal jurDesc, boolean isAPagar, ParametrosEmpresa parametros, MovimentacaoContabil mov) {
		if(jurDesc == null || jurDesc.signum() == 0)
			return null;
			
		PlanoConta contaJurDesc = null;
		TipoLancamento credDeb = null;
		String historico = null;
				
		if(isAPagar){
			if(jurDesc.signum() < 0){
				contaJurDesc = parametros.getContaDescontoObtidos();
				credDeb = TipoLancamento.DEBITO;
				historico = "Desconto concedido sobre ";
			} else {
				contaJurDesc = parametros.getContaJurosPagos();
				credDeb = TipoLancamento.CREDITO;
				historico = "Juros concedido sobre ";
			}
		} else {
			if(jurDesc.signum() < 0){
				contaJurDesc = parametros.getContaDescontoConcedido();
				credDeb = TipoLancamento.CREDITO;
				historico = "Descontos concedidos sobre ";
			} else {
				contaJurDesc = parametros.getContaJurosRecebido();
				credDeb = TipoLancamento.DEBITO;
				historico = "Juros recebidos sobre ";
			}
		}

		return LancamentoContabil.create(mov, credDeb, contaJurDesc, jurDesc, historico, StringUtils.EMPTY);
	}
	
	private List<ContaPagarReceber> contasParaSalvar(List<ContaPagarReceber> contas, Date dataPagamento) throws Exception{
		List<ContaPagarReceber> contasParaSalvar = Lists.newArrayList();
		for(ContaPagarReceber conta : contas){
			setarMovimentacao(conta, dataPagamento);
			contasParaSalvar.add(conta);
			
			if(conta.getValorPagoParcialTratado().signum() != 0){
				ContaPagarReceber contaFilha = new ContaPagarReceber();
				// Aqui tem q setar os valores verificar se vai ser criada uma nova classe para as contas filhas ou utilizará a mesma
			}
		}
		
		return null;
	}
	
}
