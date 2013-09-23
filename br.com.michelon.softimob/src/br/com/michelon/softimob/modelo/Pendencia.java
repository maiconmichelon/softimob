package br.com.michelon.softimob.modelo;

import java.math.BigDecimal;
import java.util.Date;

import org.eclipse.ui.IEditorInput;

import br.com.michelon.softimob.aplicacao.service.GenericService;

public interface Pendencia {

	public enum TipoPendencia{

		ALUGUEL("Aluguel", Aluguel.class),
		REFORMA("Chamado de reforma", ChamadoReforma.class),
		COMISSAO("Comissão", Comissao.class),
		CONTA("Conta", ContaPagarReceber.class),
		CONTRATO("Contrato de Prestação de Serviço", ContratoPrestacaoServico.class),
		PROPOSTA("Proposta", Proposta.class),
		RESERVA("Reserva", Reserva.class);
		
		private final Class<?> clazz;
		private final String nome;

		TipoPendencia(String nome, Class<?> clazz){
			this.nome = nome;
			this.clazz = clazz;
		}
		
		public String getNome() {
			return nome;
		}
		
		public Class<?> getClazz() {
			return clazz;
		}
		
		@Override
		public String toString() {
			return getNome();
		}
		
	}

	Date getDataGeracao();
	Date getDataVencimento();
	Date getDataFechamento();
	
	String getDescricao();
	
	String getIdEditor();
	IEditorInput getEditorInput();
	
	BigDecimal getValor();
	
	GenericService<?> getService();
	
	void finalizarPendencia() throws Exception;
	
	boolean confirmarFinalizarPendencia();
	
}
