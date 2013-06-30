package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class IndiceMes implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;
	
	@NotNull(message = "A data não pode ser vazia")
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@NotNull(message = "Valor da porcentagem não pode ser vazia")
	@Column(nullable = false)
	private Double porcentagem;

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

	public Double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(Double porcentagem) {
		this.porcentagem = porcentagem;
	}
	
}