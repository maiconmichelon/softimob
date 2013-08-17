package br.com.michelon.softimob.aplicacao.helper;

import org.apache.commons.lang3.StringUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Text;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBElement;

public class DocxHelper {

	private final String XPATH_TO_SELECT_TEXT_NODES = "//w:t";

	public void createPartControl(File file, Object object) {

		try {
			// Abre o arquivo
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(file);

			// Pega todas as linhas do arquivo e joga em uma lista
			List<Object> texts = wordMLPackage.getMainDocumentPart().getJAXBNodesViaXPath(XPATH_TO_SELECT_TEXT_NODES, true);

			for(int i = 0; i < texts.size(); i++){
				Text text = (Text) ((JAXBElement<?>) texts.get(i)).getValue();
				String var = text.getValue();
				List<Text> cleanList = Lists.newArrayList();
				
				if(var.contains("<p")){
					String beforeTag = var.substring(0, var.indexOf("<p"));
					String coringa = StringUtils.EMPTY;
					
					do{
						cleanList.add(text);
						coringa = coringa.concat(text.getValue());
						
						i++;
						if(texts.size() != i)
							text = (Text) ((JAXBElement<?>) texts.get(i)).getValue();
						
					}while(!coringa.contains("</p>"));
					i--;
					
					String afterTag = coringa.substring(coringa.indexOf("</p>") + 3, coringa.length()-1);
					
					coringa = coringa.substring(coringa.indexOf("<p>") + 3, coringa.indexOf("</p>"));
					coringa = coringa.trim();
					
					for(Text cText : cleanList){
						cText.setValue("");
					}
					
					try{
						Object atribute = ReflectionHelper.getAtribute(object, coringa);
						String stringFormatada = FormatterHelper.formatObject(atribute);
						cleanList.get(0).setValue(beforeTag.concat(stringFormatada).concat(afterTag));
					}catch(NoSuchMethodException ne){
						cleanList.get(0).setValue("PARAMETRO NÃO ENCONTRADO");
					}
				}
				
			}

			wordMLPackage.save(file);
		} catch (Docx4JException e) {
		} catch (Exception e) {
		}

	}

}
