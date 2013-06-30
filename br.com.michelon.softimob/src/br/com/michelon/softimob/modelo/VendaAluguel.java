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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.michelon.softimob.aplicacao.service.ComissaoService;
import br.com.michelon.softimob.aplicacao.service.VistoriaService;

@Inheritance(strategy=InheritanceType.JOINED)
@MappedSuperclass
@Entity
public class VendaAluguel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private ContratoPrestacaoServico contrato;
	
	//Cliente que vai alugar ou comprar a casa
	@ManyToOne
	private Cliente cliente;
	
	//Corretor que vendeu ou alugou
	@ManyToOne
	private Funcionario funcionario;
	
	@Column(length=14, scale = 2)
	private BigDecimal valor;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAssinaturaContrato;

	@ManyToOne
	private CheckList checkList;
	
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

	public List<Comissao> getComissoes() {
		return new ComissaoService().findByVendaAluguel(this);
	}

	public List<Vistoria> getVistorias(){
		return new VistoriaService().findByVendaAluguel(this);
	}
	
	public CheckList getCheckList() {
		return checkList;
	}

	public void setCheckList(CheckList checkList) {
		this.checkList = checkList;
	}
	
}
