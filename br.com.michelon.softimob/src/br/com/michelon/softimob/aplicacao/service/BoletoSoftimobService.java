package br.com.michelon.softimob.aplicacao.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.michelon.softimob.aplicacao.exception.ParametroNaoInformadoException;
import br.com.michelon.softimob.modelo.ArquivoRetorno;
import br.com.michelon.softimob.modelo.BoletoSoftimob;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.LancamentoContabil;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.modelo.ParametrosEmpresa;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.persistencia.BoletoSoftimobDAO;

public class BoletoSoftimobService extends GenericService<BoletoSoftimob>{

	public BoletoSoftimobService() {
		super(BoletoSoftimobDAO.class);
	}
	
	@Override
	protected BoletoSoftimobDAO getRepository() {
		return (BoletoSoftimobDAO) super.getRepository();
	}

	public BoletoSoftimob findByConta(ContaPagarReceber conta) {
		return getRepository().findByConta(conta);
	}

	public void efetuarBaixa(BoletoSoftimob boleto, ArquivoRetorno retorno) throws Exception {
		ContaPagarReceber conta = boleto.getConta();
		ParametrosEmpresa param = ParametrosEmpresa.getInstance();
		
		PlanoConta caixa = param.getContaCaixa();
		if(caixa == null )
			throw new ParametroNaoInformadoException("A conta caixa parametrizada não pode ser vazia.");
		
		MovimentacaoContabil mov = new MovimentacaoContabil();
		mov.setData(retorno.getDataPagamento());
		mov.setValor(new BigDecimal(retorno.getValorPago()));
		
		conta.setValorJurosDesconto(boleto.getMora().compareTo(BigDecimal.ZERO) > 0 ? boleto.getMora() : boleto.getDesconto().negate());
		
		conta.setDataPagamento(retorno.getDataPagamento());
		conta.setMovimentacao(mov);
		
		List<LancamentoContabil> gerarLancamentos = new ContaPagarReceberService().gerarLancamentos(conta, mov, retorno.getDataPagamento(), caixa);
		mov.setLancamentos(gerarLancamentos);
	}
}
