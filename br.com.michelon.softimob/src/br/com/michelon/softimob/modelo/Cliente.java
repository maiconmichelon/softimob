package br.com.michelon.softimob.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Cliente extends Comissionado implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return getNome();
	}
	
}
