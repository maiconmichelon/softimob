package br.com.michelon.softimob.aplicacao.helper;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Emissor;
import br.com.caelum.stella.boleto.Sacado;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.ParametrosEmpresa;

public class BoletoHelper {

	public static void gerarBoleto(ContaPagarReceber conta) {
		
		ParametrosEmpresa parametroEmpresa = ParametrosEmpresa.getInstance();
		Calendar c = Calendar.getInstance();
		
		Emissor emissor = Emissor
				.novoEmissor()
				.comCedente(parametroEmpresa.getRazaoSocial())
				.comAgencia(parametroEmpresa.getAgencia())
				.comDigitoAgencia(parametroEmpresa.getDigitoAgencia())
				.comContaCorrente(parametroEmpresa.getContaCorrente())
				.comDigitoContaCorrente(parametroEmpresa.getDigitoContaCorrente())
				.comNumeroConvenio(parametroEmpresa.getNumeroConvenio())
				.comCarteira(parametroEmpresa.getCarteira())
				.comNossoNumero(parametroEmpresa.getNossoNumero());
		
		Sacado sacado = Sacado.novoSacado().comNome("Paulo Silveira");
		
		Datas datas = Datas.novasDatas();
		datas.comProcessamento(c);
		
		c.setTime(conta.getDataConta());
		datas.comDocumento(c);
		
		c.setTime(conta.getDataVencimento());
		datas.comVencimento(c);
		
		Boleto boleto = Boleto.novoBoleto().comDatas(datas).comEmissor(emissor)
				.comBanco(parametroEmpresa.getBanco().getBanco()).comSacado(sacado).comValorBoleto(conta.getValor())
				.comNumeroDoDocumento("1");

		new GeradorDeBoleto(boleto).geraPDF("arquivo.pdf");
		try {
			Desktop.getDesktop().open(new File("arquivo.pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
