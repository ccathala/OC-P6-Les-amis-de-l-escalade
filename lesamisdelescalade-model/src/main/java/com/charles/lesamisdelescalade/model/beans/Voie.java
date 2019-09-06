package com.charles.lesamisdelescalade.model.beans;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Voie {
	
	private int id;
	@Min(value = 1)
	@Max(value = 99)
	@NotEmpty
	private int numero;
	
	@Size(min=3)
	@NotEmpty
	private String nom;
	
	private int secteur_id;
	
	public Voie() {
		super();
		// TODO Auto-generated constructor stub
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
