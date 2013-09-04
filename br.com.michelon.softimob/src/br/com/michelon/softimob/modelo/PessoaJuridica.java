package br.com.michelon.softimob.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.Index;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.stella.bean.validation.CNPJ;

@Entity
public class PessoaJuridica extends Cliente{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@CNPJ(message = "CNPJ informado é invalido", formatted = true)
	@NotEmpty(message = "O cnpj não pode ser vazio.")
	@Index
	@Column(nullable = false)
	private String cnpj;
	
	@NotEmpty(message = "A inscrição estadual não pode ser vazia")
	@Column(nullable = false)
	private String inscrisaoEstadual;
	
	@NotNull(message = "O sócio administrador da empresa deve ser informado")
	@ManyToOne
	private PessoaFisica socioProprietario;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscrisaoEstadual() {
		return inscrisaoEstadual;
	}

	public void setInscrisaoEstadual(String inscrisaoEstadual) {
		this.inscrisaoEstadual = inscrisaoEstadual;
	}

	public PessoaFisica getSocioProprietario() {
		return socioProprietario;
	}

	public void setSocioProprietario(PessoaFisica socioProprietario) {
		this.socioProprietario = socioProprietario;
	}

}
