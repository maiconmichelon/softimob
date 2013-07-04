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
import javax.validation.constraints.NotNull;

@Entity
public class ModeloContrato implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O nome não pode ser vazio")
	@Column(nullable = false, unique = true)
	private String nome;
	
	@NotNull(message = "Insira o arquivo referente ao modelo")
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Arquivo arquivo;
	
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

	public Arquivo getArquivo() {
		return arquivo;
	}
	
	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
}
