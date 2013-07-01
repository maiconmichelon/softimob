package br.com.michelon.softimob.aplicacao.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

}
