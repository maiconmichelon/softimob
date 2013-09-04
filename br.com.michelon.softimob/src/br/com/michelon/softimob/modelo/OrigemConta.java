package br.com.michelon.softimob.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.eclipse.persistence.annotations.Index;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.michelon.softimob.aplicacao.annotation.DeactivateOnDelete;

@Entity
public class OrigemConta implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Index
	@NotEmpty(message = "Nome não pode ser vazio")
	@Column(unique = true, nullable = false)
	private String nome;

	@ManyToOne
	private PlanoConta conta;
	
	@ManyToOne
	private PlanoConta contaContraPartida;
	
	@Column
	@DeactivateOnDelete
	private Boolean ativo = true;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlanoConta getContaContraPartida() {
		return contaContraPartida;
	}

	public void setContaContraPartida(PlanoConta contaContraPartida) {
		this.contaContraPartida = contaContraPartida;
	}

	public PlanoConta getConta() {
		return conta;
	}

	public void setConta(PlanoConta conta) {
		this.conta = conta;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public String toString() {
		return nome;
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
		OrigemConta other = (OrigemConta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
