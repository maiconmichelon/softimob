package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

@Entity
public class MovimentacaoContabil implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length = 14, scale = 2, nullable = false)
	private BigDecimal valor;
	
	@NotEmpty(message = "A movimentação deve conter lançamentos.")
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
	private List<LancamentoContabil> lancamentos = Lists.newArrayList();

	@NotNull(message = "A data da movimentação não pode ser vazia.")
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date data = new Date();

	@OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
	@br.com.michelon.softimob.aplicacao.annotation.Log
	private Log log = new Log();
	
	public Log getLog() {
		return log;
	}
	
	public void setLog(Log log) {
		this.log = log;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public List<LancamentoContabil> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<LancamentoContabil> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	void setLancamentosDebito(){
		
	}
	
	void setLancamentosCredito(){
		
	}
	
	public List<LancamentoContabil> getLancamentosCredito() {
		return Lists.newArrayList(Collections2.filter(lancamentos, new Predicate<LancamentoContabil>() {
			@Override
			public boolean apply(LancamentoContabil arg0) {
				return arg0.isCredito();
			}
		}));
	}
	
	public List<LancamentoContabil> getLancamentosDebito() {
		return Lists.newArrayList(Collections2.filter(lancamentos, new Predicate<LancamentoContabil>() {
			@Override
			public boolean apply(LancamentoContabil arg0) {
				return arg0.isDebito();
			}
		}));
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimentacaoContabil other = (MovimentacaoContabil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
