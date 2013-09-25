package br.com.michelon.softimob.modelo;

import java.io.Serializable;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;

import br.com.michelon.softimob.aplicacao.service.VistoriaService;

import com.google.common.collect.Lists;

@Entity
public class Vistoria implements Serializable, ContainsPhotos{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Informe a data da vistoria.")
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date data = new Date();
	
	@NotNull(message = "Selecione o funcionário responsável pela vistoria.")
	@ManyToOne(optional=false)
	private Funcionario funcionario;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Arquivo> fotos = Lists.newArrayList();
	
	@Column
	private String observacoes = StringUtils.EMPTY;

	@ManyToOne(optional = false)
	private VendaAluguel vendaAluguel;
	
	@NotNull()
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemCheckList> itensCheckList = Lists.newArrayList();
	
	@SuppressWarnings("unused")
	private Vistoria(){}
	
	public Vistoria(Venda venda){
		this.vendaAluguel = venda;
		addCheckList();
	}
	
	public Vistoria(Aluguel aluguel){
		this.vendaAluguel = aluguel;
		addCheckList();
	}
	
	private void addCheckList() {
		ParametrosEmpresa instance = ParametrosEmpresa.getInstance();
		if(instance != null){
			CheckList chkList = instance.getCheckListVistoria();
			if (chkList != null) {
				for (Item item : chkList.getItens()) {
					getItensCheckList().add(new ItemCheckList(item.getNome(), item.getObrigatorio()));
				}
			}
		}
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

	public List<Arquivo> getFotos() {
		return fotos;
	}
	
	public void setFotos(List<Arquivo> fotos) {
		this.fotos = fotos;
	}
	
	public VendaAluguel getVendaAluguel() {
		return vendaAluguel;
	}
	
	public void setVendaAluguel(VendaAluguel vendaAluguel) {
		this.vendaAluguel = vendaAluguel;
	}
	
	public List<ItemCheckList> getItensCheckList() {
		return itensCheckList;
	}
	
	public void setItensCheckList(List<ItemCheckList> itensCheckList) {
		this.itensCheckList = itensCheckList;
	}

	private transient static VistoriaService service;
	
	@Override
	public Integer getNumeroFotos() {
		if(service == null)
			service = new VistoriaService();
		return service.sizeImages(this);
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
		Vistoria other = (Vistoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
