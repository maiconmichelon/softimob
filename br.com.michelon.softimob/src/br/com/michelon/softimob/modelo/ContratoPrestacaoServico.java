package br.com.michelon.softimob.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.eclipse.ui.IEditorInput;

@Entity
public class ContratoPrestacaoServico implements Pendencia{

	public enum TipoContrato{
		
		LOCACAO("Locação"),
		VENDA("Venda"),
		LOCACAOVENDA("Locação / Venda");
		
		private final String descricao;

		private TipoContrato(String descricao) {
			this.descricao = descricao;
		}
		
		@Override
		public String toString() {
			return this.descricao;
		};
		
	}
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(precision = 14, scale = 2)
	private BigDecimal valor = BigDecimal.ZERO;
	
	@NotNull(message="Informe o tipo do contrato.")
	@Column(nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private TipoContrato tipo;
	
	@JoinColumn(name = "imovel_id")
	@ManyToOne(optional=false)
	private Imovel imovel;
	
	@Column(nullable=false)
	private Boolean divulgar = true;
	
	@NotNull(message="Informe a data que foi feito o contrato.")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio = new Date();
	
	@NotNull(message="Informe a data de vencimento do contrato.")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVencimento = new Date();
	
	@ManyToOne
	private Funcionario funcionario;
	
	@SuppressWarnings("unused")
	private ContratoPrestacaoServico() {}
	
	@ManyToOne(optional = false)
	private Cliente cliente;
	
	public ContratoPrestacaoServico(Imovel imovel){
		this.imovel = imovel;
	}
	
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

	public String getDivulgarExtenso() {
		return divulgar ? "Sim" : "Não";
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

	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public String toString() {
		return imovel.toString();
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
		ContratoPrestacaoServico other = (ContratoPrestacaoServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
