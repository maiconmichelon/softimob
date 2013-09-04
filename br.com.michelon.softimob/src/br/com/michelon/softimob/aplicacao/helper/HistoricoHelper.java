package br.com.michelon.softimob.aplicacao.helper;

import java.math.BigDecimal;
import java.util.Date;

import br.com.michelon.softimob.modelo.ContaPagarReceber;

public class HistoricoHelper {

	public static String getHistoricoPagamento(ContaPagarReceber c, Date dataBaixa){
		return String.format("%s de %s na data de %s no valor de R$%s", c.isApagar() ? "Pagamento" : "Recebimento", 
				c.getOrigem().getNome(), FormatterHelper.getSimpleDateFormat().format(dataBaixa), FormatterHelper.getDecimalFormatter().format(c.getValorMovimentacao()));
	}
	
	public static String getHistoricoJurosDesconto(ContaPagarReceber c, Date dataBaixa){
		StringBuilder sb = new StringBuilder();
		
		if(c.getValorJurDescTratado().compareTo(BigDecimal.ZERO) == 0)
			return null;
		
		if(c.getValorJurDescTratado().signum() > 0)
			sb.append(String.format("Juros %s ", c.isApagar() ? "pago " : "recebido "));
		else
			sb.append(String.format("Desconto %s ", c.isApagar() ? "recebido " : "concedido "));
		
		return sb.append(String.format("na data de %s no valor de R$ %s", 
					FormatterHelper.getSimpleDateFormat().format(dataBaixa), 
					FormatterHelper.getDecimalFormatter().format(c.getValorJurDescTratado().abs()))
				).toString();
	}
	
}
