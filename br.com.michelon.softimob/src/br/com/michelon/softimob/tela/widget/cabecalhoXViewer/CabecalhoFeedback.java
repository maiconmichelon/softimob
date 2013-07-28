package br.com.michelon.softimob.tela.widget.cabecalhoXViewer;

import java.util.List;

import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.modelo.Feedback;
import br.com.michelon.softimob.tela.widget.xViewer.CabecalhoXViewer;

public class CabecalhoFeedback extends CabecalhoXViewer<Feedback>{

	public CabecalhoFeedback(List<Feedback> subElements) {
		super(Feedback.class, subElements, ImageRepository.FEEDBACK_16.getImage(), "Data do Feedback", "Cliente", "Funcionário", "Observações");
	}

}
