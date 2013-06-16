package br.com.michelon.softimob.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PessoaFisica extends Cliente{

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String cpf;
	
	@Column(nullable = false)
	private String rg;
	
	@Column
	private String filiacao;

	@Column(nullable = false)
	private String estadoCivil;
	
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

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
}
