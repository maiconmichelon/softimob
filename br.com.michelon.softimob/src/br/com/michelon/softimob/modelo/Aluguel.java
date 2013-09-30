package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.eclipse.ui.IEditorInput;

import com.google.common.collect.Lists;

import br.com.michelon.softimob.aplicacao.editorInput.AluguelEditorInput;
import br.com.michelon.softimob.aplicacao.service.AluguelService;
import br.com.michelon.softimob.aplicacao.service.ChamadoReformaService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.tela.editor.AluguelEditor;

@Entity
public class Aluguel extends VendaAluguel implements Pendencia, Serializable{

	private static final long serialVersionUID = 1L;

	public Aluguel() {
//		ParametrosEmpresa params = ParametrosEmpresa.getInstance();
//		if(params != null)
//			setModeloContrato(params.getContratoAluguel());
	}
	
	@ManyToOne
	private Cliente fiador;
	
	@NotNull(message = "A duração do contrato não pode ser vazia.")
	@Temporal(TemporalType.DATE)
	private Date dataVencimento = new Date();
	
	@ManyToOne
	private Indice reajuste;
	
	@Column(nullable = false)
	private Boolean resolvido = false;
	
	@Temporal(TemporalType.DATE)
	private Date dataFechamento = null;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
	private List<ContaPagarReceber> parcelas = Lists.newArrayList();
	
	public List<ContaPagarReceber> getParcelas() {
		return parcelas;
	}
	
	public Cliente getFiador() {
		return fiador;
	}

	public void setFiador(Cliente fiador) {
		this.fiador = fiador;
	}

	public Indice getReajuste() {
		return reajuste;
	}

	public void setReajuste(Indice reajuste) {
		this.reajuste = reajuste;
	}

	public void setResolvido(Boolean resolvido) {
		dataFechamento = resolvido ? new Date() : null;
		this.resolvido = resolvido;
	}
	
	public Boolean getResolvido() {
		return resolvido;
	}
	
	public List<ChamadoReforma> getChamados() {
		return new ChamadoReformaService().findByAluguel(this);
	}

	private transient static AluguelService service;
	
	@Override
	public GenericService<?> getService() {
		if(service == null)
			service = new AluguelService();
		return service;
	}
	
	@Override
	public Date getDataGeracao() {
		return getDataAssinaturaContrato();
	}

	@Override
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	@Override
	public Date getDataFechamento() {
		return dataFechamento;
	}

	@Override
	public String getDescricao() {
		return toString();
	}

	@Override
	public String getIdEditor() {
		return AluguelEditor.ID;
	}

	@Override
	public boolean confirmarFinalizarPendencia() {
		return true;
	}
	
	@Override
	public IEditorInput getEditorInput() {
		AluguelEditorInput aluguelEditorInput = new AluguelEditorInput();
		aluguelEditorInput.setModelo(this);
		return aluguelEditorInput;
	}

	@Override
	public void finalizarPendencia() throws Exception {
		((AluguelService)getService()).finalizarPendencia(this);
	}

}
