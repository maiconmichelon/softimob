package br.com.michelon.softimob.modelo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Venda extends VendaAluguel {

	private static final long serialVersionUID = 1L;

	public Venda() {
		ParametrosEmpresa params = ParametrosEmpresa.getInstance();
		if(params != null)
			setModeloContrato(params.getContratoVenda());
	}
	
}