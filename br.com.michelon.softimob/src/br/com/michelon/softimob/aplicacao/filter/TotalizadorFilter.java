package br.com.michelon.softimob.aplicacao.filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Label;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.ReflectionHelper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class TotalizadorFilter extends GenericFilter {

	private final Map<String, Label> labels;

	public TotalizadorFilter(Map<String, Label> totalizadores) {
		this.labels = totalizadores;
	}

	@Override
	public Object[] filter(Viewer viewer, Object parent, Object[] elements) {
		Map<String, BigDecimal> totais = criarMapTotais();

		int size = elements.length;
		ArrayList<Object> out = Lists.newArrayListWithCapacity(size);
		for (int i = 0; i < size; ++i) {
			Object element = elements[i];
			if (select(viewer, parent, element)) {
				out.add(element);
				totalizar(element, totais);
			}
		}

		atualizarLabels(totais, labels);
		
		return out.toArray();
	}

	private void atualizarLabels(Map<String, BigDecimal> totais, Map<String, Label> labels) {
		for(String atribute : labels.keySet()){
			BigDecimal total = totais.get(atribute);
			labels.get(atribute).setText(FormatterHelper.formatObject(total));
		}
	}

	private Map<String, BigDecimal> criarMapTotais() {
		Map<String, BigDecimal> totais = Maps.newHashMap();
		for (String att : labels.keySet()) {
			totais.put(att, BigDecimal.ZERO);
		}
		return totais;
	}

	private void totalizar(Object element, Map<String, BigDecimal> totais) {
		for (String att : labels.keySet()) {
			BigDecimal valor;
			
			try {
				valor = (BigDecimal) ReflectionHelper.getAtribute(element, att);
			} catch (Exception e) {
				valor = BigDecimal.ZERO;
			}
			
			totais.put(att, totais.get(att).add(valor));
		}
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return true;
	}

}
