package br.com.michelon.softimob.tela.widget.propostaXViewer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewer;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewerColumn;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewerContentProvider;
import br.com.michelon.softimob.tela.widget.xViewer.XViewerColumnProperties;

import com.google.common.collect.Maps;

public class PropostaGenericXViewer {

	public static GenericXViewer<Proposta> createXviewer(Composite cp){
		
		Map<Class<?>, XViewerColumnProperties> m1 = Maps.newHashMap();
		m1.put(Proposta.class, new XViewerColumnProperties("data", ImageRepository.PROPOSTA_16.getImage(), false));
		GenericXViewerColumn c1 = new GenericXViewerColumn("Data", 130, m1);
		
		Map<Class<?>, XViewerColumnProperties> m2 = Maps.newHashMap();
		m2.put(Proposta.class, new XViewerColumnProperties("dataFechamento"));
		GenericXViewerColumn c2 = new GenericXViewerColumn("Data de Fechamento", 130, m2);
		
		Map<Class<?>, XViewerColumnProperties> m3 = Maps.newHashMap();
		m3.put(Proposta.class, new XViewerColumnProperties("clienteProprietario.nome"));
		GenericXViewerColumn c3 = new GenericXViewerColumn("Quem Propôs", 200, m3);
		
		Map<Class<?>, XViewerColumnProperties> m4 = Maps.newHashMap();
		m4.put(Proposta.class, new XViewerColumnProperties("funcionario.nome"));
		GenericXViewerColumn c4 = new GenericXViewerColumn("Funcionário", 200, m4);

		Map<Class<?>, XViewerColumnProperties> m5 = Maps.newHashMap();
		m5.put(Proposta.class, new XViewerColumnProperties("valor"));
		GenericXViewerColumn c5 = new GenericXViewerColumn("Valor", 70, m5);
		
		Map<Class<?>, XViewerColumnProperties> m6 = Maps.newHashMap();
		m6.put(Proposta.class, new XViewerColumnProperties("observacoes"));
		GenericXViewerColumn c6 = new GenericXViewerColumn("Observações", 300, m6);
		
		Map<Class<?>, XViewerColumnProperties> m7 = Maps.newHashMap();
		m7.put(Proposta.class, new XViewerColumnProperties("statusExtenso"));
		GenericXViewerColumn c7 = new GenericXViewerColumn("Status", 90, m7);
		
		List<GenericXViewerColumn> columns = Arrays.asList(c1, c2, c3, c4, c5, c6, c7);
		
		GenericXViewer<Proposta> viewerMovimentacoes = new GenericXViewer<Proposta>(cp, SWT.BORDER | SWT.FULL_SELECTION, columns, new GenericXViewerContentProvider() {
			
			@Override
			public Object[] getChildrenElements(Object parentElement) {
				if(parentElement instanceof Proposta){
					return new Object[]{((Proposta)parentElement).getContraProposta()};
				}
				return null;
			}
		});
		
		return viewerMovimentacoes;
	}
	
}
