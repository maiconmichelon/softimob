package br.com.michelon.softimob.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.Index;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.stella.bean.validation.CPF;

@Entity
public class PessoaFisica extends Cliente{

	public enum EstadoCivil{
		
		CASADO("Casado"),
		SOLTEIRO("Solteiro"),
		VIUVO("Viuvo"),
		DIVORCIADO("Divorciado")
		;

		private String descricao;
		
		EstadoCivil(String descricao){
			this.descricao = descricao;
		}
		
		@Override
		public String toString() {
			return descricao;
		}
	}
	
	private static final long serialVersionUID = 1L;
	
	@Index
	@CPF(message = "CPF informardo é inválido.", formatted = true)
	@NotEmpty(message = "O CPF não pode ser vazio.")
	@Column(nullable = false, unique = true)
	private String cpf;
	
	@Index
	@NotEmpty(message = "O RG não pode ser vazio.")
	@Column(nullable = false, unique = true)
	private String rg;
	
	@Column
	private String filiacao;

	@NotNull(message = "O estado civil deve ser informado.")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoCivil estadoCivil;
	
	@NotEmpty(message="A nacionalidade não pode ser vazia")
	@Column(nullable = false)
	private String nacionalidade;

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

	public String getFiliacao() {
		return filiacao;
	}

	public void setFiliacao(String filiacao) {
		this.filiacao = filiacao;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	
	
}
