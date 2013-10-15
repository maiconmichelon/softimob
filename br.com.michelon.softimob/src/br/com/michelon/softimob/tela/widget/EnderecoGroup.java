package br.com.michelon.softimob.tela.widget;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.cep.CEP;
import br.com.michelon.softimob.aplicacao.cep.CEPNaoEncontradoException;
import br.com.michelon.softimob.aplicacao.cep.CEPServiceFactory;
import br.com.michelon.softimob.aplicacao.cep.CEPServiceFailureException;
import br.com.michelon.softimob.aplicacao.helper.NumberHelper;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.service.BairroService;
import br.com.michelon.softimob.aplicacao.service.CidadeService;
import br.com.michelon.softimob.aplicacao.service.EnderecoService;
import br.com.michelon.softimob.aplicacao.service.EstadoService;
import br.com.michelon.softimob.aplicacao.service.RuaService;
import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.modelo.Endereco;
import br.com.michelon.softimob.modelo.Estado;
import br.com.michelon.softimob.modelo.Rua;

public class EnderecoGroup {

	private Logger log = Logger.getLogger(getClass());
	
	private Group control;
	private ComboViewer cvCidades;
	private ComboViewer cvBairros;
	private ComboViewer cvRuas;
	private Text txtNumero;
	private Text txtComplemento;
	private Text txtCep;

	private WritableValue value = WritableValue.withValueType(Endereco.class);

	private EnderecoService enderecoService = new EnderecoService();
	private EstadoService estadoService = new EstadoService();
	private CidadeService cidadeService = new CidadeService();
	private BairroService bairroService = new BairroService();
	private RuaService ruaService = new RuaService();
	private ComboViewer cvUF;

	private DataBindingContext context;

	public EnderecoGroup(Composite parent, Endereco endereco, int style) {
		createComponents(parent, style);

		setEndereco(endereco);
	}

