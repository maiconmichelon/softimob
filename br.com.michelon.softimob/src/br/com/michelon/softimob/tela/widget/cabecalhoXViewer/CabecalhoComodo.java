package br.com.michelon.softimob.tela.widget.cabecalhoXViewer;

import java.util.List;

import br.com.michelon.softimob.modelo.Comodo;
import br.com.michelon.softimob.tela.widget.xViewer.CabecalhoXViewer;

public class CabecalhoComodo extends CabecalhoXViewer<Comodo>{

	public CabecalhoComodo(List<Comodo> subElements) {
		super(Comodo.class, subElements, "Cômodo", "Descrição");
	}

}
