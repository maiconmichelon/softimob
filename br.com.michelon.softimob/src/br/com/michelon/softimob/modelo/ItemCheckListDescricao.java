package br.com.michelon.softimob.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.StringUtils;

@Entity
public class ItemCheckListDescricao {

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String descricao = StringUtils.EMPTY;
	
	@Column
	private Boolean finalizado = false;
	
	@ManyToOne(optional=false)
	private ItemCheckList item;

	public ItemCheckListDescricao(ItemCheckList item) {
		this.item = item;
	}

	@SuppressWarnings("unused")
	private ItemCheckListDescricao() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public ItemCheckList getItem() {
		return item;
	}

	public void setItem(ItemCheckList item) {
		this.item = item;
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
		ItemCheckListDescricao other = (ItemCheckListDescricao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
