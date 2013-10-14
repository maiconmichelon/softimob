package br.com.michelon.softimob.modelo;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class ArquivoRetorno {

	private String nossoNumero;
	private Float valor;
	private Float valorPago;
	private Date dataPagamento;

	public ArquivoRetorno() {
	}

	public ArquivoRetorno(String nossoNumero, Float valor, Float valorPago, Date dataPagamento) {
		this.nossoNumero = nossoNumero;
		this.valor = valor;
		this.valorPago = valorPago;
		this.dataPagamento = dataPagamento;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Float getValorPago() {
		return valorPago;
	}

	public void setValorPago(Float valorPago) {
		this.valorPago = valorPago;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nossoNumero == null) ? 0 : nossoNumero.hashCode());
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
		ArquivoRetorno other = (ArquivoRetorno) obj;
		if (nossoNumero == null) {
			if (other.nossoNumero != null)
				return false;
		} else if (!nossoNumero.equals(other.nossoNumero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nossoNumero + " - " + valor + " - Recebido: " + valorPago + " - " + dataPagamento;
	}
}
