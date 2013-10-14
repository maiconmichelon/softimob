package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.eclipse.ui.IEditorInput;

import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.PropostaService;
import br.com.michelon.softimob.tela.editor.ImovelEditor;

@Entity
public class Proposta implements Serializable, Pendencia{

	private static final long serialVersionUID = 1L;

	public static final int ACEITA = 0;
	public static final int RECUSADA = 1;
	public static final int CONTRAPROPOSTA = 2;

	public static final int CONTRA_PROPOSTA_PROPRIETARIO = 3;
	public static final int CONTRA_PROPOSTA_CLIENTE = 4;
	public static final int PRIMEIRA = 5;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotNull(message = "Informe a data que foi feita a proposta.")
	@Past(message="A data informada referente a criação da proposta esta incorreta.")
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	
	@Past(message="A data informada para a data de fechamento da proposta esta incorreta.")
	@Temporal(TemporalType.DATE)
	private Date dataFechamento;
	
	@NotNull(message="Informe o cliente que fez a proposta.")
	@ManyToOne(optional = false)
	private Cliente cliente;
	
	@ManyToOne()
	private Funcionario funcionario;
	
	@NotNull(message = "Informe o valor da proposta.")
	@Column(precision = 14, scale = 2, nullable = false)
	private BigDecimal valor;

	@Column
	private String observacoes;
	
	@Column
	private Integer status;
	
	@OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
	private Proposta contraProposta;
	
	@Column(nullable = false)
	private Integer tipoContraProposta = PRIMEIRA;
	
	@ManyToOne()
	private Imovel imovel;
	
	public Proposta(Imovel imovel) {
		this.imovel = imovel;
	}
	
	@SuppressWarnings("unused")
	private Proposta(){}
	
	public Proposta(Proposta proposta) {
		setFuncionario(proposta.getFuncionario());
		setCliente(proposta.getCliente());
		setImovel(proposta.getImovel());
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Proposta getContraProposta() {
		return contraProposta;
	}

	public void setContraProposta(Proposta contraProposta) {
		this.contraProposta = contraProposta;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getTipoContraProposta() {
		return tipoContraProposta;
	}

	public Comissionado getClienteProprietario(){
		return tipoContraProposta != null && tipoContraProposta.equals(CONTRA_PROPOSTA_PROPRIETARIO) ? imovel.getProprietario() : cliente;
	}
	
	public void setTipoContraProposta(Integer tipoContraProposta) {
		this.tipoContraProposta = tipoContraProposta;
	}

	public String getRealizador() {
		return tipoContraProposta == null || CONTRA_PROPOSTA_CLIENTE == tipoContraProposta ? 
				"Cliente - " + getCliente().getNome() : "Proprietário - " + getImovel().getProprietario().getNome();
	}

	@Override
	public Date getDataGeracao() {
		return getData();
	}

	@Override
	public Date getDataVencimento() {
		return null;
	}

	@Override
	public String getDescricao() {
		return String.format("Proposta de %s para %s referente ao %s", getCliente().getNome(), getImovel().getProprietario().getNome(), getImovel().getDescricao());
	}

	@Override
	public boolean confirmarFinalizarPendencia() {
		return false;
	}
	
	@Override
	public String getIdEditor() {
		return ImovelEditor.ID;
	}

	@Override
	public IEditorInput getEditorInput() {
		ImovelEditorInput editorInput = new ImovelEditorInput();
		editorInput.setModelo(getImovel());
		return editorInput;
	}

	private static transient PropostaService service;
	
	@Override
	public GenericService<?> getService() {
		if(service == null)
			service = new PropostaService();
		return service;
	}

	@Override
	public void finalizarPendencia() throws Exception {
		((PropostaService)getService()).abrirTela(this);
	}
	
	public String getStatusExtenso(){
		Integer status = getStatus();
		if(status == null)
			return "Em aberto";
		if(status == CONTRAPROPOSTA)
			return "Contraproposta";
		if(status == ACEITA)
			return "Aceito";
		if(status == RECUSADA)
			return "Recusada";
		return null;
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
		Proposta other = (Proposta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
