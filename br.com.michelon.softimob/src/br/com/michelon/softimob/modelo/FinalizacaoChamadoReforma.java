package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.collect.Lists;

@Entity
public class FinalizacaoChamadoReforma implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Data de fechamento do chamado de reforma não pode ser vazia.")
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date data = new Date();
	
	@ManyToOne
	private Funcionario funcionario;
	
	@NotEmpty(message = "A descrição da finalização do chamado não pode ser vazia.")
	@Column(nullable = false)
	private String descricaoConclusao;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ContaPagarReceber> contas = Lists.newArrayList();

	@NotNull(message = "Informe se o chamado foi aceito ou recusado.")
	@Column(nullable = false)
	private Integer status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoConclusao() {
		return descricaoConclusao;
	}

	public void setDescricaoConclusao(String descricaoConclusao) {
		this.descricaoConclusao = descricaoConclusao;
	}

	public List<ContaPagarReceber> getContas() {
		return contas;
	}

	public void setContas(List<ContaPagarReceber> conta) {
		this.contas = conta;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		FinalizacaoChamadoReforma other = (FinalizacaoChamadoReforma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
