package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Funcionario extends Comissionado implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Selecione um departamento.")
	@ManyToOne(optional=false)
	private Departamento departamento;

	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;
	
	public Funcionario() {
	}

	public Funcionario(String nome) {
		setNome(nome);
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	@Override
	public String toString() {
		return getNome();
	}
	
	
	
}
