package br.com.michelon.softimob.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemCheckListDescricao {

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String descricao;
	
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
	
}
