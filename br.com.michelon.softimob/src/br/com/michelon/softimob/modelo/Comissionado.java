package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.michelon.softimob.aplicacao.annotation.DeactivateOnDelete;

@Entity
@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Comissionado implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotEmpty(message="O nome não pode ser vazio")
	private String nome;
	
	@Column(length=10, nullable = false)
	@Length(max = 10, min = 10, message = "O telefone deve ter 10 caracteres.")
	@NotNull(message = "O telefone deve ter 13 caracteres.")
	private String telefone;
	
	@Column(length=10)
	private String celular;
	
	@Column
	@Email(message="Não é um e-mail válido.")
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(columnDefinition="TIMESTAMP WITH TIME ZONE")
	private Date dataNascimento;
	
	@Column
	@DeactivateOnDelete
	private Boolean ativo = true;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional=false)
	private Endereco endereco = new Endereco();
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
	@br.com.michelon.softimob.aplicacao.annotation.Log
	private Log log = new Log();
	
	public Log getLog() {
		return log;
	}
	
	public void setLog(Log log) {
		this.log = log;
	}
	
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

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
		Comissionado other = (Comissionado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
