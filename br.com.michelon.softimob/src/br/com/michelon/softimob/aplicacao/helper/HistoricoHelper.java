package br.com.michelon.softimob.aplicacao.helper;

import java.util.Date;

import br.com.michelon.softimob.modelo.ContaPagarReceber;

public class HistoricoHelper {

	public static String getHistoricoPagamento(ContaPagarReceber c, Date dataBaixa){
		return String.format("%s de %s na data de %s no valor de R$%s", c.isApagar() ? "Pagamento" : "Recebimento", 
				c.getOrigem().getNome(), FormatterHelper.getSimpleDateFormat().format(dataBaixa), FormatterHelper.getDecimalFormatter().format(c.getValorMovimentacao()));
	}
	
	public static String getHistoricoJurosDesconto(ContaPagarReceber c, Date dataBaixa){
		return String.format("%s de %s na data de %s no valor de R$%s", c.isApagar() ? "Pagamento" : "Recebimento", 
				c.getValorJurDescTratado().signum() < 0 ? "desconto" : "juros", FormatterHelper.getSimpleDateFormat().format(dataBaixa), 
				FormatterHelper.getDecimalFormatter().format(c.getValorJurDescTratado()));
	}
	
}
