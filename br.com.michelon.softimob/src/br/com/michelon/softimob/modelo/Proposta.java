package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
public class Proposta implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	public static final int ACEITA = 0;
	public static final int RECUSADA = 1;
	public static final int CONTRAPROPOSTA = 2;

	public static final int CONTRA_PROPOSTA_PROPRIETARIO = 3;
	public static final int CONTRA_PROPOSTA_CLIENTE = 4;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotNull(message = "Informe a data que foi feita a proposta.")
	@Past(message="A data informada referente a criação da proposta esta incorreta.")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Past(message="A data informada para a data de fechamento da proposta esta incorreta.")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFechamento;
	
	@NotNull(message="Informe o cliente que fez a proposta.")
	@ManyToOne(optional = false)
	private Cliente cliente;
	
	@ManyToOne()
	private Funcionario funcionario;
	
	@NotNull(message = "Informe o valor da proposta.")
	@Column(precision = 14, scale = 2)
	private BigDecimal valor;

	@Column
	private String observacoes;
	
	@Column
	private Integer status;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Proposta contraProposta;
	
	@Column
	private Integer tipoContraProposta;
	
	@ManyToOne()
	private Imovel imovel;
	
	public Proposta(Imovel imovel) {
		this.imovel = imovel;
	}
	
	@SuppressWarnings("unused")
	private Proposta(){}
	
	public Proposta(Proposta contraProposta) {
		this.contraProposta = contraProposta;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Proposta getContraProposta() {
		return contraProposta;
	}

	public void setContraProposta(Proposta contraProposta) {
		this.contraProposta = contraProposta;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getTipoContraProposta() {
		return tipoContraProposta;
	}

	public void setTipoContraProposta(Integer tipoContraProposta) {
		this.tipoContraProposta = tipoContraProposta;
	}

	public String getRealizador() {
		return tipoContraProposta == CONTRA_PROPOSTA_CLIENTE ? "Cliente - " + getCliente().getNome() : 
			"Proprietário - " + getImovel().getProprietario().getNome();
	}
	
}
