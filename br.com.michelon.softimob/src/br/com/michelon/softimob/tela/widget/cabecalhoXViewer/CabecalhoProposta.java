package br.com.michelon.softimob.tela.widget.cabecalhoXViewer;

import java.util.List;

import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.tela.widget.xViewer.CabecalhoXViewer;

public class CabecalhoProposta extends CabecalhoXViewer<Proposta>{

	public CabecalhoProposta(List<Proposta> subElements) {
		super(Proposta.class, subElements, ImageRepository.PROPOSTA_16.getImage(), "Data da Proposta", "Cliente", "Funcionário", "Valor", "Observações");
	}

}
