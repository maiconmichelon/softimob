package br.com.michelon.softimob.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.eclipse.ui.IEditorInput;

public class ContratoPrestacaoServico implements Pendencia{

	public enum TipoContrato{
		
		LOCACAO("Locação"),
		VENDA("Venda"),
		LOCACAOVENDA("Locação / Venda");
		
		private final String descricao;

		private TipoContrato(String descricao) {
			this.descricao = descricao;
		}
		
		public String toString() {
			return this.descricao;
		};
		
	}
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(precision = 14, scale = 2)
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private TipoContrato tipo;
	
	@ManyToOne
	private Imovel imovel;
	
	@NotNull
	@Column(nullable=false)
	private Boolean divulgar;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVencimento;
	
	@ManyToOne
	private Funcionario funcionario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoContrato getTipo() {
		return tipo;
	}

	public void setTipo(TipoContrato tipo) {
		this.tipo = tipo;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public Date getDataGeracao() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Date getDataVencimento() {
		return dataVencimento;
	}

	@Override
	public Date getDataFechamento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescricao() {
		// TODO Auto-generated method stub
		return null;
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
		return this.valor;
	}

	public Boolean getDivulgar() {
		return divulgar;
	}

	public void setDivulgar(Boolean divulgar) {
		this.divulgar = divulgar;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
