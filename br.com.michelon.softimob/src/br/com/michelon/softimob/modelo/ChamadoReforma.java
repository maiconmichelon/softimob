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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.eclipse.ui.IEditorInput;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.michelon.softimob.aplicacao.service.AcontecimentoChamadoService;
import br.com.michelon.softimob.aplicacao.service.ChamadoReformaService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.PendenciaService;

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
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new Date();
	
	@ManyToOne
	private Funcionario funcionario;
	
	@NotEmpty(message = "A descrição do problema não pode ser vazia.")
	@Column(nullable = false)
	private String problema;
	
	@OneToOne(cascade = CascadeType.ALL)
	private FinalizacaoChamadoReforma finalizacao;
	
	public ChamadoReforma(Aluguel aluguel){
		this.aluguel = aluguel;
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

	public List<AcontecimentoChamado> getAcontecimentos() {
		return new AcontecimentoChamadoService().findByChamadoReforma(this);
	}

	@Override
	public Date getDataGeracao() {
		return data;
	}

	@Override
	public Date getDataVencimento() {
		return null;
	}

	@Override
	public String getDescricao() {
		return "Chamado de Reforma"; 
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDataFechamento() {
		// TODO Auto-generated method stub
		return null;
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
	
	private transient static ChamadoReformaService c;
	private transient static PendenciaService pService;
	
	@Override
	public GenericService<?> getService() {
		if(c == null)
			c = new ChamadoReformaService();
		return c;
	}

	@Override
	public void finalizarPendencia() throws Exception {
		if(pService == null)
			pService = new PendenciaService();
		pService.finalizarPendencia(this);
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
