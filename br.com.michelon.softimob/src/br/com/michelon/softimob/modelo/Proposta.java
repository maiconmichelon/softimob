package br.com.michelon.softimob.modelo;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Proposta {

	public static final int ACEITA = 0;
	public static final int RECUSADA = 1;
	
	@Column(precision = 14, scale = 2)
	private BigDecimal valor;
	
	@Column
	private Integer status;
	
	@OneToOne
	private Proposta contraProposta;
	
	@ManyToOne
	private Imovel imovel;
	
	@ManyToOne
	private Cliente comprador;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@ManyToOne
	private Funcionario funcionario;

	private String observacoes;
	
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

	public Cliente getComprador() {
		return comprador;
	}

	public void setComprador(Cliente comprador) {
		this.comprador = comprador;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
	
}
