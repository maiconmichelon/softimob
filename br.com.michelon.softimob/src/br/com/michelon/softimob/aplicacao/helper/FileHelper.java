package br.com.michelon.softimob.aplicacao.helper;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.google.common.io.Files;

import br.com.michelon.softimob.modelo.Arquivo;

public class FileHelper {

	public static byte[] getBytes(File file) {
		int len = (int) file.length();
		byte[] sendBuf = new byte[len];
		FileInputStream inFile = null;
		try {
			inFile = new FileInputStream(file);
			inFile.read(sendBuf, 0, len);

		} catch (FileNotFoundException fnfex) {
		} catch (IOException ioex) {}
		
		return sendBuf;
	}

	public static File criarDiretorioArquivos(List<Arquivo> arquivos){
		File tempFolder = Files.createTempDir();
		for(Arquivo arq : arquivos){
			insertIntTempFolder(tempFolder, arq);
		}
		return tempFolder;
	}
	
	public static void insertIntTempFolder(File createTempDir, Arquivo arq) {
        File file = new File(createTempDir.getAbsoluteFile() + "/" + arq.getNome());
 
        try {
		    FileOutputStream fileOuputStream = new FileOutputStream(file); 
		    fileOuputStream.write(arq.getArquivo().getArquivo());
		    fileOuputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	public static void openFile(File tempFolder, String arq) throws IOException{
		Desktop.getDesktop().open(new File(tempFolder.getAbsoluteFile() + "/" + arq));
	}
	
}
