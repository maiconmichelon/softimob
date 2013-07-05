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
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "movimentacao")
	private List<LancamentoContabil> lancamentos = Lists.newArrayList();

	@NotNull(message = "A data da movimentação não pode ser vazia.")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date data;

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
	
}
