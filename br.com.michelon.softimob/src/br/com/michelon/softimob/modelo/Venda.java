package br.com.michelon.softimob.modelo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Venda extends VendaAluguel {

	private static final long serialVersionUID = 1L;

	public Venda() {
		ParametrosEmpresa instance = ParametrosEmpresa.getInstance();
		if(instance != null){
			CheckList chkList = instance.getCheckListVenda();
			if (chkList != null) {
				for (ItemCheckList item : chkList.getItens()) {
					getItensCheckList().add(new ItemCheckListDescricao(item));
				}
			}
		}
	}

}