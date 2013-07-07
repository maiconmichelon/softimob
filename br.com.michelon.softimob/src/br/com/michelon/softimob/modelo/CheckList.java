package br.com.michelon.softimob.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.collect.Lists;

@Entity
public class CheckList {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Nome da check list não pode ser vazia")
	@Column(nullable = false)
	private String nome;
	
	@OneToMany
	private List<ItemCheckList> itens = Lists.newArrayList();

	@Column(nullable = false)
	private Boolean ativo = true;
	
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

	public List<ItemCheckList> getItens() {
		return this.itens;
	}

	public void setItens(List<ItemCheckList> itens) {
		this.itens = itens;
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
	
}
