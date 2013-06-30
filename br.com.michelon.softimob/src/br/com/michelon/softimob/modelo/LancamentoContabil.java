package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LancamentoContabil implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private TipoLancamento tipo;
	
	@Column
	private String historico;
	
	@Column
	private String complemento;
	
	@Column(scale=2, length=14)
	private BigDecimal valor;
	
	@ManyToOne
	private PlanoConta conta;
	
	@ManyToOne(optional = false)
	private final MovimentacaoContabil movimentacao;

	public LancamentoContabil(MovimentacaoContabil movimentacao){
		this.movimentacao = movimentacao;
	}
	
	@SuppressWarnings("unused")
	private LancamentoContabil(){
		this(null);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}
	
	public MovimentacaoContabil getMovimentacao() {
		return movimentacao;
	}
	
	public boolean isDebito(){
		return TipoLancamento.DEBITO.equals(tipo);
	}
	
	public boolean isCredito(){
		return TipoLancamento.CREDITO.equals(tipo);
	}
	
	public static LancamentoContabil createDebito(MovimentacaoContabil mov, PlanoConta contaDebito, BigDecimal valor, String historico, String complemento) {
		return create(mov, TipoLancamento.DEBITO, contaDebito, valor, historico, complemento);
	}
	
	public static LancamentoContabil createCredito(MovimentacaoContabil mov, PlanoConta contaCredito, BigDecimal valor, String historico, String complemento) {
		return create(mov, TipoLancamento.CREDITO, contaCredito, valor, historico, complemento);
	}
	
	public static LancamentoContabil create(MovimentacaoContabil mov, TipoLancamento tipo, PlanoConta conta, BigDecimal valor, String historico, String complemento) {
		LancamentoContabil lcto = new LancamentoContabil(mov);
		
		lcto.setTipo(tipo);
		lcto.setComplemento(complemento);
		lcto.setConta(conta);
		lcto.setValor(valor);
		lcto.setHistorico(historico);
		
		return lcto;
	}

	public enum TipoLancamento{
		
		CREDITO("Crédito"),
		DEBITO("Débito");
		
		private String descricao;
		
		private TipoLancamento(String descricao) {
			this.descricao = descricao;
		}
		
		public String getDescricao() {
			return descricao;
		}
		
		@Override
		public String toString() {
			return descricao;
		}
		
	}

}
