package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.collect.Lists;

@Entity
public class Indice implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;
	
	@NotEmpty(message = "O nome do índice não pode ser vazio")
	@Column(nullable = false)
	private String nome;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<IndiceMes> indices = Lists.newArrayList();

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

	public List<IndiceMes> getIndices() {
		return indices;
	}

	public void setIndices(List<IndiceMes> indices) {
		this.indices = indices;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
