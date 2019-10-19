package com.charles.lesamisdelescalade.model.beans;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Bean Voie
 * 
 * @author Charles
 *
 */
public class Voie {
	
	private int id;
	@Min(value = 1, message="saisir un numero de voie supérieur à 0")
	@Max(value = 99, message="saisir un numero de voie inférieur à 99")
	private int numero;
	
	@Size(min=3, message="doit contenir au minimum 3 caractères")
	@NotEmpty
	private String nom;
	
	private int secteur_id;
	
	public Voie() {
		super();
	}

	public Voie(int id, int numero, String nom, int secteur_id) {
		super();
		this.id = id;
		this.numero = numero;
		this.nom = nom;
		this.secteur_id = secteur_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getSecteur_id() {
		return secteur_id;
	}

	public void setSecteur_id(int secteur_id) {
		this.secteur_id = secteur_id;
	}

	@Override
	public String toString() {
		return "Voie [id=" + id + ", numero=" + numero + ", nom=" + nom + ", secteur_id=" + secteur_id + "]";
	}
	
	
	
	
	
	
	

}
