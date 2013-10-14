package br.com.michelon.softimob.tela.widget;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryGroupRenderer;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TreeAdapter;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.FileHelper;
import br.com.michelon.softimob.aplicacao.service.ArquivoService;
import br.com.michelon.softimob.modelo.Arquivo;
import br.com.michelon.softimob.modelo.ContainsPhotos;

import com.google.common.collect.Lists;

public class PhotoComposite extends Composite {

	private Gallery gallery;
	private GalleryItem giFotosImovel;
	private ArquivoService arquivoService = new ArquivoService();
	private Logger log = Logger.getLogger(getClass());
	private File tempFolder;
	private IObservableValue value;
	
	public PhotoComposite(Composite parent, int style, IObservableValue value) {
		super(parent, style);

		value.addValueChangeListener(new IValueChangeListener() {
			@Override
			public void handleValueChange(ValueChangeEvent event) {
				carregar();
			}
		});
		
		this.value = value;
		
		createComponents();
	}

	private void createComponents() {
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 10;
		setLayout(gridLayout);
		
		gallery = new Gallery(this, SWT.V_SCROLL | SWT.MULTI | SWT.BORDER);
		gallery.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		
		final DefaultGalleryGroupRenderer gr = new DefaultGalleryGroupRenderer();
		
		gr.setAnimation(true);
		gr.setMinMargin(2);
		gr.setItemHeight(156);
		gr.setItemWidth(172);
		gr.setMaxImageHeight(64);
		gr.setMaxImageWidth(72);
		gr.setAutoMargin(true);
		
		gallery.setGroupRenderer(gr);
		gallery.setLowQualityOnUserAction(true);
		gallery.setHigherQualityDelay(500);
		gallery.setItemRenderer(new DefaultGalleryItemRenderer());
		gallery.addTreeListener(new TreeAdapter() {
			
			@Override
			public void treeExpanded(TreeEvent e) {
				if(e.item.equals(giFotosImovel)){
					carregar();
				}
			}
			
		});
		
		gallery.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				Gallery gal = (Gallery) e.widget;

				if (gal.getSelectionCount() < 1)
					return;

				try {
					FileHelper.openFile(tempFolder, gal.getSelection()[0].getText());
				} catch (IOException e1) {
					log.error("Erro ao abrir as fotos.", e1);
					DialogHelper.openErrorMultiStatus("Erro ao abrir as fotos do imóvel.\n" +
							"Verifique se seu computador possui um visualizador configurado para arquivos JPG.", e1.getMessage());
				}
			}
		});
		
		createGalleryImovel();
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 1, 1));
		btnNewButton_1.setImage(ImageRepository.ADD_16.getImage());
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				giFotosImovel.setExpanded(true);
				Event event = new Event();
				event.item = giFotosImovel;
				gallery.notifyListeners(SWT.Expand, event);
				
				List<Arquivo> photos = DialogHelper.openPhotoDialog();
				
				if(photos == null || photos.size() < 1)
					return;
				
				for(Arquivo photo : photos){
					int i = 1;
					String nomeCompleto = photo.getNome();
					int indexOf = nomeCompleto.indexOf(".");
					String nome = nomeCompleto.substring(0, indexOf);
					String ext = nomeCompleto.substring(indexOf, nomeCompleto.length());
					
					if(getFotos().contains(photo)){
						do{
							photo.setNome(String.format("%s (%s)%s", nome, i, ext));
							i++;
						}while(getFotos().contains(photo));
					}
					
					getFotos().add(photo);
					FileHelper.insertIntTempFolder(tempFolder, photo);
					
					if(giFotosImovel.isExpanded() || !isEmpty())
						addPhotoToViewComposite(photo);
				}
			}
		});
		
		Button btnRemover = new Button(this, SWT.NONE);
		btnRemover.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		btnRemover.setImage(ImageRepository.REMOVE_16.getImage());
		btnRemover.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GalleryItem[] selection = gallery.getSelection();
				List<Arquivo> toRemove = Lists.newArrayList();
				
				for(Arquivo arq : getFotos()){
					for(GalleryItem gal : selection){
						if(arq.getNome().equals(gal.getText())){
							toRemove.add(arq);
							removeTempFolder(tempFolder, arq);
							if(gallery.indexOf(gal) >= 0){
								gallery.remove(gal);
							}
						}
					}
				}
				getFotos().removeAll(toRemove);
			}
		});
	}

	public void removeTempFolder(File createTempDir, Arquivo arq) {
        File file = new File(createTempDir.getAbsoluteFile() + "/" + arq.getNome());
 
        try {
		    file.delete();
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	private void createGalleryImovel() {
		giFotosImovel = new GalleryItem(gallery, SWT.NONE);
		giFotosImovel.setText("Fotos");
		giFotosImovel.setItemCount(getNumeroFotos());
	}
	
	private void addPhotoToViewComposite(Arquivo photo) {
		try {
			GalleryItem gallerItem = new GalleryItem(giFotosImovel, SWT.NONE);
			gallerItem.setText(photo.getNome());
			gallerItem.setImage(arquivoService.getImage(photo.getArquivo().getArquivo()));
			
			gallery.setSelection(new GalleryItem[]{gallerItem});
		} catch (IOException e1) {
			log.error("Erro ao criar imagem.", e1);
		}
	}

	private boolean isEmpty(){
		return giFotosImovel.getItems().length == 0 || giFotosImovel.getItem(0) == null;
	}
	
	@Override
	protected void checkSubclass() {
	}
	
	private List<Arquivo> getFotos(){
		ContainsPhotos c = (ContainsPhotos) value.getValue();
		return c.getFotos();
	}
	
	private Integer getNumeroFotos(){
		ContainsPhotos c = (ContainsPhotos) value.getValue();
		if(c == null)
			return 0;
		
		Integer numeroFotos = c.getNumeroFotos();
		return numeroFotos == null ? 0 : numeroFotos;
	}
	
	private void carregar() {
		giFotosImovel.clearAll();
		giFotosImovel.setText("Fotos");
		giFotosImovel.setItemCount(0);
		
		if(isEmpty() && giFotosImovel.isExpanded()){
			tempFolder = FileHelper.criarDiretorioArquivos(getFotos());
			
			for(Arquivo arq : getFotos()){
				FileHelper.insertIntTempFolder(tempFolder, arq);
				addPhotoToViewComposite(arq);
			}
		}
		
		if(!giFotosImovel.isExpanded()){
			giFotosImovel.setItemCount(getNumeroFotos());
		}
	}
}
