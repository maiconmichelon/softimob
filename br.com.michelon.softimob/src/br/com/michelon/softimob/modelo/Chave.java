package br.com.michelon.softimob.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Chave implements Serializable{

	private static final long serialVersionUID = 1L;

	public enum LocalizacaoChave{
		IMOBILIARIA("Imobiliária"),
		CLIENTE("Cliente");
		
		private String descricao;

		LocalizacaoChave(String descricao){
			this.descricao = descricao;
		}
		
		@Override
		public String toString() {
			return descricao;
		}
	}
	
	@Id @GeneratedValue
	private Long id;
	
	@NotEmpty(message="O número da chave não pode ser vazio.")
	@Column(nullable = false)
	private String numero;
	
	@ManyToOne(optional=false)
	private Imovel imovel;
	
	@NotNull(message = "Informe a localização da chave")
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private LocalizacaoChave localizacao;
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
	@br.com.michelon.softimob.aplicacao.annotation.Log
	private Log log = new Log();
	
	public Chave(Imovel imovel) {
		this.imovel = imovel;
	}
	
	@SuppressWarnings("unused")
	private Chave(){}
	
	public Log getLog() {
		return log;
	}
	
	public void setLog(Log log) {
		this.log = log;
	}
	
	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setLocalizacao(LocalizacaoChave localizacao) {
		this.localizacao = localizacao;
	}
	
	public LocalizacaoChave getLocalizacao() {
		return localizacao;
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
		Chave other = (Chave) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return false;
	}
	
}