	private void createComponents(Composite parent, int style) {
		control = new Group(parent, style);
		control.setText("Endereço");
		
		GridLayout gridLayout = new GridLayout(3, false);
		gridLayout.verticalSpacing = 10;
		control.setLayout(gridLayout);

		Label lblCep_1 = new Label(control, SWT.NONE);
		lblCep_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCep_1.setText("CEP");

		CEPTextField textField = new CEPTextField(control);
		txtCep = textField.getControl();
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_1.widthHint = 124;
		txtCep.setLayoutData(gd_text_1);
		txtCep.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				Text txt = (Text) e.widget;
				String txtCep = NumberHelper.extractNumbers(txt.getText());
				Rua rua = null;
				try{
					CEP cep = CEPServiceFactory.getCEPService().obtemPorNumeroCEP(txtCep);

					rua = cadastrarCep(cep);
					if(rua != null)
						selecionarRua(rua);
				}catch(CEPNaoEncontradoException ce){
					log.info("Cep não encontrado");
				}catch(CEPServiceFailureException se){
					log.error("Não houve exito em conectar-se com o serviço dos correios.");
				}catch(Exception e1){
					log.error("Erro ao buscar CEP.", e1);
				} 
				
				if(rua == null) {
					List<Endereco> enderecos = enderecoService.findByCep(txt.getText());
					if(enderecos != null && !enderecos.isEmpty()){
						Endereco end = enderecos.get(0);
						if(end.getRua() != null)
							selecionarRua(end.getRua());
					}
				}
			}
		});
		new Label(control, SWT.NONE);

		Label lblUf = new Label(control, SWT.NONE);
		lblUf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUf.setText("UF");

		cvUF = new ComboViewer(control, SWT.READ_ONLY);
		Combo combo = cvUF.getCombo();
		GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 41;
		combo.setLayoutData(gd_combo);
		cvUF.setContentProvider(ArrayContentProvider.getInstance());
		cvUF.addPostSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Estado estado = (Estado) SelectionHelper.getObject((IStructuredSelection) event.getSelection());

				if(estado != null){
					cvCidades.setInput(estado.getCidades());
					cvBairros.setInput(null);
					cvRuas.setInput(null);
					
					limparCidade();
				}
			}
		});

		Label label = new Label(control, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

		Label lblCidade = new Label(control, SWT.NONE);
		lblCidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCidade.setText("Cidade");

		cvCidades = new ComboViewer(control, SWT.READ_ONLY);
		Combo combo_1 = cvCidades.getCombo();
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cvCidades.addPostSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Cidade cidade = (Cidade) SelectionHelper.getObject((IStructuredSelection) event.getSelection());

				if(cidade != null){
					cvBairros.setInput(cidade.getBairros());
					cvRuas.setInput(null);
					
					limparBairro();
				}
			}
		});
		cvCidades.setContentProvider(ArrayContentProvider.getInstance());
		new Label(control, SWT.NONE);

		Label lblBairro = new Label(control, SWT.NONE);
		lblBairro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBairro.setText("Bairro");

		cvBairros = new ComboViewer(control, SWT.READ_ONLY);
		Combo combo_2 = cvBairros.getCombo();
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cvBairros.addPostSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Bairro bairro = (Bairro) SelectionHelper.getObject((IStructuredSelection) event.getSelection());
				
				if(bairro != null){
					cvRuas.setInput(bairro.getRuas());
					limparRua();
				}
			}
		});
		cvBairros.setContentProvider(ArrayContentProvider.getInstance());
		new Label(control, SWT.NONE);

		Label lblRua_1 = new Label(control, SWT.NONE);
		lblRua_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRua_1.setText("Rua");

		cvRuas = new ComboViewer(control, SWT.READ_ONLY);
		Combo combo_3 = cvRuas.getCombo();
		combo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cvRuas.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Rua) element).getNome();
			}
		});
		cvRuas.setContentProvider(ArrayContentProvider.getInstance());
		new Label(control, SWT.NONE);

		Label lblNmero = new Label(control, SWT.NONE);
		lblNmero.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNmero.setText("Número");

		txtNumero = new Text(control, SWT.BORDER);
		GridData gd_text_7 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_7.widthHint = 98;
		txtNumero.setLayoutData(gd_text_7);
		new Label(control, SWT.NONE);

		Label lblComplemento = new Label(control, SWT.NONE);
		lblComplemento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblComplemento.setText("Complemento");

		txtComplemento = new Text(control, SWT.BORDER);
		txtComplemento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(control, SWT.NONE);

		context = initDataBindings();
	}
	
	private void updateTargets(){
		context.updateTargets();
	}

	protected Rua cadastrarCep(CEP cep) {
		try{
			Estado estado = estadoService.findByUf(cep.getUf());
			if(estado == null){
				estado = new Estado();
				estado.setNome(cep.getUf());
				estado.setUf(cep.getUf());
				estadoService.salvar(estado);
			}
			
			Cidade cidade = cidadeService.findByNome(cep.getLocalidade());
			if(cidade == null){
				cidade = new Cidade();
				cidade.setNome(cep.getLocalidade());
				cidade.setEstado(estado);
				cidadeService.salvar(cidade);
			}
			
			Bairro bairro = bairroService.findByNome(cep.getBairro());
			if(bairro == null){
				bairro = new Bairro();
				bairro.setNome(cep.getBairro());
				bairro.setCidade(cidade);
				bairroService.salvar(bairro);
			}
			
			Rua rua = ruaService.findByNome(cep.getLogradouro());
			if(rua == null){
				rua = new Rua();
				rua.setNome(cep.getLogradouro());
				rua.setBairro(bairro);
				ruaService.salvar(rua);
			}
			
			return rua;
		}catch(Exception e){
			log.error("Erro ao cadastrar cep.\n" + e.getLocalizedMessage(), e);
			return null;
		}
	}

	public Endereco getEndereco() {
		return (Endereco) value.getValue();
	}

	public void setEndereco(Endereco endereco) {
		cvUF.setInput(estadoService.findAll());
		
		if(endereco.getRua() != null){
			Bairro bairro = endereco.getRua().getBairro();
			Cidade cidade = bairro.getCidade();
			Estado estado = cidade.getEstado();
			Rua rua = endereco.getRua();
			
			selecionarEstado(estado);
			selecionarCidade(cidade);
			selecionarBairro(bairro);
			selecionarRua(rua);
		} else {
			cvCidades.setInput(null);
			cvBairros.setInput(null);
			cvRuas.setInput(null);
		}
		
		value.setValue(endereco);
	}

	public Group getControl() {
		return control;
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTxtCepObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtCep);
		IObservableValue valueCepObserveDetailValue = PojoProperties.value(Endereco.class, "cep", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtCepObserveWidget, valueCepObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtComplementoObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtComplemento);
		IObservableValue valueComplementoObserveDetailValue = PojoProperties.value(Endereco.class, "complemento", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtComplementoObserveWidget, valueComplementoObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtNumeroObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtNumero);
		IObservableValue valueNumeroObserveDetailValue = PojoProperties.value(Endereco.class, "numero", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtNumeroObserveWidget, valueNumeroObserveDetailValue, null, null);
