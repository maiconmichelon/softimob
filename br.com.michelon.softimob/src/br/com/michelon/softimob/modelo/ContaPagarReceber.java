package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class ContaPagarReceber implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final char PAGAR = 'P';
	public static final char RECEBER = 'R';
	
	@NotNull(message = "O valor da conta não pode ser vazio.")
	@Column(precision = 14, scale = 2, nullable = false)
	private BigDecimal valor;
	
	@Column(precision = 14, scale = 2)
	private BigDecimal valorPagoParcial;
	
	@Column(precision = 14, scale = 2)
	private BigDecimal valorJurosDesconto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConta;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVencimento;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPagamento;

	@ManyToOne
	private OrigemConta origem;
	
	@NotNull(message = "Selecione o tipo da conta.")
	@Column(length = 1, nullable = false)
	private Character tipo;
	
	@ManyToOne
	private MovimentacaoContabil movimentacao;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorPagoParcial() {
		return valorPagoParcial;
	}

	public void setValorPagoParcial(BigDecimal valorPagoParcial) {
		this.valorPagoParcial = valorPagoParcial;
	}

	public BigDecimal getValorJurosDesconto() {
		return valorJurosDesconto;
	}

	public void setValorJurosDesconto(BigDecimal valorJurosDesconto) {
		this.valorJurosDesconto = valorJurosDesconto;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public OrigemConta getOrigem() {
		return origem;
	}

	public void setOrigem(OrigemConta origem) {
		this.origem = origem;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public MovimentacaoContabil getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(MovimentacaoContabil movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Date getDataConta() {
		return dataConta;
	}

	public void setDataConta(Date dataConta) {
		this.dataConta = dataConta;
	}

	public void efetuarBaixa(MovimentacaoContabil movimentacao2) {
		// TODO Auto-generated method stub
		
	}
	
}
