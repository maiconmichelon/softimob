package br.com.michelon.softimob.tela.widget.imovelXViewer;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.nebula.widgets.xviewer.XViewerColumn;
import org.eclipse.nebula.widgets.xviewer.XViewerLabelProvider;
import org.eclipse.swt.graphics.Image; 
import org.jsoup.helper.StringUtil;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.modelo.Chave;
import br.com.michelon.softimob.modelo.Comodo;
import br.com.michelon.softimob.modelo.Feedback;
import br.com.michelon.softimob.modelo.Imovel;

public class ImovelXViewerLabelProvider extends XViewerLabelProvider {

	public ImovelXViewerLabelProvider(ImovelXViewer viewer) {
		super(viewer);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getColumnText(Object element, XViewerColumn xCol, int columnIndex) throws Exception {
		if(element instanceof String){
			return element.toString();
		}else if(element instanceof Imovel){
			Imovel imovel = (Imovel) element;
			
			if(xCol.equals(ImovelXViewerFactory.angariador))
				return imovel.getAngariador() == null ? "" : imovel.getAngariador().getNome();
			if(xCol.equals(ImovelXViewerFactory.metragem))
				return imovel.getMetragem() == null ? StringUtils.EMPTY : imovel.getMetragem().toString();
			if(xCol.equals(ImovelXViewerFactory.codigo))
				return imovel.getId().toString();
			if(xCol.equals(ImovelXViewerFactory.proprietario))
				return imovel.getProprietario() == null ? "" : imovel.getProprietario().getNome();
//			if(xCol.equals(ImovelXViewerFactory.status))
//				return imovel.getStatus().toString();
			if(xCol.equals(ImovelXViewerFactory.tipo))
				return imovel.getTipo() == null ? "" : imovel.getTipo().getDescricao();
		}else if(element instanceof Chave){
			if(columnIndex == 0)
				return ((Chave)element).getNumero();
		}else if(element instanceof Feedback){
			
			if(columnIndex == 0)
				return FormatterHelper.getSimpleDateFormat().format(((Feedback)element).getData());
			if(columnIndex == 1)
				return ((Feedback)element).getFuncionario() == null ? "" : ((Feedback)element).getFuncionario().getNome();
//			if(columnIndex == 2)
//				return ((HistoricoImovel)element).getFeedback();
		}else if(element instanceof Comodo){
			
			if(columnIndex == 0)
				return ((Comodo)element).getTipoComodo().getNome();
			if(columnIndex == 1)
				return ((Comodo)element).getDescricao();
		}else if(element instanceof List){
			
			List<Object> elements = (List<Object>) element;
				
			if(xCol.equals(ImovelXViewerFactory.codigo) && elements.size() > 0){
				if(elements.get(0) instanceof Comodo)
					return "Cômodos";
				if(elements.get(0) instanceof Chave)
					return "Chaves";
				if(elements.get(0) instanceof Feedback)
					return "Históricos";
			}
			if(xCol.equals(ImovelXViewerFactory.metragem))
				return String.valueOf(elements.size());
		}
		return "";
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
