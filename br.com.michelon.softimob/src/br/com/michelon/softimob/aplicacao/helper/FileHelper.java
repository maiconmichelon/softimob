package br.com.michelon.softimob.aplicacao.helper;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.io.Files;

import br.com.michelon.softimob.modelo.Arquivo;

public class FileHelper {

	private static Logger log = Logger.getLogger(FileHelper.class);
	
	public static byte[] getBytes(File file) {
		int len = (int) file.length();
		byte[] sendBuf = new byte[len];
		FileInputStream inFile = null;
		try {
			inFile = new FileInputStream(file);
			inFile.read(sendBuf, 0, len);

			inFile.close();
		} catch (FileNotFoundException fnfex) {
			log.error("Erro ao pegar bytes do arquivo.", fnfex);
		} catch (IOException ioex) {
			log.error("Erro ao pegar bytes do arquivo.", ioex);
		}
			
		return sendBuf;
	}
	
	public static File criarDiretorioArquivos(List<Arquivo> arquivos){
		File tempFolder = criarDiretorioArquivos();
		for(Arquivo arq : arquivos){
			insertIntTempFolder(tempFolder, arq);
		}
		return tempFolder;
	}
	
	public static File criarDiretorioArquivos(){
		return Files.createTempDir();
	}
	
	
	public static void insertIntTempFolder(File createTempDir, Arquivo arq) {
		insertIntTempFolder(createTempDir, arq.getNome(), arq.getArquivo().getArquivo());
	}
	
	public static void insertIntTempFolder(File createTempDir, String nome, byte[] arquivo) {
        File file = new File(createTempDir.getAbsoluteFile() + "/" + nome);
 
        try {
		    FileOutputStream fileOuputStream = new FileOutputStream(file); 
		    fileOuputStream.write(arquivo);
		    fileOuputStream.close();
        }catch(Exception e){
        	log.error("Erro ao inserir escrever em um diretório.");
        }
	}
	
	public static void openFile(File tempFolder, String arq) throws IOException{
		Desktop.getDesktop().open(new File(tempFolder.getAbsoluteFile() + "/" + arq));
	}

}
