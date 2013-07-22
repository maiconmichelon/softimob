package br.com.michelon.softimob.tela.widget.cabecalhoXViewer;

import java.util.List;

import br.com.michelon.softimob.modelo.Chave;
import br.com.michelon.softimob.tela.widget.xViewer.CabecalhoXViewer;

public class CabecalhoChave extends CabecalhoXViewer<Chave>{

	public CabecalhoChave(List<Chave> subElements) {
		super(Chave.class, subElements, "Número", "Localização");
	}

}
