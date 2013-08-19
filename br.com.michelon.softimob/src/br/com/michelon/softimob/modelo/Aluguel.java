package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.eclipse.ui.IEditorInput;

import br.com.michelon.softimob.aplicacao.editorInput.AluguelEditorInput;
import br.com.michelon.softimob.aplicacao.service.AluguelService;
import br.com.michelon.softimob.aplicacao.service.ChamadoReformaService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.tela.editor.AluguelEditor;

@Entity
public class Aluguel extends VendaAluguel implements Pendencia, Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Cliente fiador;
	
	@NotNull(message = "A duração do contrato não pode ser vazia.")
	@Column
	private Integer duracao;
	
//	@NotNull
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date dataVencimento;
	
	@Column
	private Integer reajuste;
	
	public Aluguel(){
	}
	
	public Cliente getFiador() {
		return fiador;
	}

	public void setFiador(Cliente fiador) {
		this.fiador = fiador;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Integer getReajuste() {
		return reajuste;
	}

	public void setReajuste(Integer reajuste) {
		this.reajuste = reajuste;
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
		Calendar c = Calendar.getInstance();
		c.setTime(getDataAssinaturaContrato());
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + duracao);
		return c.getTime();
	}

	@Override
	public Date getDataFechamento() {
		// TODO Auto-generated method stub
		return null;
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
	public IEditorInput getEditorInput() {
		AluguelEditorInput aluguelEditorInput = new AluguelEditorInput();
		aluguelEditorInput.setModelo(this);
		return aluguelEditorInput;
	}

}
