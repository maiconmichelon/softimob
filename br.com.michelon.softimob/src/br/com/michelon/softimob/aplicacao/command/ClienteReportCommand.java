package br.com.michelon.softimob.aplicacao.command;

import java.util.Date;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.ReportHelper;
import br.com.michelon.softimob.aplicacao.listener.OnErrorListener;
import br.com.michelon.softimob.aplicacao.listener.OnSuccessfulListener;

import com.google.common.collect.Maps;

public class ClienteReportCommand extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Map<String, Object> m = Maps.newHashMap();
		m.put("hoje", new Date());
		ReportHelper.gerarRelatorio(m, "reports/clientes.jasper", new OnErrorListener() {
			@Override
			public void onError(String message) {
				DialogHelper.openError(message);
			}
		}, new OnSuccessfulListener() {
			@Override
			public void onSucessful(String message) {}
		});
		return null;
	}

}
