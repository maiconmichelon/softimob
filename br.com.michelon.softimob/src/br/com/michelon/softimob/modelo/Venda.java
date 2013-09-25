package br.com.michelon.softimob.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import br.com.michelon.softimob.aplicacao.service.ContratoPrestacaoServicoService;
import br.com.michelon.softimob.aplicacao.service.ImovelService;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Venda extends VendaAluguel {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Cliente proprietario;
	
	@Column(nullable = false)
	private Boolean fechado = false;
	
	public Venda() {
//		ParametrosEmpresa params = ParametrosEmpresa.getInstance();
//		if(params != null)
//			setModeloContrato(params.getContratoVenda());
	}
	
	public Cliente getProprietario() {
		return proprietario;
	}
	
	public void setProprietario(Cliente proprietario) {
		this.proprietario = proprietario;
	}
	
	public Boolean getFechado() {
		return fechado;
	}
	
	public void setFechado(Boolean fechado) throws Exception {
		if(fechado){
			setProprietario(getContrato().getImovel().getProprietario());
			
			getContrato().getImovel().setProprietario(getCliente());
			getContrato().setResolvido(true);
			
			new ImovelService().salvar(getContrato().getImovel());
			new ContratoPrestacaoServicoService().salvar(getContrato());
		}
		
		this.fechado = fechado;
	}
	
}