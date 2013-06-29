package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;

@Entity
public class Imovel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private Boolean ativo = true;

	@Column
	private Integer metragem;

	@ManyToOne()
	private Funcionario angariador;

	@NotNull(message="O proprietário deve ser informado.")
	@ManyToOne(optional=false)
	private Cliente proprietario;
	
	@NotNull(message="O tipo de imóvel não foi informado")
	@ManyToOne(optional=false)
	private TipoImovel tipo;

	@Column
	private String observacoes;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional=false)
	private Endereco endereco = new Endereco();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="imovel")
	private List<Comodo> comodos = Lists.newArrayList();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="imovel")
	private List<Chave> chaves = Lists.newArrayList();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="imovel")
	private List<Feedback> feedbacks = Lists.newArrayList();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="imovel")
	private List<Proposta> propostas = Lists.newArrayList();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="imovel")
	private List<Reserva> reservas = Lists.newArrayList();
 	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="imovel")
	private List<ContratoPrestacaoServico> contratos = Lists.newArrayList();
	
	public void setContratos(List<ContratoPrestacaoServico> contratos) {
		this.contratos = contratos;
	}
	
	public List<ContratoPrestacaoServico> getContratos() {
		return contratos;
	}
	
	public List<Proposta> getPropostas() {
		return propostas;
	}

	public void setPropostas(List<Proposta> propostas) {
		this.propostas = propostas;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Cliente getProprietario() {
		return proprietario;
	}

	public void setProprietario(Cliente proprietario) {
		this.proprietario = proprietario;
	}

	public Funcionario getAngariador() {
		return angariador;
	}

	public void setAngariador(Funcionario angariador) {
		this.angariador = angariador;
	}


	public Integer getMetragem() {
		return metragem;
	}

	public void setMetragem(Integer metragem) {
		this.metragem = metragem;
	}

	public List<Comodo> getComodos() {
		return comodos;
	}

	public void setComodos(List<Comodo> comodos) {
		this.comodos = comodos;
	}

	public List<Chave> getChaves() {
		return chaves;
	}

	public void setChaves(List<Chave> chaves) {
		this.chaves = chaves;
	}

	public TipoImovel getTipo() {
		return tipo;
	}

	public void setTipo(TipoImovel tipo) {
		this.tipo = tipo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Override
	public String toString() {
		return this.id + " - " + this.endereco.toString() ;
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
		Imovel other = (Imovel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
