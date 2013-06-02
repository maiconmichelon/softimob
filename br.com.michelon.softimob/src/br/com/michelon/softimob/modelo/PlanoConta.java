package br.com.michelon.softimob.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PlanoConta implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final int ATIVA = 1;
	public static final int PASSIVA = 2;
	
	@Id @GeneratedValue
	private Long id;
	
	@Column
	private Long codigo;
	
	@Column
	private String descricao;
	
	@Column
	private Integer tipo;
	
	@Column
	private Boolean ativo = true;
	
	public PlanoConta(String descricao, long codigo) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getCodigoDescricao() {
		return String.format("%s - %s", getCodigo() , getDescricao());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
