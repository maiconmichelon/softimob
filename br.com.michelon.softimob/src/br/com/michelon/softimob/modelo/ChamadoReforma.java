package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.collect.Lists;

public class ChamadoReforma implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final int ACEITO = 1;
	public static final int RECUSADO = 2;
	public static final int ABERTO = 3;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long numero;
	
	@ManyToOne(optional=false)
	private Imovel imovel;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAbertura;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFechamento;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Funcionario funcionarioAbertura;
	
	@ManyToOne
	private Funcionario funcionarioFechamento;
	
	@Column(nullable = false)
	private String problema;
	
	@OneToMany(orphanRemoval = true)
	private List<AcontecimentoChamado> acontecimentoChamado = Lists.newArrayList();
	
	@Column
	private Integer status;

	@Column(nullable = false)
	private String descricaoConclusao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionarioAbertura() {
		return funcionarioAbertura;
	}

	public void setFuncionarioAbertura(Funcionario funcionarioAbertura) {
		this.funcionarioAbertura = funcionarioAbertura;
	}

	public Funcionario getFuncionarioFechamento() {
		return funcionarioFechamento;
	}

	public void setFuncionarioFechamento(Funcionario funcionarioFechamento) {
		this.funcionarioFechamento = funcionarioFechamento;
	}

	public String getProblema() {
		return problema;
	}

	public void setProblema(String problema) {
		this.problema = problema;
	}

	public List<AcontecimentoChamado> getAcontecimentoChamado() {
		return acontecimentoChamado;
	}

	public void setAcontecimentoChamado(
			List<AcontecimentoChamado> acontecimentoChamado) {
		this.acontecimentoChamado = acontecimentoChamado;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescricaoConclusao() {
		return descricaoConclusao;
	}

	public void setDescricaoConclusao(String descricaoConclusao) {
		this.descricaoConclusao = descricaoConclusao;
	}
	
}
