package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Aluguel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	private Imovel imovel;
	
	@ManyToOne(optional = false)
	private Cliente inquilino;
	
	@ManyToOne()
	private Cliente fiador;
	
	@ManyToOne()
	private Funcionario vendedor;
	
	@Column(length=14, scale = 2, nullable = false)
	private BigDecimal valor;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAssinaturaContrato;
	
	@Column
	private Integer duracao;
	
	@Column
	private Integer reajuste;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Cliente getFiador() {
		return fiador;
	}

	public void setFiador(Cliente fiador) {
		this.fiador = fiador;
	}

	public Funcionario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Funcionario vendedor) {
		this.vendedor = vendedor;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataAssinaturaContrato() {
		return dataAssinaturaContrato;
	}

	public void setDataAssinaturaContrato(Date dataAssinaturaContrato) {
		this.dataAssinaturaContrato = dataAssinaturaContrato;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Integer getReajuste() {
		return reajuste;
	}

	public void setReajuste(Integer reajuste) {
		this.reajuste = reajuste;
	}

	public Cliente getInquilino() {
		return inquilino;
	}

	public void setInquilino(Cliente inquilino) {
		this.inquilino = inquilino;
	}
	
}
