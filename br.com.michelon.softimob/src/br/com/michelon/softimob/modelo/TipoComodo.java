package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eclipse.persistence.annotations.Index;

import br.com.michelon.softimob.aplicacao.annotation.DeactivateOnDelete;

import com.google.common.collect.Lists;

@Entity
public class TipoComodo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Index
	@NotNull(message = "O nome do Cômodo não pode ser vazio")
	@Column(nullable = false, unique = true)
	private String nome;

	@Size(min = 1, message = "O tipo de cômodo deve ter pelo menos um tipo de imóvel.")
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<TipoImovelTipoComodo> tipoImovelTipoComodo = Lists.newArrayList();
	
	@Column
	@DeactivateOnDelete
	private Boolean ativo = true;
	
	public TipoComodo(String nome) {
		this.nome = nome;
	}

	public TipoComodo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<TipoImovelTipoComodo> getTipoImovelTipoComodo() {
		return tipoImovelTipoComodo;
	}

	public void setTipoImovelTipoComodo(List<TipoImovelTipoComodo> tipoImovelTipoComodo) {
		this.tipoImovelTipoComodo = tipoImovelTipoComodo;
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
		TipoComodo other = (TipoComodo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
