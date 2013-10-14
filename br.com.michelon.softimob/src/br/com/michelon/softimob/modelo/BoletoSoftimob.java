package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeCobranca;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;

import br.com.michelon.softimob.aplicacao.exception.ClienteIncompletoException;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;

@Entity
public class BoletoSoftimob implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private BancosSuportados banco;

	@Column
	private Integer codigoAgencia;

	@Column
	private Integer carteira;

	@Column
	private String digitoAgencia;

	@Column
	private String digitoConta;

	@Column
	private Integer codigoConta;
	
	@ManyToOne
	private Cliente cliente;
	
	@Column
	private String razaoSocialCedente;
	
	@Column
	private String cnpjCedente;
	
	@Column
	private String localPagamento;
	
	@Column
	private String instrucaoSacado;
	
	@Column
	private String instrucaoExtra;

	@Column
	private String numeroDoDocumento;

	@Column(precision = 12, scale = 2)
	private BigDecimal valor;

	@Temporal(TemporalType.DATE)
	private Date dataDocumento;

	@Temporal(TemporalType.DATE)
	private Date dataVencimento;

	@Column(precision = 12, scale = 2)
	private BigDecimal desconto;

	@Column(precision = 12, scale = 2)
	private BigDecimal mora;

	@Enumerated(EnumType.STRING)
	private TipoDeTitulo tipoDeDocumento;

	@Column(precision = 12, scale = 2)
	private BigDecimal deducao;

	@Column(precision = 12, scale = 2)
	private BigDecimal acrecimo;

	@Column(precision = 12, scale = 2)
	private BigDecimal valorCobrado;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private ContaPagarReceber conta;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BancosSuportados getBanco() {
		return banco;
	}

	public void setBanco(BancosSuportados banco) {
		this.banco = banco;
	}

	public Integer getCodigoAgencia() {
		return codigoAgencia;
	}

	public void setCodigoAgencia(Integer codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	public Integer getCarteira() {
		return carteira;
	}

	public void setCarteira(Integer carteira) {
		this.carteira = carteira;
	}

	public String getDigitoAgencia() {
		return digitoAgencia;
	}

	public void setDigitoAgencia(String digitoAgencia) {
		this.digitoAgencia = digitoAgencia;
	}

	public String getDigitoConta() {
		return digitoConta;
	}

	public void setDigitoConta(String digitoConta) {
		this.digitoConta = digitoConta;
	}

	public Integer getCodigoConta() {
		return codigoConta;
	}

	public void setCodigoConta(Integer codigoConta) {
		this.codigoConta = codigoConta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getRazaoSocialCedente() {
		return razaoSocialCedente;
	}

	public void setRazaoSocialCedente(String razaoSocialCedente) {
		this.razaoSocialCedente = razaoSocialCedente;
	}

	public String getCnpjCedente() {
		return cnpjCedente;
	}

	public void setCnpjCedente(String cnpjCedente) {
		this.cnpjCedente = cnpjCedente;
	}

	public String getLocalPagamento() {
		return localPagamento;
	}

	public void setLocalPagamento(String localPagamento) {
		this.localPagamento = localPagamento;
	}

	public String getInstrucaoSacado() {
		return instrucaoSacado;
	}

	public void setInstrucaoSacado(String instrucaoSacado) {
		this.instrucaoSacado = instrucaoSacado;
	}

	public String getInstrucaoExtra() {
		return instrucaoExtra;
	}

	public void setInstrucaoExtra(String instrucaoExtra) {
		this.instrucaoExtra = instrucaoExtra;
	}

	public String getNumeroDoDocumento() {
		return numeroDoDocumento;
	}

	public void setNumeroDoDocumento(String numeroDoDocumento) {
		this.numeroDoDocumento = numeroDoDocumento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getMora() {
		return mora;
	}

	public void setMora(BigDecimal mora) {
		this.mora = mora;
	}

	public TipoDeTitulo getTipoDeDocumento() {
		return tipoDeDocumento;
	}

	public void setTipoDeDocumento(TipoDeTitulo tipoDeDocumento) {
		this.tipoDeDocumento = tipoDeDocumento;
	}

	public BigDecimal getDeducao() {
		return deducao;
	}

	public void setDeducao(BigDecimal deducao) {
		this.deducao = deducao;
	}

	public BigDecimal getAcrecimo() {
		return acrecimo;
	}

	public void setAcrecimo(BigDecimal acrecimo) {
		this.acrecimo = acrecimo;
	}

	public BigDecimal getValorCobrado() {
		return valorCobrado;
	}

	public void setValorCobrado(BigDecimal valorCobrado) {
		this.valorCobrado = valorCobrado;
	}

	public ContaPagarReceber getConta() {
		return conta;
	}
	
	public void setConta(ContaPagarReceber conta) {
		this.conta = conta;
	}
	
	private void setDadosBoleto(ParametrosEmpresa param, ContaPagarReceber conta){
		numeroDoDocumento = conta.getId().toString();
		
		valor = conta.getValor();

		dataDocumento = new Date();
		dataVencimento = conta.getDataVencimento();		
		desconto = conta.getValorJurosDesconto().signum() > 0 ? BigDecimal.ZERO : conta.getValorJurosDesconto().abs();
		mora = conta.getValorJurDescZeroCasoNegativo();

		tipoDeDocumento = TipoDeTitulo.DM_DUPLICATA_MERCANTIL;
		deducao = BigDecimal.ZERO;
		acrecimo = BigDecimal.ZERO;
		
		valorCobrado = conta.getValor().add(conta.getValorJurosDesconto());
	}
	
	private void setDadosInstrucoes(ParametrosEmpresa param){
		localPagamento = param.getLocalPagamento();
		instrucaoSacado = param.getInstrucaoSacado();
		instrucaoExtra = param.getInstrucaoExtra();
	}
	
	private void setDadosCedente(ParametrosEmpresa param) {
		razaoSocialCedente = param.getRazaoSocial();
		cnpjCedente = param.getCnpj();
	}
	
	private void setDadosAgencia(ParametrosEmpresa param) throws Exception{
		if(param.getContaCorrente().concat(param.getDigitoContaCorrente().toString()).length() > 10)
			throw new Exception("A conta parametrizada deve ter no máximo 10 digitos.");
		
		banco = BancosSuportados.BANCOOB;
		codigoConta = Integer.parseInt(param.getContaCorrente());
		digitoConta = param.getDigitoContaCorrente().toString();
		carteira = 1;
		codigoAgencia = Integer.parseInt(param.getAgencia());
		digitoAgencia = param.getDigitoAgencia().toString();
	}
	
	public static BoletoSoftimob insertData(BoletoSoftimob boletoSoftimob, ParametrosEmpresa param, Cliente cliente, ContaPagarReceber conta) throws Exception {
		boletoSoftimob.setConta(conta);
		boletoSoftimob.setCliente(cliente);
		boletoSoftimob.setDadosAgencia(param);
		boletoSoftimob.setDadosBoleto(param, conta);
		boletoSoftimob.setDadosCedente(param);
		boletoSoftimob.setDadosInstrucoes(param);
		
		return boletoSoftimob;
	}
	
	public Boleto createBoletoJRImun() throws ClienteIncompletoException{
		Sacado sacado;
		try {
			sacado = crieUmSacado();
		} catch (NullPointerException ne) {
			throw new ClienteIncompletoException("É necessário que todos os dados do cliente estejam preenchidos.");
		}
		
		return crieOsDadosDoNovoBoleto(new Boleto(crieOsDadosDoNovoTitulo(new Titulo(crieUmaContaBancaria(), sacado, crieUmCedente()))));
	}
	
	private String getNossoNumeroFormatado(){
		Calendar c = Calendar.getInstance();
		c.setTime(dataDocumento);
		String ano = String.valueOf(c.get(Calendar.YEAR));
		int length = ano.length();
		
		String nossoNumero = FormatterHelper.formatarSeisNumeros(String.valueOf(getId()));
		nossoNumero = ano.substring(length - 2, length) + nossoNumero;
		return nossoNumero;
	}
	
	public static Long extractNossoNumero(String nossoNumero) {
		return Long.parseLong(nossoNumero.substring(2, nossoNumero.length()));
	}
	
	private ContaBancaria crieUmaContaBancaria() {
		ContaBancaria contaBancaria = new ContaBancaria(getBanco().create());
		contaBancaria.setNumeroDaConta(new NumeroDaConta(getCodigoConta(), getDigitoConta()));
		contaBancaria.setCarteira(new Carteira(getCarteira(), TipoDeCobranca.SEM_REGISTRO));
		contaBancaria.setAgencia(new Agencia(getCodigoAgencia(), getDigitoAgencia()));

		return contaBancaria;
	}

	private Cedente crieUmCedente() {
		return new Cedente(getRazaoSocialCedente(), getCnpjCedente());
	}

	// Quem paga o boleto.
	private Sacado crieUmSacado() throws NullPointerException{
		Cliente cli = getCliente();
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
	
	private Titulo crieOsDadosDoNovoTitulo(Titulo titulo) {
		
		titulo.setNumeroDoDocumento(getNumeroDoDocumento());
		
		titulo.setValor(getValor());

		titulo.setDataDoDocumento(getDataDocumento());
		titulo.setDataDoVencimento(getDataVencimento());		
		titulo.setDesconto(getDesconto());
		titulo.setMora(getMora());

		titulo.setTipoDeDocumento(getTipoDeDocumento());
		titulo.setAceite(Aceite.N);
		titulo.setDeducao(getDeducao());
		titulo.setAcrecimo(getAcrecimo());
		
		titulo.setValorCobrado(getValorCobrado());

		titulo.setDigitoDoNossoNumero(getDigitioVerificador(getNossoNumeroFormatado()));
		titulo.setNossoNumero(getNossoNumeroFormatado());
		
		return titulo;
	}
	
	public static String getDigitioVerificador(String nossoNumero){
		String[] cs = nossoNumero.split("");
		boolean m = false;
		
		int soma = 0;
		for(String s : cs)  {
			if(!s.isEmpty()) {
				int val = Integer.parseInt(s);
				soma += !m ? val : val*2;
				m = !m;
			}
		}
		
		return String.valueOf(10 - (soma % 10));
	}
	
	private Boleto crieOsDadosDoNovoBoleto(Boleto boleto) {
		
		boleto.setLocalPagamento(getLocalPagamento());
		boleto.setInstrucaoAoSacado(getInstrucaoSacado());
		boleto.setInstrucao1(getInstrucaoExtra());
		
		return boleto;
	}
	
}