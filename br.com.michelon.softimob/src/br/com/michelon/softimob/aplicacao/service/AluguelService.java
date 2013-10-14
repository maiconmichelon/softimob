package br.com.michelon.softimob.aplicacao.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.michelon.softimob.aplicacao.exception.ViolacaoForeignKey;
import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Pendencia;
import br.com.michelon.softimob.persistencia.AluguelDAO;

public class AluguelService extends GenericService<Aluguel>{

	private Logger log = Logger.getLogger(getClass());
	
	public AluguelService() {
		super(AluguelDAO.class);
	}
	
	@Override
	protected AluguelDAO getRepository() {
		return (AluguelDAO) super.getRepository();
	}
	
	public List<Aluguel> findByImovel(Imovel imovel){
		return getRepository().findByImovel(imovel);
	}
	
	public List<Pendencia> findPendencias(Date dataHoje){
		return getRepository().findByResolvidoFalseAndDataVencimentoLessThan(dataHoje);
	}

	public void finalizarPendencia(Aluguel aluguel) {
		try {
			aluguel.setResolvido(true);
			salvar(aluguel);
		} catch (Exception e) {
			log.error("Erro ao finalizar aluguel como pendencia.", e);
		}
	}

	public String geraObservacoesContaAluguel(Aluguel aluguel) {
		return String.format("Aluguel da casa %s para %s", aluguel.getContrato().getImovel().toString(), aluguel.getCliente());
	}

	public Long findContPendencias(Date dataHoje) {
		return getRepository().findContPendencia(dataHoje);
	}
	
	@Override
	public void removerAtivarOuDesativar(Aluguel registro) throws Exception {
		if(!registro.getChamados().isEmpty())
			throw new ViolacaoForeignKey("Antes de excluir a locação remova todos os chamados de reforma.");
		if(!registro.getComissoes().isEmpty())
			throw new ViolacaoForeignKey("Antes de excluir a locação remova todas as suas comissões.");
		if(!registro.getParcelas().isEmpty())
			throw new ViolacaoForeignKey("Antes de excluir a locação remova todas as parcelas da locação.");
		if(!registro.getVistorias().isEmpty())
			throw new ViolacaoForeignKey("Antes de excluir a locação remova todas as vistorias.");
		super.removerAtivarOuDesativar(registro);
	}
	
}
