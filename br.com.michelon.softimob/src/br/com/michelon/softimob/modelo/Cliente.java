package br.com.michelon.softimob.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Cliente {

	public Cliente() {
	}
	
	public Cliente(String nome) {
		this.nome = nome;
	}
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	@NotNull(message="Nome do cliente não pode ser vazio")
	private String nome;
	
	@Column(nullable = false)
	private String cpf;
	
	@Column(nullable = false)
	private String rg;

	@Column(nullable = false)
	private String nacionalidade;
	
	@Column(nullable = false)
	private String estadoCivil;

	@Column(length=10, nullable = false)
	@NotNull(message = "O telefone não pode ser nulo.")
	private String telefone;
	
	@Column(length=10)
	private String celular;

	@Column
	private String email;
	
	@Column
	private String filiacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

//	@NotNull(message = "Endereço não pode ser vazio.")
//	@OneToOne(optional = false)
	private transient Endereco endereco;
	
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

}
