package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.google.common.collect.Lists;

@Entity
public class Aluguel extends VendaAluguel implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Cliente fiador;
	
	@Column
	private Integer duracao;
	
	@Column
	private Integer reajuste;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vistoria> vistoria = Lists.newArrayList();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ChamadoReforma> chamados = Lists.newArrayList();
	
	public Cliente getFiador() {
		return fiador;
	}

	public void setFiador(Cliente fiador) {
		this.fiador = fiador;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Integer getReajuste() {
		return reajuste;
	}

	public void setReajuste(Integer reajuste) {
		this.reajuste = reajuste;
	}

}
