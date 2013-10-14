package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.aplicacao.exception.ViolacaoForeignKey;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.persistencia.MovimentacaoContabilDAO;

public class MovimentacaoContabilService extends GenericService<MovimentacaoContabil>{

	public MovimentacaoContabilService() {
		super(MovimentacaoContabilDAO.class);
	}

	@Override
	public void removerAtivarOuDesativar(MovimentacaoContabil registro) throws Exception {
		ContaPagarReceber contaPagarReceber = new ContaPagarReceberService().findByMovimentacao(registro);
		if(contaPagarReceber != null)
			throw new ViolacaoForeignKey("Essa movimentação possui uma conta, para exclui-la somente estornando o pagamento.");
		super.removerAtivarOuDesativar(registro);
	}
	
}