//		// NAO TESTEI SE REALMENTE FUNCIONA MAS ESSE POLICY ON REQUEST PODE SER MUDADO, TALVEZ PARA NEVER
//		// POIS PODE DAR PIPOCO AO TROCAR DE ENDERECOS OU CARREGAR A PAGINA, OU AINDA TROCAR A CIDADE DE UM BAIRRO QUE NAO DEVERIA, OU UM BAIRRO DE UMA RUA...
//		IObservableValue observeSingleSelectionCvUF = ViewerProperties.singleSelection().observe(cvUF);
//		IObservableValue valueRuabairrocidadeestadoObserveDetailValue = PojoProperties.value(Endereco.class, "rua.bairro.cidade.estado", Estado.class).observeDetail(value);
//		bindingContext.bindValue(observeSingleSelectionCvUF, valueRuabairrocidadeestadoObserveDetailValue, new UpdateValueStrategy(UpdateValueStrategy.POLICY_ON_REQUEST), null);
//		//
//		IObservableValue observeSingleSelectionCvCidades = ViewerProperties.singleSelection().observe(cvCidades);
//		IObservableValue valueRuabairrocidadeObserveDetailValue = PojoProperties.value(Endereco.class, "rua.bairro.cidade", Cidade.class).observeDetail(value);
//		bindingContext.bindValue(observeSingleSelectionCvCidades, valueRuabairrocidadeObserveDetailValue, new UpdateValueStrategy(UpdateValueStrategy.POLICY_ON_REQUEST), null);
//		//
//		IObservableValue observeSingleSelectionCvBairros = ViewerProperties.singleSelection().observe(cvBairros);
//		IObservableValue valueRuabairroObserveDetailValue = PojoProperties.value(Endereco.class, "rua.bairro", Bairro.class).observeDetail(value);
//		bindingContext.bindValue(observeSingleSelectionCvBairros, valueRuabairroObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionCvRuas = ViewerProperties.singleSelection().observe(cvRuas);
		IObservableValue valueRuaObserveDetailValue = PojoProperties.value(Endereco.class, "rua", Rua.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionCvRuas, valueRuaObserveDetailValue, null, null);
		//
		return bindingContext;
	}

	private void selecionarRua(Rua rua) {
		selecionarBairro(rua.getBairro());
		cvRuas.setInput(ruaService.findRuasByBairro(rua.getBairro()));

		if(getEndereco() == null)
			return ;
		
		getEndereco().setRua(rua);
		updateTargets();
	}

	private void selecionarBairro(Bairro bairro) {
		selecionarCidade(bairro.getCidade());
		cvBairros.setInput(bairroService.findByCidade(bairro.getCidade()));
		cvBairros.setSelection(new StructuredSelection(bairro));
	}

	private void selecionarCidade(Cidade cidade) {
		selecionarEstado(cidade.getEstado());
		cvCidades.setInput(cidadeService.findCidadesByEstado(cidade.getEstado()));
		cvCidades.setSelection(new StructuredSelection(cidade));
	}

	private void selecionarEstado(Estado estado) {
		cvUF.setInput(estadoService.findAll());
		cvUF.setSelection(new StructuredSelection(estado));
	}
	
	private void limparCidade(){
		if(getEndereco() != null){
			limparBairro();
			cvCidades.setSelection(null);
		}
	}
	
	private void limparBairro(){
		if(getEndereco() != null){
			limparRua();
			cvBairros.setSelection(null);
		}
	}
	
	private void limparRua(){
		if(getEndereco() != null)
			getEndereco().setRua(null);
	}
}
