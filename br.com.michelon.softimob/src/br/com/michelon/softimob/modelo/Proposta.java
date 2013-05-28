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

@Entity
public class Proposta implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int ACEITA = 0;
	public static final int RECUSADA = 1;
	public static final int CONTRAPROPOSTA = 2;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFechamento;
	
	@ManyToOne(optional = false)
	private Cliente cliente;
	
	@ManyToOne(optional = false)
	private Funcionario funcionario;
	
	@Column(precision = 14, scale = 2)
	private BigDecimal valor;

	@Column
	private String observacoes;
	
	@Column
	private Integer status;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Proposta contraProposta;
	
	@ManyToOne()
	private Imovel imovel;

	public Proposta(Imovel imovel) {
		this.imovel = imovel;
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
	
}
