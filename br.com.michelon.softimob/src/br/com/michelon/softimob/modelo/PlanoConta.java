package br.com.michelon.softimob.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class PlanoConta implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final int ATIVA = 1;
	public static final int PASSIVA = 2;
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(unique = true, nullable = false)
	@NotEmpty(message = "O código da conta não pode ser vazio.")
	private String codigo;
	
	@NotEmpty(message = "A descrição do plano de conta não pode ser vazio.")
	@Column(nullable = false)
	private String nome;
	
	@NotNull(message = "Informe o tipo da conta.")
	@Column(nullable = false)
	private Integer tipo;
	
	@Column
	private Boolean ativo = true;
	
	public PlanoConta(){
		
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTipoExtenso(){
		return tipo == ATIVA ? "Ativo " : "Passivo";
	}
	
	@Override
	public String toString() {
		return String.format("%s - %s", getCodigo() , getNome());
	}

	public String getCodigoDescricao(){
		return toString();
	}
	
}
