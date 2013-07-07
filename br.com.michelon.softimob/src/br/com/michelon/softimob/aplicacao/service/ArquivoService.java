package br.com.michelon.softimob.aplicacao.service;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

import br.com.michelon.softimob.modelo.Arquivo;
import br.com.michelon.softimob.persistencia.ArquivoDAO;

public class ArquivoService extends GenericService<Arquivo>{

	public ArquivoService() {
		super(ArquivoDAO.class);
	}

	public String[] getNomes(List<Arquivo> arquivos){
		String[] nomes = new String[arquivos.size()];
		for(int i = 0 ; i < nomes.length ; i++){
			nomes[i] = arquivos.get(i).getNome();
		}
		return nomes;
	}
	
	public Image getImage(byte[] arquivo) throws IOException{
		ByteArrayInputStream in = new ByteArrayInputStream(arquivo);
		DataInputStream readIn = new DataInputStream(in);

		ImageData imdata = new ImageData(readIn);
		
		readIn.close();

		return new Image(Display.getCurrent(), imdata);
	}
	
}
