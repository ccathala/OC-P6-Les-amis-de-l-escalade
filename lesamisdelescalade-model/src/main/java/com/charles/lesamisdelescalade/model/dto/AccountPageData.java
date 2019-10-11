package com.charles.lesamisdelescalade.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

public class AccountPageData {
	
	private int topo_id;
	private String topo_nom;
	private String site_nom;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull(message="ne peut pas être vide")
	@PastOrPresent
	private Date date_parution;
	private String dateParution;
	
	public AccountPageData() {
		super();
		
	}

	public AccountPageData(int topo_id, String topo_nom, String site_nom, Date date_parution, String dateParution) {
		super();
		this.topo_id = topo_id;
		this.topo_nom = topo_nom;
		this.site_nom = site_nom;
		this.date_parution = date_parution;
		this.dateParution = dateParution;
	}

	public int getTopo_id() {
		return topo_id;
	}

	public void setTopo_id(int topo_id) {
		this.topo_id = topo_id;
	}

	public String getTopo_nom() {
		return topo_nom;
	}

	public void setTopo_nom(String topo_nom) {
		this.topo_nom = topo_nom;
	}

	public String getSite_nom() {
		return site_nom;
	}

	public void setSite_nom(String site_nom) {
		this.site_nom = site_nom;
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

	@Override
	public String toString() {
		return "AccountPageData [topo_id=" + topo_id + ", topo_nom=" + topo_nom + ", site_nom=" + site_nom
				+ ", date_parution=" + date_parution + ", dateParution=" + dateParution + "]";
	}

	
	
	
}
