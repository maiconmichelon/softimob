package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class TipoComodo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O nome do Cômodo não pode ser vazio")
	@Column(nullable = false, unique = true)
	private String nome;

	@OneToMany
	private List<TipoImovelTipoComodo> tipoImovel;
	
	//Se quando cadastrar o imovel ele ja estar cadastrado
	private Boolean preAdicionado;
	
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

	public List<TipoImovelTipoComodo> getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(List<TipoImovelTipoComodo> tipoImovel) {
		this.tipoImovel = tipoImovel;
	}
	
	public Boolean getPreAdicionado() {
		return preAdicionado;
	}

	public void setPreAdicionado(Boolean preAdicionado) {
		this.preAdicionado = preAdicionado;
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
