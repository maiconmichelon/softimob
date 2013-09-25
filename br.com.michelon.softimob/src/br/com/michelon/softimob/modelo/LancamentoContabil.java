package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

@Entity
public class LancamentoContabil implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Informe se o lançamento é a crédito ou débito")
	@Column(nullable = false)
	private TipoLancamento tipo;
	
	@Column
	private String historico = StringUtils.EMPTY;
	
	@Column
	private String complemento = StringUtils.EMPTY;
	
	@NotNull(message = "Informe o valor do lançamento")
	@Column(scale=2, length=14, nullable = false)
	private BigDecimal valor;
	
	@NotNull(message = "Informe a conta referente do lançamento")
	@ManyToOne(optional = false)
	private PlanoConta conta;
	
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
		LancamentoContabil lcto = new LancamentoContabil();
		
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (this == obj)
			return true;
		LancamentoContabil other = (LancamentoContabil) obj;
		
		if(id == null && other.id == null)
			return tipo != null && tipo.equals(other.tipo) && valor != null && valor.equals(other.valor) && conta != null && conta.equals(other.conta);
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
