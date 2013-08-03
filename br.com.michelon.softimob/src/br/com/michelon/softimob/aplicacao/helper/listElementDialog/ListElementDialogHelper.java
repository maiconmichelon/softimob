package br.com.michelon.softimob.aplicacao.helper.listElementDialog;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.helper.ReflectionHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.service.CheckListService;
import br.com.michelon.softimob.aplicacao.service.ClienteService;
import br.com.michelon.softimob.aplicacao.service.ComissionadoService;
import br.com.michelon.softimob.aplicacao.service.ContratoPrestacaoServicoService;
import br.com.michelon.softimob.aplicacao.service.FuncionarioService;
import br.com.michelon.softimob.aplicacao.service.IndiceService;
import br.com.michelon.softimob.aplicacao.service.ModeloContratoService;
import br.com.michelon.softimob.aplicacao.service.OrigemContaService;
import br.com.michelon.softimob.aplicacao.service.PessoaFisicaService;
import br.com.michelon.softimob.aplicacao.service.PlanoContaService;
import br.com.michelon.softimob.aplicacao.service.TipoComodoService;
import br.com.michelon.softimob.aplicacao.service.TipoImovelService;
import br.com.michelon.softimob.modelo.CheckList;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.modelo.Comissionado;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico.TipoContrato;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Indice;
import br.com.michelon.softimob.modelo.OrigemConta;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.modelo.TipoComodo;
import br.com.michelon.softimob.modelo.TipoImovel;

public class ListElementDialogHelper {
	
	private static Logger log = Logger.getLogger(ListElementDialogHelper.class);
	
	public static void addListElementDialogToText(final TipoDialog tipoDialog, Text text, final WritableValue value, final String property){
		addListElementDialogToText(tipoDialog, text, value, property, null);
	}
	
