package br.com.michelon.softimob.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TipoImovelTipoComodo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Boolean preSelecionado;

	@ManyToOne
	private TipoImovel tipoImovel;

	@ManyToOne
	private TipoComodo tipoComodo;

	public TipoImovel getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(TipoImovel tipoImovel) {
		this.tipoImovel = tipoImovel;
	}

	public TipoComodo getTipoComodo() {
		return tipoComodo;
	}

	public void setTipoComodo(TipoComodo tipoComodo) {
		this.tipoComodo = tipoComodo;
	}
	
	public Boolean getPreSelecionado() {
		return preSelecionado;
	}

	public void setPreSelecionado(Boolean preSelecionado) {
		this.preSelecionado = preSelecionado;
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
		TipoImovelTipoComodo other = (TipoImovelTipoComodo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
