package br.com.michelon.softimob.modelo;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

public class LancamentoContabil {
	
	public static final char CREDITO = 'C';
	public static final char DEBITO = 'D';
	
	private Character tipo;
	
	private String historico;
	
	private BigDecimal valor;
	
	private PlanoConta conta;

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
