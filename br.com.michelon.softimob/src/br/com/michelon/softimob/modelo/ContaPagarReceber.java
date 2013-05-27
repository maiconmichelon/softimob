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
import javax.validation.constraints.NotNull;

import org.eclipse.ui.IEditorInput;

@Entity
public class ContaPagarReceber implements Serializable, Conta{

	private static final long serialVersionUID = 1L;

	public static final char PAGAR = 'P';
	public static final char RECEBER = 'R';
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
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

	@Column
	private String observacoes;
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public String getObservacoes() {
		return observacoes;
	}
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void efetuarBaixa(MovimentacaoContabil movimentacao2) {
		// TODO Auto-generated method stub
	}

	@Override
	public Date getDataGeracao() {
		return dataConta;
	}

	@Override
	public Date getDataFechamento() {
		return dataPagamento;
	}

	@Override
	public String getDescricao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIdEditor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEditorInput getEditorInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovimentacaoContabil gerarMovimentacao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pagarConta() {
		// TODO Auto-generated method stub
	}

	@Override
	public void estornar() {
		// TODO Auto-generated method stub
	}
}
