package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.ui.IEditorInput;

import com.google.common.collect.Lists;

@Entity
public class ChamadoReforma implements Serializable, Pendencia{

	private static final long serialVersionUID = 1L;

	public static final int ACEITO = 1;
	public static final int RECUSADO = 2;
	public static final int ABERTO = 3;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Aluguel aluguel;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAbertura;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFechamento;
	
	@ManyToOne
	private Funcionario funcionarioAbertura;
	
	@ManyToOne
	private Funcionario funcionarioFechamento;
	
	@Column(nullable = false)
	private String problema;
	
	@OneToMany(orphanRemoval = true)
	private List<AcontecimentoChamado> acontecimentos = Lists.newArrayList();
	
	@Column
	private Integer status;

	@Column(nullable = false)
	private String descricaoConclusao;

	@OneToOne
	private ContaPagarReceber conta;
	
	public ChamadoReforma(Aluguel aluguel){
		this.aluguel = aluguel;
	}
	
	@SuppressWarnings("unused")
	private ChamadoReforma(){};
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	@Override
	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
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

	public List<AcontecimentoChamado> getAcontecimentos() {
		return acontecimentos;
	}

	public void setAcontecimentos(List<AcontecimentoChamado> acontecimentos) {
		this.acontecimentos = acontecimentos;
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

	@Override
	public Date getDataGeracao() {
		return dataAbertura;
	}

	@Override
	public Date getDataVencimento() {
		return null;
	}

	@Override
	public String getDescricao() {
		return "Chamado de Reforma"; 
	}

	@Override
	public String getIdEditor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEditorInput getEditorInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getValor() {
		// TODO Auto-generated method stub
		return null;
	}

	public ContaPagarReceber getConta() {
		return conta;
	}

	public void setConta(ContaPagarReceber conta) {
		this.conta = conta;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}
	
}
