package br.com.michelon.softimob.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.eclipse.persistence.annotations.Index;

@Entity
public class Boleto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Index
	@OneToOne
	private ContaPagarReceber conta;
	
	@OneToOne
	private BaixaBoleto baixa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ContaPagarReceber getConta() {
		return conta;
	}

	public void setConta(ContaPagarReceber conta) {
		this.conta = conta;
	}

	public BaixaBoleto getBaixa() {
		return baixa;
	}

	public void setBaixa(BaixaBoleto baixa) {
		this.baixa = baixa;
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
		Boleto other = (Boleto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}