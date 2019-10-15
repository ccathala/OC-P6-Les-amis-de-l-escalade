package com.charles.lesamisdelescalade.model.beans;

import java.sql.Timestamp;
import javax.validation.constraints.Size;

public class Commentaire {

	private int id;
	@Size(max=500)
	private String texte;
	private Timestamp date;
	private int status_id;
	private int utilisateur_id;
	private int site_id;
	
	public Commentaire() {
		super();
		
	}

	public Commentaire(int id, String texte, Timestamp date, int status_id, int utilisateur_id, int site_id) {
		super();
		this.id = id;
		this.texte = texte;
		this.date = date;
		this.status_id = status_id;
		this.utilisateur_id = utilisateur_id;
		this.site_id = site_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getUtilisateur_id() {
		return utilisateur_id;
	}

	public void setUtilisateur_id(int utilisateur_id) {
		this.utilisateur_id = utilisateur_id;
	}

	public int getSite_id() {
		return site_id;
	}

	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}

	@Override
	public String toString() {
		return "Commentaire [id=" + id + ", texte=" + texte + ", date=" + date + ", status_id=" + status_id
				+ ", utilisateur_id=" + utilisateur_id + ", site_id=" + site_id + "]";
	}

	
	
	
}
	