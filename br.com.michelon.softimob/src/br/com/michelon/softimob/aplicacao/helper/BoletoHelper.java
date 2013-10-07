package br.com.michelon.softimob.aplicacao.helper;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;

import br.com.michelon.softimob.aplicacao.exception.ListException;
import br.com.michelon.softimob.aplicacao.exception.ParametroNaoInformadoException;
import br.com.michelon.softimob.aplicacao.service.BoletoSoftimobService;
import br.com.michelon.softimob.modelo.BoletoSoftimob;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.ParametrosEmpresa;

import com.google.common.collect.Lists;

public class BoletoHelper {

	private static final String NAME_FILE_BOLETO = "boleto.pdf";
	private static File diretorioArquivos;
	private static Logger log = Logger.getLogger(BoletoHelper.class);
	
	
	public static void gerarBoleto(final Boleto boleto){
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
	
	public static BoletoSoftimob criarBoleto(ContaPagarReceber conta, Cliente cliente) throws Exception {
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

		return BoletoSoftimob.create(parametroEmpresa, cliente, conta);
	}
	
	public static void gerarBoleto(ContaPagarReceber conta, Cliente cliente) throws Exception {
		BoletoSoftimobService service = new BoletoSoftimobService();
		BoletoSoftimob boleto = service.findByConta(conta);
		
		if(boleto == null){
			boleto = criarBoleto(conta, cliente);
			service.salvar(boleto);
		}
		
		gerarBoleto(boleto.createBoletoJRImun());
	}

	public static void importarArquivoRetorno(List<String> linhas) {
		// TODO Auto-generated method stub
		
	}

}
