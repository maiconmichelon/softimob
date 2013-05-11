package br.com.michelon.softimob.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ModeloContrato {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O nome não pode ser vazio")
	@Column(nullable = false, unique = true)
	private String nome;
	
	private Byte modelo;
	
}
