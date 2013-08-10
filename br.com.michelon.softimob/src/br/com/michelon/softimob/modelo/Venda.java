package br.com.michelon.softimob.modelo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import br.com.michelon.softimob.aplicacao.service.CheckListService;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Venda extends VendaAluguel {

	private static final long serialVersionUID = 1L;

	public Venda() {
		ParametrosEmpresa instance = ParametrosEmpresa.getInstance();
		if(instance != null){
			getItensCheckList().addAll(new CheckListService().getNewItens(instance.getCheckListVenda()));
		}
	}

}