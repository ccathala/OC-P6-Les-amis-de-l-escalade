package com.charles.lesamisdelescalade.model.beans;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Topo {

	private int id;
	@Size(min=3, message="doit contenir au minimum 3 caractères")
	@NotEmpty
	private String nom;
	@Size(min=30, max=250, message="doit contenir entre 30 et 250 caractères")
	@NotEmpty
	private String description;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull(message="ne peut pas être vide")
	@PastOrPresent
	private Date date_parution;
	@Min(value=1)
	private int site_id;
	
	public Topo() {
		super();
	}

	public Topo(int id, String nom, String description, Date annee_parution, int site_id) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.date_parution = annee_parution;
		this.site_id = site_id;
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

	public Date getDate_parution() {
		return date_parution;
	}

	public void setDate_parution(Date annee_parution) {
		this.date_parution = annee_parution;
	}

	public int getSite_id() {
		return site_id;
	}

	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}

	@Override
	public String toString() {
		return "Topo [id=" + id + ", nom=" + nom + ", description=" + description + ", date_parution=" + date_parution
				+ ", site_id=" + site_id + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
