package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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

import com.google.common.collect.Lists;

@Entity
public class Imovel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String codigo;

	@Column
	private Boolean vender;
	
	@Column
	private Boolean alugar;
	
	@Column
	private Boolean ativo;

	@Column(precision = 14, scale = 2)
	private BigDecimal metragem;
	
	@Column(precision = 14, scale = 2)
	private BigDecimal valor;
	
	@ManyToOne()
	private Funcionario angariador;

	@ManyToOne()
	private Cliente proprietario;
	
	@ManyToOne()
	private TipoImovel tipo;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Endereco endereco;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comodo> comodos = Lists.newArrayList();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Chave> chaves = Lists.newArrayList();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Feedback> feedbacks = Lists.newArrayList();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Proposta> propostas = Lists.newArrayList();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vistoria> vistoria = Lists.newArrayList();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ChamadoReforma> chamados = Lists.newArrayList();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reserva> reservas = Lists.newArrayList();
 	
	public List<Proposta> getPropostas() {
		return propostas;
	}

	public void setPropostas(List<Proposta> propostas) {
		this.propostas = propostas;
	}

	public List<Vistoria> getVistoria() {
		return vistoria;
	}

	public void setVistoria(List<Vistoria> vistoria) {
		this.vistoria = vistoria;
	}

	public List<ChamadoReforma> getChamados() {
		return chamados;
	}

	public void setChamados(List<ChamadoReforma> chamados) {
		this.chamados = chamados;
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


	public BigDecimal getMetragem() {
		return metragem;
	}

	public void setMetragem(BigDecimal metragem) {
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Boolean getVender() {
		return vender;
	}

	public void setVender(Boolean vender) {
		this.vender = vender;
	}

	public Boolean getAlugar() {
		return alugar;
	}

	public void setAlugar(Boolean alugar) {
		this.alugar = alugar;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public String toString() {
		return this.codigo + " - " + this.endereco.toString() ;
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
