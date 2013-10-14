package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.aplicacao.exception.ViolacaoForeignKey;
import br.com.michelon.softimob.modelo.Venda;
import br.com.michelon.softimob.persistencia.VendaDAO;

public class VendaService extends GenericService<Venda>{

	public VendaService() {
		super(VendaDAO.class);
	}
	
	@Override
	public void removerAtivarOuDesativar(Venda registro) throws Exception {
		if(!registro.getComissoes().isEmpty())
			throw new ViolacaoForeignKey("Antes de excluir a venda remova todas as suas comissões.");
		if(!registro.getVistorias().isEmpty())
			throw new ViolacaoForeignKey("Antes de excluir a venda remova todas as vistorias.");
		super.removerAtivarOuDesativar(registro);
	}
	
	
}
