package br.com.michelon.softimob.modelo;

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
public class Venda {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Imovel imovel;
	
	@ManyToOne
	private Cliente comprador;
	
	@ManyToOne
	private Funcionario vendedor;
	
	@Column(length=14, scale = 2)
	private BigDecimal valor;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAssinaturaContrato;

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

	public Cliente getComprador() {
		return comprador;
	}

	public void setComprador(Cliente comprador) {
		this.comprador = comprador;
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

}