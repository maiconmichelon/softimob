package br.com.michelon.softimob.aplicacao.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import br.com.michelon.softimob.aplicacao.exception.ContaJaPagaRecebidaException;
import br.com.michelon.softimob.aplicacao.exception.ContaNaoParametrizadaException;
import br.com.michelon.softimob.aplicacao.helper.HistoricoHelper;
import br.com.michelon.softimob.aplicacao.helper.LogHelper;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.LancamentoContabil;
import br.com.michelon.softimob.modelo.LancamentoContabil.TipoLancamento;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.modelo.OrigemConta;
import br.com.michelon.softimob.modelo.ParametrosEmpresa;
import br.com.michelon.softimob.modelo.Pendencia;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.persistencia.ContaPagarReceberDAO;
import br.com.michelon.softimob.tela.view.PgtoRecContaView;
import br.com.michelon.softimob.tela.view.PgtoRecContaView.ModeloPgtoConta;

import com.google.common.collect.Lists;

public class ContaPagarReceberService extends GenericService<ContaPagarReceber>{

	private Logger log = Logger.getLogger(getClass());
	
	public ContaPagarReceberService() {
		super(ContaPagarReceberDAO.class);
	}

	@Override
	protected ContaPagarReceberDAO getRepository() {
		return (ContaPagarReceberDAO) super.getRepository();
	}
	
	public MovimentacaoContabil geraMovimentacao(ContaPagarReceber c, ModeloPgtoConta model) throws Exception{
		MovimentacaoContabil mov = new MovimentacaoContabil();
		LogHelper.setLog(mov);
		
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
		ParametrosEmpresa parametroEmpresa = ParametrosEmpresa.getInstance();
		
		BigDecimal valorDuplicata = conta.getValor();
		List<LancamentoContabil> lancamentos = Lists.newArrayList();

		String historico = HistoricoHelper.getHistoricoPagamento(conta, dataPagamento);

		if (conta.isApagar()){
			if(conta.getOrigem().getConta() == null)
				throw new ContaNaoParametrizadaException(String.format("Para gerar os lançamentos é necessário que para o tipo de conta %s a conta esteja parametrizada.", conta.getOrigem().getNome()));
			lancamentos.add(LancamentoContabil.createDebito(movimentacao, conta.getOrigem().getConta(), valorDuplicata, historico, ""));
		}else{
			if(conta.getOrigem().getContaContraPartida() == null)
				throw new ContaNaoParametrizadaException(String.format("Para gerar os lançamentos é necessário que para o tipo de conta %s a conta de contra-partida esteja parametrizada.", conta.getOrigem().getNome()));
			lancamentos.add(LancamentoContabil.createCredito(movimentacao, conta.getOrigem().getContaContraPartida(), valorDuplicata, historico, ""));
		}
		
		PlanoConta contaCaixaBanco = planoContaBanco == null ? parametroEmpresa.getContaCaixa() : planoContaBanco;
		
		if(contaCaixaBanco == null)
			throw new ContaNaoParametrizadaException("Para gerar os lançamentos é necessário que a conta Caixa esteja parametrizada ou seja informada uma conta conta banco.");
		
		if (conta.isAReceber())
			lancamentos.add(LancamentoContabil.createDebito(movimentacao, contaCaixaBanco, conta.getValor().add(conta.getValorJurDescTratado()), historico, ""));
		else
			lancamentos.add(LancamentoContabil.createCredito(movimentacao, contaCaixaBanco, conta.getValor().add(conta.getValorJurDescTratado()), historico, ""));

		LancamentoContabil lctoJurosDesconto = gerarLancamentoJurosDesconto(conta, dataPagamento, parametroEmpresa, movimentacao);
		if(lctoJurosDesconto != null)
			lancamentos.add(lctoJurosDesconto);
		
		return lancamentos;
	}

	
	private LancamentoContabil gerarLancamentoJurosDesconto(ContaPagarReceber c, Date dataPagamento, ParametrosEmpresa parametros, MovimentacaoContabil mov) throws Exception {
		BigDecimal jurDesc = c.getValorJurosDesconto();
		
		if(jurDesc == null || jurDesc.signum() == 0)
			return null;
			
		PlanoConta contaJurDesc = null;
		TipoLancamento credDeb = null;
				
		if(c.isApagar()){
			if(jurDesc.signum() < 0){
				contaJurDesc = parametros.getContaDescontoRecebido();
				credDeb = TipoLancamento.CREDITO;
			} else {
				contaJurDesc = parametros.getContaJurosPagos();
				credDeb = TipoLancamento.DEBITO;
			}
		} else {
			if(jurDesc.signum() < 0){
				contaJurDesc = parametros.getContaDescontoConcedido();
				credDeb = TipoLancamento.DEBITO;
			} else {
				contaJurDesc = parametros.getContaJurosRecebido();
				credDeb = TipoLancamento.CREDITO;
			}
		}

		if(contaJurDesc == null)
			throw new ContaNaoParametrizadaException("A conta de Juros/Desconto deve ser parametrizada.");
		
		return LancamentoContabil.create(mov, credDeb, contaJurDesc, jurDesc.abs(), HistoricoHelper.getHistoricoJurosDesconto(c, dataPagamento), StringUtils.EMPTY);
	}
	
