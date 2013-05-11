package br.com.michelon.softimob.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Imovel {

	public static final int VENDIDO = 2;
	public static final int ALUGADO = 3;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(precision = 14, scale = 2)
	private BigDecimal metragem;
	
	@Column
	private String codigo;
	
	@ManyToMany
	private List<Comodo> comodos = new ArrayList<Comodo>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Funcionario angariador;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Chave> chaves;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private TipoImovel tipo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Cliente proprietario;
	
	@OneToMany
	private List<HistoricoImovel> feedbacks = new ArrayList<HistoricoImovel>();
	
	private BigDecimal valor;
	
	private Boolean ocupado;
	
	private Boolean vender;
	
	private Boolean alugar;
	
	private transient Endereco endereco;
	
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
	
	public List<HistoricoImovel> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<HistoricoImovel> feedbacks) {
		this.feedbacks = feedbacks;
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

	public Boolean getOcupado() {
		return ocupado;
	}

	public void setOcupado(Boolean ocupado) {
		this.ocupado = ocupado;
	}

}
