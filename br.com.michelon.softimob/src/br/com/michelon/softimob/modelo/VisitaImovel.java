package br.com.michelon.softimob.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class VisitaImovel {

	@NotNull(message = "A descrição não pode ser vazia.")
	@Column(nullable = false)
	private String descricao;
	
	@NotNull(message = "Selecione um funcionário.")
	@ManyToOne(optional = false)
	private Funcionario funcionario;
	
	@NotNull(message = "Data não pode ser vazia.")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
}
