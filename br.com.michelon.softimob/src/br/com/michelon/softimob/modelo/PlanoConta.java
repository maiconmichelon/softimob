package br.com.michelon.softimob.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.Index;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.michelon.softimob.aplicacao.annotation.DeactivateOnDelete;

@Entity
public class PlanoConta implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final int ATIVA = 1;
	public static final int PASSIVA = 2;
	
	@Id @GeneratedValue
	private Long id;
	
	@Index
	@Column(unique = true, nullable = false)
	@NotEmpty(message = "O código da conta não pode ser vazio.")
	private String codigo;
	
	@Index
	@NotEmpty(message = "A descrição do plano de conta não pode ser vazio.")
	@Column(nullable = false)
	private String nome;
	
	@NotNull(message = "Informe o tipo da conta.")
	@Column(nullable = false)
	private Integer tipo;
	
	@Column
	@DeactivateOnDelete
	private Boolean ativo = true;
	
	public PlanoConta(){
		
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTipoExtenso(){
		return tipo == ATIVA ? "Ativo " : "Passivo";
	}
	
	@Override
	public String toString() {
		return String.format("%s - %s", getCodigo() , getNome());
	}

	public String getCodigoDescricao(){
		return toString();
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
		PlanoConta other = (PlanoConta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
