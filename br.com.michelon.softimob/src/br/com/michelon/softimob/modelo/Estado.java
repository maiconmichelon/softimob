package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.michelon.softimob.aplicacao.service.CidadeService;

@Entity
public class Estado implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Nome do estado não pode ser vazio.")
	@Column(nullable = false, unique = true)
	private String nome;
	
	@NotNull(message = "UF do estado não pode ser vazio.")
	@Length(max = 2, min = 2, message = "A UF do estado deve possuir 2 caracteres.")
	@Column(nullable = false, length = 2, unique = true)
	private String uf;
	
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public List<Cidade> getCidades() {
		return new CidadeService().findCidadesByEstado(this);
	}
	
	@Override
	public String toString() {
		return uf;
	}
	
}
