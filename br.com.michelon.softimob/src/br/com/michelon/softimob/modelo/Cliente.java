package br.com.michelon.softimob.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@Entity
@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public class Cliente extends Comissionado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return getNome();
	}
	
}
