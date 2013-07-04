package br.com.michelon.softimob.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Arquivo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nome;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private ArquivoBytes arquivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArquivoBytes getArquivo() {
		return arquivo;
	}
	
	public void setArquivo(ArquivoBytes arquivo) {
		this.arquivo = arquivo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