	public void baixarContas (List<ContaPagarReceber> contas) throws Exception{
		List<ContaPagarReceber> contasParaSalvar = Lists.newArrayList();
		for(ContaPagarReceber conta : contas){
			contasParaSalvar.add(conta);
			
//			if(conta.getValorPagoParcialTratado().signum() != 0 && conta.getValorPagoParcialTratado().compareTo(conta.getValor()) < 0){
//				ContaPagarReceber contaFilha = new ContaPagarReceber();
//				
//				contaFilha.setValor(conta.getValor().subtract(conta.getValorPagoParcialTratado()));
//				contaFilha.setContaPai(conta);
//				contaFilha.setDataConta(conta.getDataConta());
//				contaFilha.setDataVencimento(conta.getDataVencimento());
//				contaFilha.setObservacoes(conta.getObservacoes());
//				contaFilha.setOrigem(conta.getOrigem());
//				contaFilha.setTipo(conta.getTipo());
//				
//				contasParaSalvar.add(contaFilha);
//			} 
//
//			conta.setValorPagoParcial(conta.getValor());
			
			if(conta.getDataPagamento() == null)
				throw new Exception("A conta não possui data de pagamento.");
			if(conta.getMovimentacao() == null)
				throw new Exception("A conta não possui movimentação");
//			if(conta.getValorPagoParcial().signum() == 0)
//				throw new Exception("O valor pago parcial não pode ser igual a zero");

			contasParaSalvar.add(conta);
		}
		
		salvar(contasParaSalvar);
	}
	
	public void estornarContas(List<ContaPagarReceber> contas) throws Exception{
		List<ContaPagarReceber> contasToMerge = Lists.newArrayList();
		
		for(ContaPagarReceber c : contas){
//			if(c.getContaPai() == null){
				zerarConta(c);
				
				contasToMerge.add(c);
//			} else {
//				ContaPagarReceber contaPai = c.getContaPai();
//				zerarConta(contaPai);
//
//				contasToDelete.add(c);
//				contasToMerge.add(contaPai);
//			}
		}
		
		salvar(contasToMerge);
	}

	private void zerarConta(ContaPagarReceber c) {
		c.setDataPagamento(null);
		c.setMovimentacao(null);
//		c.setValorPagoParcial(BigDecimal.ZERO);
		c.setValorJurosDesconto(BigDecimal.ZERO);
	}
	
	public List<Pendencia> findByDataVencimento(Date dataHoje){
		return getRepository().findByDataPagamentoIsNullAndDataVencimentoLessThan(dataHoje);
	}
	
	public Long findPendencia(Date data){
		return getRepository().findPendencia(data);
	}
	
	public List<ContaPagarReceber> buscarContas(Date dataInicio, Date dataFinal){
		return getRepository().buscarContas(dataInicio, dataFinal);
	}

	public void abrirTela() {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(PgtoRecContaView.ID);
		} catch (PartInitException e) {
			log.error("Erro ao abrir view de pagamento de contas.");
		}
	}

	@Override
	public void removerAtivarOuDesativar(ContaPagarReceber registro) throws Exception {
		if(registro.isJaPagaRecebida())
			throw new ContaJaPagaRecebidaException();
		super.removerAtivarOuDesativar(registro);
	}
	
	public List<ContaPagarReceber> gerarParcelas(int numParcelas, BigDecimal valor, Date dataInicio, int tipo, Date dataConta, String observacoes, OrigemConta tipoConta){
		List<ContaPagarReceber> contas = Lists.newArrayList();
		
		for(int i = 0; i < numParcelas ; i++){
			ContaPagarReceber conta = new ContaPagarReceber();
			
			Calendar c = Calendar.getInstance();
			c.setTime(dataInicio);
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) + i);
			
			conta.setDataVencimento(c.getTime());
			conta.setDataConta(dataConta);
			conta.setOrigem(tipoConta);
			conta.setValor(valor);
			conta.setTipo(tipo);
			conta.setObservacoes(observacoes);
			
			contas.add(conta);
		}
		
		return contas;
	}
	
	public ContaPagarReceber findByMovimentacao(MovimentacaoContabil mov){
		return getRepository().findByMovimentacao(mov);
	}
	
}
