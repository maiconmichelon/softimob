package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import br.com.michelon.softimob.aplicacao.annotation.WildCard;
import br.com.michelon.softimob.aplicacao.service.CheckListService;
import br.com.michelon.softimob.aplicacao.service.ComissaoService;
import br.com.michelon.softimob.aplicacao.service.VistoriaService;

import com.google.common.collect.Lists;

@Inheritance(strategy=InheritanceType.JOINED)
@MappedSuperclass
@Entity
public abstract class VendaAluguel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O imóvel não pode ser vazio.")
	@ManyToOne
	private ContratoPrestacaoServico contrato;

	@NotNull(message = "O modelo de contrato não pode ser vazio.")
	@ManyToOne(fetch = FetchType.LAZY)
	private ModeloContrato modeloContrato;
	
	//Cliente que vai alugar ou comprar a casa
	@NotNull(message = "O cliente não pode ser vazio.")
	@ManyToOne
	private Cliente cliente;
	
	//Corretor que vendeu ou alugou
	@NotNull(message = "Informe o funcionário responsável.")
	@ManyToOne
	private Funcionario funcionario;
	
	@NotNull(message = "O valor não pode ser vazio.")
	@Column(length=14, scale = 2)
	private BigDecimal valor;
	
	@NotNull(message = "A data não pode ser vazia.")
	@Temporal(TemporalType.DATE)
	@Column
	private Date dataAssinaturaContrato = new Date();

	@NotNull()
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemCheckList> itensCheckList = Lists.newArrayList();
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
	@br.com.michelon.softimob.aplicacao.annotation.Log
	private Log log = new Log();
	
	public Log getLog() {
		return log;
	}
	
	public void setLog(Log log) {
		this.log = log;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ModeloContrato getModeloContrato() {
		return modeloContrato;
	}
	
	public void setModeloContrato(ModeloContrato modeloContrato) {
		this.modeloContrato = modeloContrato;
	}
	
	public void setContrato(ContratoPrestacaoServico contrato) {
		this.contrato = contrato;
	}
	
	public ContratoPrestacaoServico getContrato() {
		return contrato;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataAssinaturaContrato() {
		return dataAssinaturaContrato;
	}

	public void setDataAssinaturaContrato(Date dataAssinaturaContrato) {
		this.dataAssinaturaContrato = dataAssinaturaContrato;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Comissao> getComissoes() {
		return new ComissaoService().findByVendaAluguel(this);
	}

	public List<Vistoria> getVistorias(){
		return new VistoriaService().findByVendaAluguel(this);
	}
	
	public List<ItemCheckList> getItensCheckList() {
		return itensCheckList;
	}

	public void setItensCheckList(List<ItemCheckList> itensCheckList) {
		this.itensCheckList = itensCheckList;
	}

	public void carregarCheckList() {
		getItensCheckList().addAll(new CheckListService().getNewItens(ParametrosEmpresa.getInstance().getCheckListAluguel()));
	}
	
	@WildCard
	public ParametrosEmpresa getParametros(){
		return ParametrosEmpresa.getInstance();
	}
	
	public boolean isOkCheckList(){
		for(ItemCheckList item : getItensCheckList()){
			if(item.getObrigatorio() && !item.getFinalizado())
				return false;
		}
		
		return true;
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
		VendaAluguel other = (VendaAluguel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		if(getContrato() == null)
			return StringUtils.EMPTY;
		
		StringBuilder sb = new StringBuilder();
		
		boolean a = this instanceof Aluguel;
		
		sb.append(a ? "Locação de " : "Venda de ");
		sb.append(getContrato().getImovel().getTipo().getNome() + " de "+ getContrato().getCliente());
		sb.append(" localizado em " + getContrato().getImovel().getEndereco().toString() + " ");
		sb.append("para o " + (a ? "inquilino " : "comprador ") + getCliente());
		
		return sb.toString();
	}

}
