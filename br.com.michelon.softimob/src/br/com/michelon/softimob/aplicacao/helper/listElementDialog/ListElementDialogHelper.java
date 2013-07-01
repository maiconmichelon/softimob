package br.com.michelon.softimob.aplicacao.helper.listElementDialog;

import java.util.Arrays;
import java.util.List;

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
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.modelo.Comissionado;

public class ListElementDialogHelper {
	
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

	public enum TipoDialog{
		
		FUNCIONARIO("Funcionários", "Selecione um funcionário.", ImageRepository.FUNCIONARIO_16),
		CLIENTE("Clientes", "Selecione um cliente.", ImageRepository.CLIENTE_16, Cliente.class), 
		COMODO("Cômodos", "Selecione um cômodo.", ImageRepository.COMODO16),
		TIPO_IMOVEL("Tipo de imóvel", "Selecione um tipo de imóvel.", ImageRepository.TIPO_IMOVEL_16),
		IMOVEL("Imóveis", "Selecione um imóvel.", ImageRepository.IMOVEL_16), 
		PLANOCONTA("Plano de Contas", "Selecione uma conta.", ImageRepository.PLANO_CONTA_16), 
		COMISSIONADO("Comissionados", "Selecione um cliente ou funcionário.", ImageRepository.COMISSAO_16, Comissionado.class), 
		MODELO_CONTRATO("Modelos de Contrato", "Selecione um modelo de contrato.", ImageRepository.CONTRATO_16),
		CONTRATO_SERVICO("Contratos de prestação de serviço", "Selecione um contrato", ImageRepository.CONTRATO_16),
		CHECK_LIST("Modelos de check list", "Selecione uma check list", ImageRepository.CHECKLIST_16),
		INDICE("Índices", "Selecione um índice", ImageRepository.INDICE_16), 
		PESSOA_FISICA("Pessoas Físicas", "Selecione uma pessoa física", ImageRepository.CLIENTE_16),
		ORIGEM_CONTA("Origens de Contas", "Selecione uma origem para sua conta", ImageRepository.ORIGEM_CONTA_16)
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
				return new FuncionarioService().findAll().toArray();
			} else if(equals(CLIENTE)){
				return new ClienteService().findAll().toArray();
			} else if(equals(PLANOCONTA)){
				return new PlanoContaService().findAll().toArray();
			} else if(equals(COMODO)){
				return new TipoComodoService().findAll().toArray();
			} else if(equals(TIPO_IMOVEL)){
				return new TipoImovelService().findAll().toArray();
			} else if(equals(MODELO_CONTRATO)){
				return new ModeloContratoService().findAll().toArray();
			} else if(equals(CONTRATO_SERVICO)){
				return new ContratoPrestacaoServicoService().findAll().toArray();
			} else if(equals(CHECK_LIST)){
				return new CheckListService().findAll().toArray();
			} else if(equals(INDICE)){
				return new IndiceService().findAll().toArray();
			} else if(equals(COMISSIONADO)){
				return new ComissionadoService().findAll().toArray();
			} else if(equals(PESSOA_FISICA)){
				return new PessoaFisicaService().findAll().toArray();
			} else if(equals(ORIGEM_CONTA)){
				return new OrigemContaService().findAll().toArray();
			} else{
				return null;
			}
		}
	}
	
}