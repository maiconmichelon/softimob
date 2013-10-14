package br.com.michelon.softimob.aplicacao.helper;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import br.com.michelon.softimob.modelo.ArquivoRetorno;
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
	
	public static BoletoSoftimob preencherBoleto(BoletoSoftimob boleto, ContaPagarReceber conta, Cliente cliente) throws Exception {
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
		
		if(!exc.isEmpty())
			throw new ListException(exc);
		
		c.setTime(conta.getDataConta());
		c.setTime(conta.getDataVencimento());

		return BoletoSoftimob.insertData(boleto, parametroEmpresa, cliente, conta);
	}
	
	public static void gerarBoleto(ContaPagarReceber conta, Cliente cliente) throws Exception {
		BoletoSoftimobService service = new BoletoSoftimobService();
		BoletoSoftimob boleto = service.findByConta(conta);
		
		
		if(boleto == null)
			boleto = new BoletoSoftimob();
		preencherBoleto(boleto, conta, cliente);
		
		service.salvar(boleto);
		
		gerarBoleto(boleto.createBoletoJRImun());
	}

	public static List<ArquivoRetorno> getRetorno(File file) throws Exception {
		List<ArquivoRetorno> retornos = Lists.newArrayList();
		List<RegT400> regs = getRegistros(file);
		
		ArquivoRetorno retorno = new ArquivoRetorno();
		for(RegT400 r : regs) {
			if (r instanceof RegT400) {
				retorno.setNossoNumero(((RegT400) r).getIdentTitulo());
				retorno.setValorPago(((RegT400) r).getVlrPago().floatValue());
				retorno.setDataPagamento(((RegT400) r).getDtDesc());
				
				retornos.add(retorno);
				retorno = new ArquivoRetorno();
			}
		}
		return retornos;
	}
	
	private static List<RegT400> getRegistros(File file) throws Exception {
		FileReader fr = new FileReader(file);
		List<RegT400> regs = Lists.newArrayList();
		leArquivo(fr, regs);
		
		return regs;
	}

	private static boolean leArquivo(final FileReader fileReaderCnab, final List<RegT400> list) throws IOException {
		
		boolean retorno = true;
		char tipo;
		
		String line = null;
		BufferedReader in = new BufferedReader(fileReaderCnab);
		try {
			while ((line = in.readLine()) != null) {
				tipo = line.charAt(0);

				switch (tipo) {
				case '1':
					RegT400 reg1 = new RegT400();
					reg1.parseLine(line);
					list.add(reg1);
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
		}

		in.close();

		return retorno;
	}
	
	public static Date stringDDMMAAToDate(final String str) throws Exception {
		Date dt = null;
		Calendar calendar = new GregorianCalendar();

		calendar = Calendar.getInstance();

		if (str != null && str.trim().length() > 5) {
			int dia = Integer.parseInt(str.substring(0, 2));
			int mes = Integer.parseInt(str.substring(2, 4));

			String an = calendar.get(Calendar.YEAR) + "";
			int ano = Integer.parseInt(an.substring(0, 2) + str.substring(4));

			if (dia > 0 && mes > 0 && ano > 0)
				dt = parseDate(ano, mes, dia);
		}

		return dt;
	}

	private static Date parseDate(int a, int m, int d) {
		Date dtRetorno = new Date();
		GregorianCalendar gcSel = new GregorianCalendar();
		
		try {
			gcSel.setTime(dtRetorno);
			gcSel.set(Calendar.YEAR, a);
			gcSel.set(Calendar.MONTH, m - 1);
			gcSel.set(Calendar.DAY_OF_MONTH, d);
			dtRetorno = gcSel.getTime();
		} finally {
			gcSel = null;
		}
		
		return dtRetorno;
	}
	
	public static BigDecimal stringToBigDecimal(final String str) throws Exception {
		BigDecimal bg = null;

		if (str != null) {
			StringBuilder v = new StringBuilder();
			char chars[] = str.trim().toCharArray();
			if (chars.length >= 3) {
				int indexDecimal = (chars.length - 1) - 2;
				for (int i = 0; i < chars.length; i++) {
					v.append(chars[i]);
					if (i == indexDecimal) {
						v.append('.');
					}
				}
				bg = new BigDecimal(v.toString());
			}
		}

		return bg;
	}

	public static class RegT400 {

		private String identTitulo;
		private BigDecimal vlrPago;
		private Date dtDesc;

		public String getIdentTitulo() {
			return identTitulo;
		}

		public void setIdentTitulo(final String identTitulo) {
			this.identTitulo = identTitulo;
		}

		public BigDecimal getVlrPago() {
			return vlrPago;
		}

		public void setVlrPago(BigDecimal vlrPago) {
			this.vlrPago = vlrPago;
		}

		public Date getDtDesc() {
			return dtDesc;
		}

		public void setDtDesc(Date dtDesc) {
			this.dtDesc = dtDesc;
		}

		public void parseLine(final String line) throws Exception {
			try {
				if (line == null) {
					throw new Exception("CNAB registro 1.\nLinha vazia.");
				} else {
					if ("1".equals(line.substring(0, 1))) {
						setDtDesc(stringDDMMAAToDate(line.substring(110, 116)));
						setIdentTitulo(line.substring(70, 78));
						setVlrPago(stringToBigDecimal(line.substring(253, 266)));
					}
				}
			} catch (Exception e) {
				throw new Exception("CNAB registro 1.\nErro ao ler registro.\n" + e.getMessage());
			}
		}

	}
	
}
