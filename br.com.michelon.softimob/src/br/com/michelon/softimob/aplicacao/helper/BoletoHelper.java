package br.com.michelon.softimob.aplicacao.helper;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;

import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Emissor;
import br.com.caelum.stella.boleto.Sacado;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import br.com.michelon.softimob.aplicacao.exception.ListException;
import br.com.michelon.softimob.aplicacao.exception.ParametroNaoInformadoException;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.ParametrosEmpresa;

import com.google.common.collect.Lists;

public class BoletoHelper {

	private static File diretorioArquivos;
	
	public static void gerarBoleto(ContaPagarReceber conta) throws ListException {
		
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
		if(parametroEmpresa.getNossoNumero() == null)
			exc.add(new ParametroNaoInformadoException("O nosso número deve ser parametrizado."));
		if(parametroEmpresa.getCarteira() == null)
			exc.add(new ParametroNaoInformadoException("A carteira deve ser parametrizado."));
		
		if(!exc.isEmpty())
			throw new ListException(exc);
		
		Emissor emissor = Emissor
				.novoEmissor()
				.comCedente(parametroEmpresa.getRazaoSocial())
				.comAgencia(Integer.parseInt(parametroEmpresa.getAgencia()))
				.comDigitoAgencia(parametroEmpresa.getDigitoAgencia())
				.comContaCorrente(Integer.parseInt(parametroEmpresa.getContaCorrente()))
				.comDigitoContaCorrente(parametroEmpresa.getDigitoContaCorrente())
				.comNumeroConvenio(Integer.parseInt(parametroEmpresa.getNumeroConvenio()))
				.comCarteira(Integer.parseInt(parametroEmpresa.getCarteira()))
				.comNossoNumero(Long.parseLong(parametroEmpresa.getNossoNumero()));
		
		Sacado sacado = Sacado.novoSacado().comNome("Paulo Silveira");
		
		Datas datas = Datas.novasDatas();
		datas.comProcessamento(c);
		
		c.setTime(conta.getDataConta());
		datas.comDocumento(c);
		
		c.setTime(conta.getDataVencimento());
		datas.comVencimento(c);
		
		final Boleto boleto = Boleto.novoBoleto().comDatas(datas).comEmissor(emissor)
				.comBanco(parametroEmpresa.getBanco().getBanco()).comSacado(sacado).comValorBoleto(conta.getValor())
				.comNumeroDoDocumento("1");

		
		Job job = new Job("Gerando boleto.") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				byte[] pdf = new GeradorDeBoleto(boleto).geraPDF();
				diretorioArquivos = FileHelper.criarDiretorioArquivos();
				FileHelper.insertIntTempFolder(diretorioArquivos, "boleto.pdf", pdf);
				
				return Status.OK_STATUS;
			}
		};

		job.addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(final IJobChangeEvent event) {
				try {
					Desktop.getDesktop().open(new File(diretorioArquivos + "/" + "boleto.pdf"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		job.setUser(true);
		job.schedule();
	}

}
