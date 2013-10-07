package br.com.michelon.softimob.aplicacao.helper;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang3.StringUtils;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Text;

import br.com.michelon.softimob.aplicacao.utils.tag.ETagDocx;
import br.com.michelon.softimob.aplicacao.utils.tag.PTagDocx;
import br.com.michelon.softimob.aplicacao.utils.tag.TagDocx;

import com.google.common.collect.Lists;

public class DocxHelper {

	private final String XPATH_TO_SELECT_TEXT_NODES = "//w:t";
	
	public void gerarContrato(File file, Object object) throws Exception{
		// Abre o arquivo
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(file);

		// Pega todas as linhas do arquivo e joga em uma lista
		List<Object> texts = wordMLPackage.getMainDocumentPart().getJAXBNodesViaXPath(XPATH_TO_SELECT_TEXT_NODES, true);

		organizarTexts(texts);
		
		List<TagDocx> tags = Arrays.asList(new PTagDocx(), new ETagDocx());
		substituir(tags, texts, object);

		wordMLPackage.save(file);
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

	private void substituir(List<TagDocx> tagType, List<Object> texts, Object object) throws Exception{
		
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
						// Se tiver </ ex: "<a>nome<". Se </ tiver um tipo invalido a tag que foi aberta ex: "<a></b>" e se não for o ultimo caractere ex: "<a>nome</"
						if(coringa.contains("</") && !coringa.contains("</" + tag.getTagWord()) && coringa.lastIndexOf("</") != coringa.length() - 2)
							throw new Exception("As tags do contrato não fecham corretamente.");
						
					}while(!coringa.contains("</"+tag.getTagWord()+">"));
					i--;
					
					String afterTag = coringa.substring(coringa.indexOf("</"+tag.getTagWord()+">") + 4, coringa.length());
					
					coringa = coringa.substring(coringa.indexOf("<"+tag.getTagWord()+">") + 3, coringa.indexOf("</"+tag.getTagWord()+">"));
					coringa = coringa.trim();
					coringa = FormatterHelper.removerAcentos(coringa);
					
					for(Text cText : cleanList){
						cText.setValue("");
					}
					
					String txtFormat;
					try {
						txtFormat = tag.format(coringa, object);
					} catch (Exception e) {
						txtFormat = "Parâmetro não encontrado";
					}
					
					if(txtFormat == null)
						txtFormat = StringUtils.EMPTY;
					
					cleanList.get(0).setValue(beforeTag.concat(txtFormat).concat(afterTag));
					i = 0;
				}
			}
		}
	}
	
}
