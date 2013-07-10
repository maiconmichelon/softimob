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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

@Entity
public class Vistoria implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Informe a data da vistoria.")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date data = new Date();
	
	@NotNull(message = "Selecione o funcionário responsável pela vistoria.")
	@ManyToOne(optional=false)
	private Funcionario funcionario;
	
	@Lob
	private Byte[] arquivo;
	
	@Column
	private String observacoes = StringUtils.EMPTY;

	@ManyToOne(optional = false)
	private VendaAluguel vendaAluguel;
	
	@NotNull()
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemCheckListDescricao> itensCheckList = Lists.newArrayList();
	
	@SuppressWarnings("unused")
	private Vistoria(){}
	
	public Vistoria(Venda venda){
		this.vendaAluguel = venda;
	}
	
	public Vistoria(Aluguel aluguel){
		this.vendaAluguel = aluguel;
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

	public Byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(Byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public VendaAluguel getVendaAluguel() {
		return vendaAluguel;
	}
	
	public void setVendaAluguel(VendaAluguel vendaAluguel) {
		this.vendaAluguel = vendaAluguel;
	}
	
	public List<ItemCheckListDescricao> getItensCheckList() {
		return itensCheckList;
	}
	
	public void setItensCheckList(List<ItemCheckListDescricao> itensCheckList) {
		this.itensCheckList = itensCheckList;
	}
	
}
