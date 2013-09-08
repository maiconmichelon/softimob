package br.com.michelon.softimob.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

@Entity
public class ItemCheckList {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O nome do item não pode ser vazio.")
	@Column(nullable = false)
	private String nome;
	
	@Column()
	private String valor = StringUtils.EMPTY;
	
	@Column(nullable = false)
	private Boolean finalizado = false;
	
	@Column(nullable = false)
	private Boolean obrigatorio = false;
	
	public ItemCheckList(String nome, Boolean obrigatorio) {
		this.nome = nome;
		this.obrigatorio = obrigatorio;
	}

	@SuppressWarnings("unused")
	private ItemCheckList() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public Boolean getFinalizado() {
		return finalizado;
	}
	
	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	public Boolean getObrigatorio() {
		return obrigatorio;
	}
	
	public void setObrigatorio(Boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCheckList other = (ItemCheckList) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
