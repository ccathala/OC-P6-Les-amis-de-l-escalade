package com.charles.lesamisdelescalade.model.beans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Bean Site
 * 
 * @author Charles
 *
 */
public class Site {
	
	private int id;
	
	@NotEmpty(message="saisir un nom de site")
	@Size(min=3, message="doit contenir au minimum 3 caractères")
	private String nom;
	
	@NotEmpty(message="saisir une description de site")
	@Size(min=30, message="doit contenir au minimum 30 caractères")
	private String description;
	
	@NotNull(message="vous devez sélectionner un département")
	private int departement_id;
	private int tag_id;
	private String picture;
	
	
	public Site() {
		super();
		
	}


	public Site(int id, String nom, String description, int departement_id, int tag_id, String picture) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.departement_id = departement_id;
		this.tag_id = tag_id;
		this.picture = picture;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getDepartement_id() {
		return departement_id;
	}


	public void setDepartement_id(int departement_id) {
		this.departement_id = departement_id;
	}


	public int getTag_id() {
		return tag_id;
	}


	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	@Override
	public String toString() {
		return "Site [id=" + id + ", nom=" + nom + ", description=" + description + ", departement_id=" + departement_id
				+ ", tag_id=" + tag_id + ", picture=" + picture + "]";
	}
	
	
		
	
	
	
	
	

}