	public static void addListElementDialogToText(final TipoDialog tipoDialog, Text text, final WritableValue value, final String property, final OkListElementDialogListener listener){
		text.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.F1){
					openDialogAndSetValue(tipoDialog, value, property, listener);
				}
			}
		});
	}
	
	// Esse é isolado dos outros, ele faz o simples, só executa o listener quando o usuario seleciona um objeto e da OK
	public static void addSelectionListDialogToButton(final TipoDialog tipoDialog, Button button, final OkListElementDialogListener listener){
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tipoDialog.openDialogAndExecuteListeners(new OkListElementDialogListener() {
					@Override
					public void ok(Object obj) {
						listener.ok(obj);
					}
				});
			}
		});
	}
	
	public static void addSelectionListDialogToButton(final TipoDialog tipoDialog, Button button, final WritableValue value, final String property){
		addSelectionListDialogToButton(tipoDialog, button, value, property, null);
	}
	
	public static void addSelectionListDialogToButton(final TipoDialog tipoDialog, Button button, final WritableValue value, final String property, final OkListElementDialogListener listener){
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openDialogAndSetValue(tipoDialog, value, property, listener);
			}
		});
	}
	
	private static void openDialogAndSetValue(final TipoDialog tipoDialog, final WritableValue value, final String property, final OkListElementDialogListener listener) {

		tipoDialog.openDialogAndExecuteListeners(new OkListElementDialogListener() {
			@Override
			public void ok(Object obj) {
				try{
					Object copy = value.getValue();
					value.setValue(null);
					
					ReflectionHelper.setAtribute(copy, property, obj, tipoDialog.getClazz());
					
					value.setValue(copy);
					
					if(listener != null)
						listener.ok(obj);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
	}

	public static void addSelectionToRemoveButton(Button btn, final IObservableValue value, final String property, final Class<?> clazz){
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ReflectionHelper.setAtribute(value.getValue(), property, null, clazz);
					
					Object obj = value.getValue();
					value.setValue(null);
					value.setValue(obj);
				} catch (Exception e1) {
					log.error("Erro ao remover objeto.", e1);
				}
			}
		});
	}
	
	public enum TipoDialog{
		
		FUNCIONARIO("Funcionários", "Selecione um funcionário.", ImageRepository.FUNCIONARIO_16, Funcionario.class),
		CLIENTE("Clientes", "Selecione um cliente.", ImageRepository.CLIENTE_16, Cliente.class), 
		COMODO("Cômodos", "Selecione um cômodo.", ImageRepository.COMODO16, TipoComodo.class),
		TIPO_IMOVEL("Tipo de imóvel", "Selecione um tipo de imóvel.", ImageRepository.TIPO_IMOVEL_16, TipoImovel.class),
		IMOVEL("Imóveis", "Selecione um imóvel.", ImageRepository.IMOVEL_16, Imovel.class), 
		PLANOCONTA("Plano de Contas", "Selecione uma conta.", ImageRepository.PLANO_CONTA_16, PlanoConta.class), 
		COMISSIONADO("Comissionados", "Selecione um cliente ou funcionário.", ImageRepository.COMISSAO_16, Comissionado.class), 
		MODELO_CONTRATO("Modelos de Contrato", "Selecione um modelo de contrato.", ImageRepository.CONTRATO_16, ContratoPrestacaoServico.class),
		CONTRATO_SERVICO_TODOS("Contratos de prestação de serviço", "Selecione um contrato", ImageRepository.CONTRATO_16, ContratoPrestacaoServico.class),
		CONTRATO_SERVICO_LOCACAO("Contratos de prestação de serviço", "Selecione um contrato", ImageRepository.CONTRATO_16, ContratoPrestacaoServico.class),
		CONTRATO_SERVICO_VENDA("Contratos de prestação de serviço", "Selecione um contrato", ImageRepository.CONTRATO_16, ContratoPrestacaoServico.class),
		CHECK_LIST("Modelos de check list", "Selecione uma check list", ImageRepository.CHECKLIST_16, CheckList.class),
		INDICE("Índices", "Selecione um índice", ImageRepository.INDICE_16, Indice.class), 
		PESSOA_FISICA("Pessoas Físicas", "Selecione uma pessoa física", ImageRepository.CLIENTE_16, Cliente.class),
		ORIGEM_CONTA("Origens de Contas", "Selecione uma origem para sua conta", ImageRepository.ORIGEM_CONTA_16, OrigemConta.class)
		;
		
		private final String title;
		private final String message;
		private ImageRepository images;
		private Class<?> clazz;
		
		private TipoDialog(String title, String message, ImageRepository images) {
			this.title = title;
			this.message = message;
			this.images = images;
		}
		
		private TipoDialog(String title, String message, ImageRepository images, Class<?> clazz) {
			this.title = title;
			this.message = message;
			this.images = images;
			this.clazz = clazz;
		}
		
		public String getMessage() {
			return message;
		}
		
		public String getTitle() {
			return title;
		}
		
		public ImageRepository getImages() {
			return images;
		}
		
		public void openDialogAndExecuteListeners(OkListElementDialogListener listener){
			openDialogAndExecuteListeners(Arrays.asList(listener));
		}
		
		public void openDialogAndExecuteListeners(List<OkListElementDialogListener> listeners){
			ElementListSelectionDialog dialog = openDialog();
			
			if(dialog.open() == IDialogConstants.OK_ID){
				for(OkListElementDialogListener listener : listeners)
					listener.ok(dialog.getFirstResult());
//			} else {
//				for(OkListElementDialogListener listener : listeners)
//					listener.ok(null);
			}
		}
		
		public String getDescription(Object obj){
			return obj.toString();
		}
		
		public Class<?> getClazz() {
			return clazz;
		}
		
		public ElementListSelectionDialog openDialog(){
			ElementListSelectionDialog dialog = new ElementListSelectionDialog(ShellHelper.getActiveShell(), new LabelProvider(){
				@Override
				public String getText(Object element) {
					return getDescription(element);
				}
			});
			
			dialog.setTitle(getTitle());
			dialog.setMessage(getMessage());
			dialog.setElements(getElements());
			dialog.setImage(getImages().getImage());
			
			return dialog;
		}
		
		public Object[] getElements(){
			if(equals(FUNCIONARIO)){
				return new FuncionarioService().findAtivos().toArray();//
			} else if(equals(CLIENTE)){
				return new ClienteService().findAtivos().toArray();
			} else if(equals(PLANOCONTA)){
				return new PlanoContaService().findAtivos().toArray();
			} else if(equals(COMODO)){
				return new TipoComodoService().findAtivos().toArray();
			} else if(equals(TIPO_IMOVEL)){
				return new TipoImovelService().findAtivos().toArray();
			} else if(equals(MODELO_CONTRATO)){
				return new ModeloContratoService().findAll().toArray();
			} else if(equals(CONTRATO_SERVICO_TODOS)){
				return new ContratoPrestacaoServicoService().findAll().toArray();
			} else if(equals(CONTRATO_SERVICO_LOCACAO)){
				return new ContratoPrestacaoServicoService().findByTipo(TipoContrato.LOCACAO).toArray();
			} else if(equals(CONTRATO_SERVICO_VENDA)){
				return new ContratoPrestacaoServicoService().findByTipo(TipoContrato.VENDA).toArray();
			} else if(equals(CHECK_LIST)){
				return new CheckListService().findAll().toArray();
			} else if(equals(INDICE)){
				return new IndiceService().findAll().toArray();
			} else if(equals(COMISSIONADO)){
				return new ComissionadoService().findAtivos().toArray();
			} else if(equals(PESSOA_FISICA)){
				return new PessoaFisicaService().findAtivos().toArray();
			} else if(equals(ORIGEM_CONTA)){
				return new OrigemContaService().findAtivos().toArray();
			} else{
				return null;
			}
		}
	}
	
}