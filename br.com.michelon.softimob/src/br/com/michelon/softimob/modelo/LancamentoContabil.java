package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.StringUtils;

@Entity
public class LancamentoContabil implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final char CREDITO = 'C';
	public static final char DEBITO = 'D';
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Character tipo;
	
	@Column
	private String historico;
	
	@Column
	private String complemento;
	
	@Column(scale=2, length=14)
	private BigDecimal valor;
	
	@ManyToOne
	private PlanoConta conta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public PlanoConta getConta() {
		return conta;
	}

	public void setConta(PlanoConta conta) {
		this.conta = conta;
	}

	public String getTipoExtenso() {
		if(tipo == CREDITO)
			return "CRÉDITO";
		if(tipo == DEBITO)
			return "DÉBITO";
		return StringUtils.EMPTY;
	}
	
}
