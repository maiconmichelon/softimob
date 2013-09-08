package br.com.michelon.softimob.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.caelum.stella.boleto.bancos.Bancos;
import br.com.michelon.softimob.aplicacao.service.ParametrosEmpresaService;

@Entity
public class ParametrosEmpresa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String razaoSocial;
	
	@Column
	@CNPJ
	private String cnpj;

	@Column
	private String creci;
	
	@OneToOne
	private ModeloContrato contratoAluguel;
	
	@OneToOne
	private ModeloContrato contratoVenda;

	@OneToOne
	private ModeloContrato contratoPrestacaoServico;
	
	@OneToOne
	private CheckList checkListAluguel;

	@OneToOne
	private CheckList checkListVenda;
	
	@OneToOne
	private CheckList checkListVistoria;
	
	@Column
	private Integer diaRecebAluguel;
	
	@Column
	private Integer diaRepasseAluguel;
	
	@Column
	private Integer diasFinalizacaoReforma;
	
	@OneToOne
	private Funcionario funcionarioResponsavelReforma;
	
	@OneToOne
	private OrigemConta tipoContaReforma;
	
	@OneToOne
	private OrigemConta tipoContaComissao;
	
	@OneToOne
	private OrigemConta tipoContaPrestacaoServico;

	@OneToOne
	private OrigemConta tipoContaAluguel;

	@OneToOne
	private PlanoConta contaDescontoConcedido;
	
	@OneToOne
	private PlanoConta contaDescontoRecebido;
	
	@OneToOne
	private PlanoConta contaJurosPagos;
	
	@OneToOne
	private PlanoConta contaJurosRecebido;
	
	@OneToOne
	private PlanoConta contaCaixa;
	
	@Enumerated(EnumType.ORDINAL)
	@Column
	private Bancos banco;
	
	@Column
	private String cedente;
	
	@Column
	private String agencia;
	
	@Column
	private Character digitoAgencia;
	
	@Column
	private String contaCorrente;
	
	@Column
	private Character digitoContaCorrente;
	
	@Column
	private String carteira;
	
	@Column
	private String numeroConvenio;
	
	@Column
	private String nossoNumero;
	
	private ParametrosEmpresa(){}
	
	private static transient ParametrosEmpresa params;
	
	public static ParametrosEmpresa getInstance(){
		try{
			if(params == null)
				params = new ParametrosEmpresaService().findParametrosEmpresa();
			return params == null ? new ParametrosEmpresa() : params;
		
		}catch(Exception e){
			return null;
		}
	}
	
	public String getCreci() {
		return creci;
	}

	public void setCreci(String creci) {
		this.creci = creci;
	}

	public PlanoConta getContaCaixa() {
		return contaCaixa;
	}
	
	public void setContaCaixa(PlanoConta contaCaixa) {
		this.contaCaixa = contaCaixa;
	}
	
	public ModeloContrato getContratoAluguel() {
		return contratoAluguel;
	}

	public void setContratoAluguel(ModeloContrato contratoAluguel) {
		this.contratoAluguel = contratoAluguel;
	}

	public CheckList getCheckListAluguel() {
		return checkListAluguel;
	}

	public void setCheckListAluguel(CheckList checkListAluguel) {
		this.checkListAluguel = checkListAluguel;
	}

	public ModeloContrato getContratoVenda() {
		return contratoVenda;
	}

	public void setContratoVenda(ModeloContrato contratoVenda) {
		this.contratoVenda = contratoVenda;
	}

	public CheckList getCheckListVenda() {
		return checkListVenda;
	}

	public void setCheckListVenda(CheckList checkListVenda) {
		this.checkListVenda = checkListVenda;
	}

	public Integer getDiaRepasseAluguel() {
		return diaRepasseAluguel;
	}

	public void setDiaRepasseAluguel(Integer diaRepasseAluguel) {
		this.diaRepasseAluguel = diaRepasseAluguel;
	}

	public Integer getDiasFinalizacaoReforma() {
		return diasFinalizacaoReforma;
	}

	public void setDiasFinalizacaoReforma(Integer diasFinalizacaoReforma) {
		this.diasFinalizacaoReforma = diasFinalizacaoReforma;
	}

	public Funcionario getFuncionarioResponsavelReforma() {
		return funcionarioResponsavelReforma;
	}

	public void setFuncionarioResponsavelReforma(Funcionario funcionarioResponsavelReforma) {
		this.funcionarioResponsavelReforma = funcionarioResponsavelReforma;
	}

	public OrigemConta getTipoContaReforma() {
		return tipoContaReforma;
	}

	public void setTipoContaReforma(OrigemConta tipoContaReforma) {
		this.tipoContaReforma = tipoContaReforma;
	}

	public OrigemConta getTipoContaComissao() {
		return tipoContaComissao;
	}

	public void setTipoContaComissao(OrigemConta tipoContaComissao) {
		this.tipoContaComissao = tipoContaComissao;
	}


	public OrigemConta getTipoContaAluguel() {
		return tipoContaAluguel;
	}
	
	public void setTipoContaAluguel(OrigemConta tipoContaAluguel) {
		this.tipoContaAluguel = tipoContaAluguel;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public ModeloContrato getContratoPrestacaoServico() {
		return contratoPrestacaoServico;
	}

	public void setContratoPrestacaoServico(ModeloContrato contratoPrestacaoServico) {
		this.contratoPrestacaoServico = contratoPrestacaoServico;
	}

	public CheckList getCheckListVistoria() {
		return checkListVistoria;
	}

	public void setCheckListVistoria(CheckList checkListVistoria) {
		this.checkListVistoria = checkListVistoria;
	}

	public Integer getDiaRecebAluguel() {
		return diaRecebAluguel;
	}

	public void setDiaRecebAluguel(Integer diaRecebAluguel) {
		this.diaRecebAluguel = diaRecebAluguel;
	}

	public OrigemConta getTipoContaPrestacaoServico() {
		return tipoContaPrestacaoServico;
	}

	public void setTipoContaPrestacaoServico(OrigemConta tipoContaPrestacaoServico) {
		this.tipoContaPrestacaoServico = tipoContaPrestacaoServico;
	}

	public PlanoConta getContaDescontoConcedido() {
		return contaDescontoConcedido;
	}

	public void setContaDescontoConcedido(PlanoConta contaDescontoConcedido) {
		this.contaDescontoConcedido = contaDescontoConcedido;
	}

	public PlanoConta getContaDescontoRecebido() {
		return contaDescontoRecebido;
	}

	public void setContaDescontoRecebido(PlanoConta contaDescontoRecebido) {
		this.contaDescontoRecebido = contaDescontoRecebido;
	}

	public PlanoConta getContaJurosPagos() {
		return contaJurosPagos;
	}

	public void setContaJurosPagos(PlanoConta contaJurosPagos) {
		this.contaJurosPagos = contaJurosPagos;
	}

	public PlanoConta getContaJurosRecebido() {
		return contaJurosRecebido;
	}

	public void setContaJurosRecebido(PlanoConta contaJurosRecebido) {
		this.contaJurosRecebido = contaJurosRecebido;
	}
	
	public Bancos getBanco() {
		return banco;
	}
	
	public void setBanco(Bancos banco) {
		this.banco = banco;
	}
	
	public String getCedente() {
		return cedente;
	}

	public void setCedente(String cedente) {
		this.cedente = cedente;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public Character getDigitoAgencia() {
		return digitoAgencia;
	}
	
	public Character getDigitoContaCorrente() {
		return digitoContaCorrente;
	}
	
	public void setDigitoAgencia(Character digitoAgencia) {
		this.digitoAgencia = digitoAgencia;
	}
	
	public void setDigitoContaCorrente(Character digitoContaCorrente) {
		this.digitoContaCorrente = digitoContaCorrente;
	}
	
	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

	public String getNumeroConvenio() {
		return numeroConvenio;
	}
	
	public void setNumeroConvenio(String numeroConvenio) {
		this.numeroConvenio = numeroConvenio;
	}
	
	public String getNossoNumero() {
		return nossoNumero;
	}
	
	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParametrosEmpresa other = (ParametrosEmpresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
