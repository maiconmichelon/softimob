package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;

import br.com.michelon.softimob.aplicacao.service.ComissaoService;
import br.com.michelon.softimob.aplicacao.service.VistoriaService;

@Inheritance(strategy=InheritanceType.JOINED)
@MappedSuperclass
@Entity
public class VendaAluguel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O imóvel não pode ser vazio.")
	@ManyToOne
	private ContratoPrestacaoServico contrato;
	
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
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAssinaturaContrato;

	@NotNull()
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemCheckListDescricao> itensCheckList = Lists.newArrayList();
	
//	@OneToMany(cascade=CascadeType.ALL)
//	private List<Comissao> comissoes = Lists.newArrayList();
//	
//	@OneToMany(cascade=CascadeType.ALL)
//	private List<Vistoria> vistorias = Lists.newArrayList();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setContrato(ContratoPrestacaoServico contrato) {
		this.contrato = contrato;
	}
	
	public ContratoPrestacaoServico getContrato() {
		return contrato;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

//	public List<Comissao> getComissoes() {
//		return comissoes;
//	}
//	
//	public List<Vistoria> getVistorias() {
//		return vistorias;
//	}
	
	public List<Comissao> getComissoes() {
		return new ComissaoService().findByVendaAluguel(this);
	}

	public List<Vistoria> getVistorias(){
		return new VistoriaService().findByVendaAluguel(this);
	}
	
	public List<ItemCheckListDescricao> getItensCheckList() {
		return itensCheckList;
	}

	public void setItensCheckList(List<ItemCheckListDescricao> itensCheckList) {
		this.itensCheckList = itensCheckList;
	}
	
}
