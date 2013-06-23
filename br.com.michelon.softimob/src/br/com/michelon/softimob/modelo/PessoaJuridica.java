package br.com.michelon.softimob.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PessoaJuridica extends Cliente{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String cnpj;
	
	@Column(nullable = false)
	private String inscrisaoEstadual;
	
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
