package br.com.michelon.softimob.tela.widget;

public class ColumnProperties {

	private String label;
	
	private String atributo;
	
	private Integer tamanho;

	public ColumnProperties(String label, String atributo, Integer tamanho) {
		this.label = label;
		this.atributo = atributo;
		this.tamanho = tamanho;
	}

	public ColumnProperties(String label, String atributo) {
		this.label = label;
		this.atributo = atributo;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}
	
}
