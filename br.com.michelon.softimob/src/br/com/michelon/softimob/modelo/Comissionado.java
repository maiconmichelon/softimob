package br.com.michelon.softimob.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

public class Comissionado {

	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	@NotNull(message="Nome do cliente não pode ser vazio")
	private String nome;
	
	@Column(length=10, nullable = false)
	@NotNull(message = "O telefone não pode ser nulo.")
	private String telefone;
	
	@Column(length=10)
	private String celular;
	
	@Column
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
