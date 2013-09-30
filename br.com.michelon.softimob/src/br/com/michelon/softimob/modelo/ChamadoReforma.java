package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.eclipse.ui.IEditorInput;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.michelon.softimob.aplicacao.editorInput.AluguelEditorInput;
import br.com.michelon.softimob.aplicacao.service.ChamadoReformaService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.tela.editor.AluguelEditor;

import com.google.common.collect.Lists;

@Entity
public class ChamadoReforma implements Serializable, Pendencia{

	private static final long serialVersionUID = 1L;

	public static final int ACEITO = 1;
	public static final int RECUSADO = 2;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	private Aluguel aluguel;
	
	@NotNull(message = "A data do chamado não pode ser vazia")
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	
	@ManyToOne
	private Funcionario funcionario;
	
	@NotEmpty(message = "A descrição do problema não pode ser vazia.")
	@Column(nullable = false)
	private String problema;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private FinalizacaoChamadoReforma finalizacao;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AcontecimentoChamado> acontecimentos = Lists.newArrayList();
	
	public ChamadoReforma(Aluguel aluguel){
		this.aluguel = aluguel;
		
		ParametrosEmpresa param = ParametrosEmpresa.getInstance();
		if(param != null)
			setFuncionario(param.getFuncionarioResponsavelReforma());
	}
	
	@SuppressWarnings("unused")
	private ChamadoReforma(){};
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProblema() {
		return problema;
	}

	public void setProblema(String problema) {
		this.problema = problema;
	}

	@Override
	public Date getDataGeracao() {
		return data;
	}

	@Override
	public Date getDataVencimento() {
		ParametrosEmpresa param = ParametrosEmpresa.getInstance();
		if(param.getDiasFinalizacaoReforma() != null){
			Calendar c = Calendar.getInstance();
			
			c.setTime(getData());
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + param.getDiasFinalizacaoReforma());
			
			return c.getTime();
		}
		
		return null;
	}

	@Override
	public String getDescricao() {
		return String.format("Chamado de Reforma de %s do %s", getAluguel().getCliente(), getAluguel().getContrato().getImovel().getDescricao()); 
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getStatus(){
		return getFinalizacao() == null ? "Em aberto" : getFinalizacao().getStatus() == ACEITO ? "Aceito" : "Recusado";
	}
	
	@Override
	public String getIdEditor() {
		return AluguelEditor.ID;
	}

	@Override
	public IEditorInput getEditorInput() {
		AluguelEditorInput ei = new AluguelEditorInput();
		ei.setModelo(getAluguel());
		return ei;
	}

	@Override
	public BigDecimal getValor() {
		return null;
	}

	@Override
	public Date getDataFechamento() {
		if(finalizacao == null)
			return null;
		return finalizacao.getData();
	}
	
	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public FinalizacaoChamadoReforma getFinalizacao() {
		return finalizacao;
	}

	public void setFinalizacao(FinalizacaoChamadoReforma finalizacao) {
		this.finalizacao = finalizacao;
	}
	
	public List<AcontecimentoChamado> getAcontecimentos(){
		return acontecimentos;
	}
	
	@Override
	public boolean confirmarFinalizarPendencia() {
		return false;
	}
	
	private transient static ChamadoReformaService c;
	
	@Override
	public GenericService<?> getService() {
		if(c == null)
			c = new ChamadoReformaService();
		return c;
	}

	@Override
	public void finalizarPendencia() throws Exception {
		((ChamadoReformaService)getService()).abrirTela(this);
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
		ChamadoReforma other = (ChamadoReforma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
