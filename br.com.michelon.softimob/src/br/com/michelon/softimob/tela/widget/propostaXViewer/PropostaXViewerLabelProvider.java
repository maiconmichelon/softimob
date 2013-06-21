package br.com.michelon.softimob.tela.widget.propostaXViewer;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.nebula.widgets.xviewer.XViewerColumn;
import org.eclipse.nebula.widgets.xviewer.XViewerLabelProvider;
import org.eclipse.swt.graphics.Image;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.modelo.Proposta;

public class PropostaXViewerLabelProvider extends XViewerLabelProvider {

	public PropostaXViewerLabelProvider(PropostaXViewer viewer) {
		super(viewer);
	}
	
	@Override
	public String getColumnText(Object element, XViewerColumn xCol, int columnIndex) throws Exception {
		if(element instanceof String){
			return element.toString();
		}else if(element instanceof Proposta){
			Proposta proposta = (Proposta) element;
			
			if(xCol.equals(PropostaXViewerFactory.imovel))
				return proposta.getImovel() == null ? StringUtils.EMPTY : proposta.getImovel().getId().toString();
			if(xCol.equals(PropostaXViewerFactory.funcionario))
				return proposta.getFuncionario() == null ? StringUtils.EMPTY : proposta.getFuncionario().getNome();
			if(xCol.equals(PropostaXViewerFactory.data))
				return proposta.getData() == null ? StringUtils.EMPTY : FormatterHelper.getSimpleDateFormat().format(proposta.getData());
			if(xCol.equals(PropostaXViewerFactory.observacoes))
				return proposta.getObservacoes() == null ? StringUtils.EMPTY : proposta.getObservacoes();
			if(xCol.equals(PropostaXViewerFactory.realizador))
				return proposta.getRealizador();
			if(xCol.equals(PropostaXViewerFactory.valor))
				return proposta.getValor() == null ? StringUtils.EMPTY : proposta.getValor().toString();
		}
		return StringUtils.EMPTY;
	}
	
	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override
	public Image getColumnImage(Object element, XViewerColumn xCol, int columnIndex) throws Exception {
		return null;
	}

}
