package br.com.michelon.softimob.aplicacao.helper;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;

import br.com.michelon.softimob.aplicacao.exception.ListException;
import br.com.michelon.softimob.aplicacao.exception.ParametroNaoInformadoException;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.ParametrosEmpresa;
import br.com.michelon.softimob.modelo.PessoaFisica;
import br.com.michelon.softimob.modelo.PessoaJuridica;

import com.google.common.collect.Lists;

public class BoletoHelper {

	private static final String NAME_FILE_BOLETO = "boleto.pdf";
	private static File diretorioArquivos;
	private static Logger log = Logger.getLogger(BoletoHelper.class);
	
	public static void gerarBoleto(ContaPagarReceber conta, Cliente cliente) throws Exception {
		
		ParametrosEmpresa parametroEmpresa = ParametrosEmpresa.getInstance();
		Calendar c = Calendar.getInstance();
		
		List<Exception> exc = Lists.newArrayList();
		
		if(parametroEmpresa.getAgencia() == null)
			exc.add(new ParametroNaoInformadoException("A agência do banco deve ser parametrizada."));
		if(parametroEmpresa.getDigitoAgencia() == null)
			exc.add(new ParametroNaoInformadoException("O digito da agência deve ser parametrizada."));
		if(parametroEmpresa.getDigitoContaCorrente() == null)
			exc.add(new ParametroNaoInformadoException("O digito da conta corrente deve ser parametrizada."));
		if(parametroEmpresa.getContaCorrente() == null)
			exc.add(new ParametroNaoInformadoException("A conta corrente deve ser parametrizada."));
		if(parametroEmpresa.getNumeroConvenio() == null)
			exc.add(new ParametroNaoInformadoException("O número do convênio deve ser parametrizado."));
		if(parametroEmpresa.getCarteira() == null)
			exc.add(new ParametroNaoInformadoException("A carteira deve ser parametrizado."));
		
		if(!exc.isEmpty())
			throw new ListException(exc);
		
		c.setTime(conta.getDataConta());
		c.setTime(conta.getDataVencimento());

		ContaBancaria contaBancaria = crieUmaContaBancaria(parametroEmpresa);
		Cedente cedente = crieUmCedente(parametroEmpresa); 
		
		Sacado sacado = null;
		try {
			sacado = crieUmSacado(cliente);
		} catch (NullPointerException ne) {
			throw new Exception("É necessário que o Cliente tenha tenha seu endereço informado em seu cadastro.");
		}
		
		Titulo titulo = crieOsDadosDoNovoTitulo(new Titulo ( contaBancaria, sacado, cedente ), conta);
		final Boleto boleto = crieOsDadosDoNovoBoleto(new Boleto(titulo), parametroEmpresa);
        
		Job job = new Job("Gerando boleto.") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Gerando boleto ...", -1);
				
				diretorioArquivos = FileHelper.criarDiretorioArquivos();
				File file = new File(diretorioArquivos + "/" + NAME_FILE_BOLETO);
				
				BoletoViewer boletoViewer = new BoletoViewer(boleto);
				boletoViewer.getPdfAsFile(file);
				
				try {
					Desktop.getDesktop().open(file);
				} catch (IOException e) {
					log.error("Erro ao abrir boleto.", e);
				}
				
				return Status.OK_STATUS;
			}
		};

		job.setUser(true);
		job.schedule();
	}

	private static Boleto crieOsDadosDoNovoBoleto(Boleto boleto, ParametrosEmpresa param) {
		
		boleto.setLocalPagamento(param.getLocalPagamento());
		boleto.setInstrucaoAoSacado(param.getInstrucaoSacado());
		boleto.setInstrucao1(param.getInstrucaoExtra());
		
		return boleto;
	}

	private static Titulo crieOsDadosDoNovoTitulo(Titulo titulo, ContaPagarReceber conta) {
		
		titulo.setNumeroDoDocumento("78180");
		
		Calendar c = Calendar.getInstance();
		String ano = String.valueOf(c.get(Calendar.YEAR));
		int length = ano.length();
		
		String nossoNumero = formatarSeisNumeros(String.valueOf(conta.getId()));
		nossoNumero = ano.substring(length - 2, length) + nossoNumero;
		
		titulo.setNossoNumero(nossoNumero);
		titulo.setValor(conta.getValor());

		titulo.setDataDoDocumento(new Date());
		titulo.setDataDoVencimento(conta.getDataVencimento());		
		titulo.setDesconto(conta.getValorJurosDesconto().signum() > 0 ? BigDecimal.ZERO : conta.getValorJurosDesconto().abs());
		titulo.setMora(conta.getValorJurDescZeroCasoNegativo());

		titulo.setTipoDeDocumento(TipoDeTitulo.OUTROS);
		titulo.setAceite(Aceite.A);
		titulo.setDeducao(BigDecimal.ZERO);
		titulo.setAcrecimo(BigDecimal.ZERO);
		
		titulo.setValorCobrado(conta.getValor().add(conta.getValorJurosDesconto()));

		return titulo;
	}
	
	// Quem paga o boleto.
	private static Sacado crieUmSacado(Cliente cli) throws NullPointerException{
		String cpfCnpj = cli instanceof PessoaFisica ? ((PessoaFisica)cli).getCpf() : ((PessoaJuridica)cli).getCnpj();
		Sacado sacado = new Sacado(cli.getNome(), cpfCnpj);

		// Informando o endereço do sacado.
		Endereco enderecoSac = new Endereco();
		enderecoSac.setUF(UnidadeFederativa.valueOfSigla(cli.getEndereco().getRua().getBairro().getCidade().getEstado().getUf()));
		enderecoSac.setLocalidade(cli.getEndereco().getRua().getBairro().getCidade().getNome());
		enderecoSac.setCep(new CEP(cli.getEndereco().getCep()));
		enderecoSac.setBairro(cli.getEndereco().getRua().getBairro().getNome());
		enderecoSac.setLogradouro(cli.getEndereco().getRua().getNome());
		enderecoSac.setNumero(cli.getEndereco().getNumero());
		sacado.addEndereco(enderecoSac);

		return sacado;
	}

	// Quem recebe o valor do boleto
	private static Cedente crieUmCedente(ParametrosEmpresa param) {
		return new Cedente(param.getRazaoSocial(), param.getCnpj());
	}

	private static ContaBancaria crieUmaContaBancaria(ParametrosEmpresa param) throws Exception {
		if(param.getCarteira().length() != 1)
			throw new Exception("A carteira parametrizada para a geração do boleto deve conter apenas 1 dígito.");
		if(param.getContaCorrente().concat(param.getDigitoContaCorrente().toString()).length() > 10)
			throw new Exception("A conta parametrizada deve ter no máximo 10 digitos.");
		
		ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCOOB.create());
		contaBancaria.setNumeroDaConta(new NumeroDaConta(Integer.parseInt(param.getContaCorrente()), param.getDigitoContaCorrente().toString()));
		contaBancaria.setCarteira(new Carteira(Integer.parseInt(param.getCarteira())));
		contaBancaria.setAgencia(new Agencia(Integer.parseInt(param.getAgencia()), param.getDigitoAgencia().toString()));

		return contaBancaria;
	}

	private static String formatarSeisNumeros(String s){
		while(s.length() != 6){
			s = "0" + s;
		}
		return s;
	}
	
}
