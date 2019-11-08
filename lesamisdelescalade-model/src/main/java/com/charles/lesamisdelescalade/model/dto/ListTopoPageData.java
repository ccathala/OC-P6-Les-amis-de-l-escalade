package com.charles.lesamisdelescalade.model.dto;

import java.util.Date;

/**
 * DTO ListTopoPage, contains all data needed to display topo list on topo list page
 * 
 * @author Charles
 *
 */
public class ListTopoPageData {

	private String code_postal;
	private String departement;
	private String topo_nom;
	private int topo_id;
	private String possesseur_nom;
	private int possesseur_id;	
	private String site;
	private String description;
	private Date date_parution;
	private String dateParution;
	private Boolean disponible;
	
	public ListTopoPageData() {
		super();
		
	}

	public ListTopoPageData(String code_postal, String departement, String topo_nom, int topo_id, String possesseur_nom,
			int possesseur_id, String site, String description, Date date_parution, String dateParution,
			Boolean disponible) {
		super();
		this.code_postal = code_postal;
		this.departement = departement;
		this.topo_nom = topo_nom;
		this.topo_id = topo_id;
		this.possesseur_nom = possesseur_nom;
		this.possesseur_id = possesseur_id;
		this.site = site;
		this.description = description;
		this.date_parution = date_parution;
		this.dateParution = dateParution;
		this.disponible = disponible;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getTopo_nom() {
		return topo_nom;
	}

	public void setTopo_nom(String topo_nom) {
		this.topo_nom = topo_nom;
	}

	public int getTopo_id() {
		return topo_id;
	}

	public void setTopo_id(int topo_id) {
		this.topo_id = topo_id;
	}

	public String getPossesseur_nom() {
		return possesseur_nom;
	}

	public void setPossesseur_nom(String possesseur_nom) {
		this.possesseur_nom = possesseur_nom;
	}

	public int getPossesseur_id() {
		return possesseur_id;
	}

	public void setPossesseur_id(int possesseur_id) {
		this.possesseur_id = possesseur_id;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
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

	public void setDate_parution(Date date_parution) {
		this.date_parution = date_parution;
	}

	public String getDateParution() {
		return dateParution;
	}

	public void setDateParution(String dateParution) {
		this.dateParution = dateParution;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	@Override
	public String toString() {
		return "ListTopoPageData [code_postal=" + code_postal + ", departement=" + departement + ", topo_nom="
				+ topo_nom + ", topo_id=" + topo_id + ", possesseur_nom=" + possesseur_nom + ", possesseur_id="
				+ possesseur_id + ", site=" + site + ", description=" + description + ", date_parution=" + date_parution
				+ ", dateParution=" + dateParution + ", disponible=" + disponible + "]";
	}

	

	
	
	
	
	
	
	
	
	
}
