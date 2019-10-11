package com.charles.lesamisdelescalade.model.dto;

import java.util.Date;

public class ReservationRequest {
	
	private int reservation_id;
	private int reservation_topo_id;
	private String reservation_topo_nom;
	private int possesseur_id;
	private String possesseur_nom;
	private int demandeur_id;
	private String demandeur_nom;
	private String site_nom;
	private int status_id;
	private String status;
	private String departement;
	private Date date_parution;
	private String dateParution;
	
	public ReservationRequest() {
		super();
		
	}

	public ReservationRequest(int reservation_id, int reservation_topo_id, String reservation_topo_nom,
			int possesseur_id, String possesseur_nom, int demandeur_id, String demandeur_nom, String site_nom,
			int status_id, String status, String departement, Date date_parution, String dateParution) {
		super();
		this.reservation_id = reservation_id;
		this.reservation_topo_id = reservation_topo_id;
		this.reservation_topo_nom = reservation_topo_nom;
		this.possesseur_id = possesseur_id;
		this.possesseur_nom = possesseur_nom;
		this.demandeur_id = demandeur_id;
		this.demandeur_nom = demandeur_nom;
		this.site_nom = site_nom;
		this.status_id = status_id;
		this.status = status;
		this.departement = departement;
		this.date_parution = date_parution;
		this.dateParution = dateParution;
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public int getReservation_topo_id() {
		return reservation_topo_id;
	}

	public void setReservation_topo_id(int reservation_topo_id) {
		this.reservation_topo_id = reservation_topo_id;
	}

	public String getReservation_topo_nom() {
		return reservation_topo_nom;
	}

	public void setReservation_topo_nom(String reservation_topo_nom) {
		this.reservation_topo_nom = reservation_topo_nom;
	}

	public int getPossesseur_id() {
		return possesseur_id;
	}

	public void setPossesseur_id(int possesseur_id) {
		this.possesseur_id = possesseur_id;
	}

	public String getPossesseur_nom() {
		return possesseur_nom;
	}

	public void setPossesseur_nom(String possesseur_nom) {
		this.possesseur_nom = possesseur_nom;
	}

	public int getDemandeur_id() {
		return demandeur_id;
	}

	public void setDemandeur_id(int demandeur_id) {
		this.demandeur_id = demandeur_id;
	}

	public String getDemandeur_nom() {
		return demandeur_nom;
	}

	public void setDemandeur_nom(String demandeur_nom) {
		this.demandeur_nom = demandeur_nom;
	}

	public String getSite_nom() {
		return site_nom;
	}

	public void setSite_nom(String site_nom) {
		this.site_nom = site_nom;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "ReservationRequest [reservation_id=" + reservation_id + ", reservation_topo_id=" + reservation_topo_id
				+ ", reservation_topo_nom=" + reservation_topo_nom + ", possesseur_id=" + possesseur_id
				+ ", possesseur_nom=" + possesseur_nom + ", demandeur_id=" + demandeur_id + ", demandeur_nom="
				+ demandeur_nom + ", site_nom=" + site_nom + ", status_id=" + status_id + ", status=" + status
				+ ", departement=" + departement + ", date_parution=" + date_parution + ", dateParution=" + dateParution
				+ "]";
	}

	

}
