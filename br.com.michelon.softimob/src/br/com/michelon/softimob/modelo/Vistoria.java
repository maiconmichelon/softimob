package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Vistoria implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Informe a data da vistoria.")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date data;
	
	@NotNull(message = "Selecione o funcionário responsável pela vistoria.")
	@ManyToOne(optional=false)
	private Funcionario funcionario;
	
	@Lob
	private Byte[] arquivo;
	
	@NotNull(message = "Selecione o último inquilino do imóvel.")
	@ManyToOne(optional=false)
	private Cliente inquilino;
	
	@NotNull(message = "Selecione o imóvel referente a vistoria.")
	@ManyToOne(optional=false)
	private Imovel imovel;
	
	@Column
	private String observacoes;

	public Vistoria(Imovel imovel) {
		this.imovel = imovel;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Cliente getInquilino() {
		return inquilino;
	}

	public void setInquilino(Cliente inquilino) {
		this.inquilino = inquilino;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(Byte[] arquivo) {
		this.arquivo = arquivo;
	}
	
}
