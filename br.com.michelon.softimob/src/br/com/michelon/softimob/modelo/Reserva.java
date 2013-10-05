package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.eclipse.ui.IEditorInput;

import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.ReservaService;
import br.com.michelon.softimob.tela.editor.ImovelEditor;

@Entity
public class Reserva implements Serializable, Pendencia{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@NotNull(message="Informe a data que foi feita a reserva.")
	@Column(nullable=false)
	private Date dataReserva = new Date();

	@NotNull(message="Informe a data de vencimento da reserva.")
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataVencimento = new Date();
	
	@NotNull(message="Informe o cliente que fez a reserva.")
	@ManyToOne(optional=false)
	private Cliente cliente;
	
	@ManyToOne
	private Funcionario funcionario;

	@Column(precision=14, scale=2)
	private BigDecimal valor;
	
	@Column
	private String observacoes;

	@ManyToOne(optional=false)
	private Imovel imovel;

	@Column(nullable=false)
	private Boolean resolvido = false;
	
	@SuppressWarnings("unused")
	private Reserva(){	}
	
	public Reserva(Imovel imovel) {
		this.imovel = imovel;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

	@Override
	public Date getDataGeracao() {
		return getDataReserva();
	}

	@Override
	public Date getDataFechamento() {
		return null;
	}

	@Override
	public String getDescricao() {
		return String.format("Reserva de %s do %s", getCliente().getNome() ,getImovel().getDescricao());
	}

	@Override
	public boolean confirmarFinalizarPendencia() {
		return true;
	}
	
	@Override
	public String getIdEditor() {
		return ImovelEditor.ID;
	}

	@Override
	public IEditorInput getEditorInput() {
		ImovelEditorInput ei = new ImovelEditorInput();
		ei.setModelo(getImovel());
		return ei;
	}

	public Boolean getResolvido() {
		return resolvido;
	}
	
	public void setResolvido(Boolean resolvido) {
		this.resolvido = resolvido;
	}
	
	private static transient ReservaService service;
	
	@Override
	public GenericService<?> getService() {
		if(service == null)
			service = new ReservaService();
		return service;
	}

	@Override
	public void finalizarPendencia() throws Exception {
		((ReservaService)getService()).finalizarPendencia(this);
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
		Reserva other = (Reserva) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
