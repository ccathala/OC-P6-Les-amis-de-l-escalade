package com.charles.lesamisdelescalade.model.dto;

import java.util.Date;

public class MyTopo {
	
	private int topo_id;
	private int utilisateur_id;
	private String topo_nom;
	private String site;
	private String departement;
	private Date date_parution;
	private String dateParution;
	private Boolean disponible;
	
	public MyTopo() {
		super();
		
	}

	public MyTopo(int topo_id, int utilisateur_id, String topo_nom, String site, String departement, Date date_parution,
			String dateParution, Boolean disponible) {
		super();
		this.topo_id = topo_id;
		this.utilisateur_id = utilisateur_id;
		this.topo_nom = topo_nom;
		this.site = site;
		this.departement = departement;
		this.date_parution = date_parution;
		this.dateParution = dateParution;
		this.disponible = disponible;
	}

	public int getTopo_id() {
		return topo_id;
	}

	public void setTopo_id(int topo_id) {
		this.topo_id = topo_id;
	}

	public int getUtilisateur_id() {
		return utilisateur_id;
	}

	public void setUtilisateur_id(int utilisateur_id) {
		this.utilisateur_id = utilisateur_id;
	}

	public String getTopo_nom() {
		return topo_nom;
	}

	public void setTopo_nom(String topo_nom) {
		this.topo_nom = topo_nom;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
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
		return "MyTopo [topo_id=" + topo_id + ", utilisateur_id=" + utilisateur_id + ", topo_nom=" + topo_nom
				+ ", site=" + site + ", departement=" + departement + ", date_parution=" + date_parution
				+ ", dateParution=" + dateParution + ", disponible=" + disponible + "]";
	}

	

	
	
	
	
	

}
