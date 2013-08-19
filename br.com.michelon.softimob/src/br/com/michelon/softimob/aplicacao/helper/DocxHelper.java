package br.com.michelon.softimob.aplicacao.helper;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Text;

import br.com.michelon.softimob.aplicacao.utils.ETagDocx;
import br.com.michelon.softimob.aplicacao.utils.PTagDocx;
import br.com.michelon.softimob.aplicacao.utils.TagDocx;

import com.google.common.collect.Lists;

public class DocxHelper {

	private final String XPATH_TO_SELECT_TEXT_NODES = "//w:t";
	private Logger log = Logger.getLogger(getClass());
	
	public void createPartControl(File file, Object object) {

		try {
			// Abre o arquivo
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(file);

			// Pega todas as linhas do arquivo e joga em uma lista
			List<Object> texts = wordMLPackage.getMainDocumentPart().getJAXBNodesViaXPath(XPATH_TO_SELECT_TEXT_NODES, true);

			organizarTexts(texts);
			
			List<TagDocx> tags = Arrays.asList(new PTagDocx(), new ETagDocx());
			substituir(tags, texts, object);

			wordMLPackage.save(file);
		} catch (Exception e) {
			log.error("Erro ao alterar documento .docx .", e);
		}
	}

	private void organizarTexts(List<Object> texts) {
		for(int i = 0; i < texts.size(); i++){
			Text text = (Text) ((JAXBElement<?>) texts.get(i)).getValue();
			String var = text.getValue();
			
			if(var.lastIndexOf("<") == var.length() -1){
				int f = i;
				String txt = "";
				while(txt.isEmpty() && f < texts.size() - 1){
					f++;
					txt = ((Text) ((JAXBElement<?>) texts.get(f)).getValue()).getValue();
				}
				
				Text textI = (Text) ((JAXBElement<?>) texts.get(i)).getValue();
				Text textF = (Text) ((JAXBElement<?>) texts.get(f)).getValue();
				
				String stringI = textI.getValue();
				textI.setValue(stringI.substring(0, stringI.length()-1));
				
				String stringF = textF.getValue();
				textF.setValue("<".concat(stringF));
			}
		}
	}

	private void substituir(List<TagDocx> tagType, List<Object> texts, Object object){
		
		for (TagDocx tag : tagType) {
			
			for(int i = 0; i < texts.size(); i++){
				
				Text text = (Text) ((JAXBElement<?>) texts.get(i)).getValue();
				String var = text.getValue();
				List<Text> cleanList = Lists.newArrayList();
				
				if(var.contains("<" + tag.getTagWord())){
					String beforeTag = var.substring(0, var.indexOf("<" + tag.getTagWord()));
					String coringa = StringUtils.EMPTY;
					
					do{
						cleanList.add(text);
						coringa = coringa.concat(text.getValue());
						
						i++;
						if(texts.size() != i)
							text = (Text) ((JAXBElement<?>) texts.get(i)).getValue();
						
					}while(!coringa.contains("</"+tag.getTagWord()+">"));
					i--;
					
					String afterTag = coringa.substring(coringa.indexOf("</"+tag.getTagWord()+">") + 3, coringa.length()-1);
					
					coringa = coringa.substring(coringa.indexOf("<"+tag.getTagWord()+">") + 3, coringa.indexOf("</"+tag.getTagWord()+">"));
					coringa = coringa.trim();
					
					for(Text cText : cleanList){
						cText.setValue("");
					}
					
					cleanList.get(0).setValue(beforeTag.concat(tag.format(coringa, object)).concat(afterTag));
				}
			}
		}
	}
	
}
