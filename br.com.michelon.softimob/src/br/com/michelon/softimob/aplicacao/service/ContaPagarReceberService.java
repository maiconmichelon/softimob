package br.com.michelon.softimob.aplicacao.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.michelon.softimob.aplicacao.helper.HistoricoHelper;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.LancamentoContabil;
import br.com.michelon.softimob.modelo.LancamentoContabil.TipoLancamento;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.modelo.ParametrosEmpresa;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.persistencia.ContaPagarReceberDAO;
import br.com.michelon.softimob.tela.view.PgtoRecContaView.ModeloPgtoConta;

import com.google.common.collect.Lists;

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
	
	public MovimentacaoContabil geraMovimentacao(ContaPagarReceber c, ModeloPgtoConta model) throws Exception{
		MovimentacaoContabil mov = new MovimentacaoContabil();
		
		mov.setData(model.getDataBaixa());
		mov.setValor(c.getValorMovimentacao());
		mov.setLancamentos(gerarLancamentos(c, mov, model.getDataBaixa(), model.getConta()));
	
		return mov;
	}
	
	/**
	 * Gera todos os lançamentos da baixa da conta, inclusive o lançamento de Juros e descontos
	 * 
	 * @param conta - Conta que sera feita a baixa;
	 * @param movimentacao - Movimentação referente a baixa
	 * @param dataPagamento - Data que será paga a conta
	 * @param planoConta - Conta para debitar ou creditar que pode ser a conta bancaria - pode ser nula e se for utiliza a conta padrao.
	 * @return - Retorna a mesma movimentação que foi passada em parametro
	 */
	public List<LancamentoContabil> gerarLancamentos(ContaPagarReceber conta, MovimentacaoContabil movimentacao, Date dataPagamento, PlanoConta planoContaBanco) throws Exception{
		ParametrosEmpresa parametros = new ParametrosEmpresaService().findParametrosEmpresa();
		List<LancamentoContabil> lctos = Lists.newArrayList();
		
		if(conta.getOrigem().getConta() != null){
			lctos.add(LancamentoContabil.createCredito(movimentacao, conta.isApagar() && planoContaBanco != null ? planoContaBanco : conta.getOrigem().getConta(), 
					conta.getValorCredito(), HistoricoHelper.getHistoricoPagamento(conta, dataPagamento), ""));
		}
		
		if(conta.getOrigem().getContaContraPartida() != null){
			lctos.add(LancamentoContabil.createDebito(movimentacao, conta.isAReceber() && planoContaBanco != null ? planoContaBanco : conta.getOrigem().getContaContraPartida(), 
					conta.getValorDebito(), HistoricoHelper.getHistoricoPagamento(conta, dataPagamento), ""));
		}
		
		LancamentoContabil lctoJurosDesconto = gerarLancamentoJurosDesconto(conta, dataPagamento, parametros, movimentacao);
		if(lctoJurosDesconto != null)
			lctos.add(lctoJurosDesconto);
		
		return lctos;
	}

	private LancamentoContabil gerarLancamentoJurosDesconto(ContaPagarReceber c, Date dataPagamento, ParametrosEmpresa parametros, MovimentacaoContabil mov) throws Exception {
		BigDecimal jurDesc = c.getValorJurosDesconto();
		
		if(jurDesc == null || jurDesc.signum() == 0)
			return null;
			
		PlanoConta contaJurDesc = null;
		TipoLancamento credDeb = null;
//		String historico = null;
				
		if(c.isApagar()){
			if(jurDesc.signum() < 0){
				contaJurDesc = parametros.getContaDescontoRecebido();
				credDeb = TipoLancamento.CREDITO;
//				historico = "Desconto concedido sobre ";
			} else {
				contaJurDesc = parametros.getContaJurosPagos();
				credDeb = TipoLancamento.DEBITO;
//				historico = "Juros concedido sobre ";
			}
		} else {
			if(jurDesc.signum() < 0){
				contaJurDesc = parametros.getContaDescontoConcedido();
				credDeb = TipoLancamento.DEBITO;
//				historico = "Descontos concedidos sobre ";
			} else {
				contaJurDesc = parametros.getContaJurosRecebido();
				credDeb = TipoLancamento.CREDITO;
//				historico = "Juros recebidos sobre ";
			}
		}

		return LancamentoContabil.create(mov, credDeb, contaJurDesc, jurDesc, HistoricoHelper.getHistoricoJurosDesconto(c, dataPagamento), StringUtils.EMPTY);
	}
	
	public void baixarContas (List<ContaPagarReceber> contas) throws Exception{
		List<ContaPagarReceber> contasParaSalvar = Lists.newArrayList();
		for(ContaPagarReceber conta : contas){
			contasParaSalvar.add(conta);
			
			if(conta.getValorPagoParcialTratado().signum() != 0){
				ContaPagarReceber contaFilha = new ContaPagarReceber();
				// Aqui tem q setar os valores verificar se vai ser criada uma nova classe para as contas filhas ou utilizará a mesma
			} else {
				conta.setValorPagoParcial(conta.getValor());
			}
			
			if(conta.getDataPagamento() == null)
				throw new Exception("A conta não possui data de pagamento.");
			if(conta.getMovimentacao() == null)
				throw new Exception("A conta não possui movimentação");
			if(conta.getValorPagoParcial().signum() == 0)
				throw new Exception("O valor pago parcial não pode ser igual a zero");

			contasParaSalvar.add(conta);
		}
		
		salvar(contasParaSalvar);
	}
	
	public void salvar(List<ContaPagarReceber> contas){
		getRepository().save(contas);
	}
	
}
