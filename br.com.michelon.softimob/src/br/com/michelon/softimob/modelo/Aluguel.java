package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.eclipse.ui.IEditorInput;

import br.com.michelon.softimob.aplicacao.service.AluguelService;
import br.com.michelon.softimob.aplicacao.service.ChamadoReformaService;
import br.com.michelon.softimob.aplicacao.service.CheckListService;
import br.com.michelon.softimob.aplicacao.service.GenericService;

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
		ParametrosEmpresa instance = ParametrosEmpresa.getInstance();
		if(instance != null){
			getItensCheckList().addAll(new CheckListService().getNewItens(instance.getCheckListAluguel()));
		}
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDataVencimento() {
		// TODO Auto-generated method stub
		return null;
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

}
