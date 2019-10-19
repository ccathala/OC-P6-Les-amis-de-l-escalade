package com.charles.lesamisdelescalade.model.beans;

/**
 * Bean ReservationTopo
 * 
 * @author Charles
 *
 */
public class ReservationTopo {

	private int reservation_topo_id;
	private int possesseur_id;
	private int demandeur_id;
	private int status_id;

	public ReservationTopo() {
		super();

	}

	public ReservationTopo(int reservation_topo_id, int possesseur_id, int demandeur_id, int status_id) {
		super();
		this.reservation_topo_id = reservation_topo_id;
		this.possesseur_id = possesseur_id;
		this.demandeur_id = demandeur_id;
		this.status_id = status_id;
	}

	public int getReservation_topo_id() {
		return reservation_topo_id;
	}

	public void setReservation_topo_id(int reservation_topo_id) {
		this.reservation_topo_id = reservation_topo_id;
	}

	public int getPossesseur_id() {
		return possesseur_id;
	}

	public void setPossesseur_id(int possesseur_id) {
		this.possesseur_id = possesseur_id;
	}

	public int getDemandeur_id() {
		return demandeur_id;
	}

	public void setDemandeur_id(int demandeur_id) {
		this.demandeur_id = demandeur_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	@Override
	public String toString() {
		return "ReservationTopo [reservation_topo_id=" + reservation_topo_id + ", possesseur_id=" + possesseur_id
				+ ", demandeur_id=" + demandeur_id + ", status_id=" + status_id + "]";
	}

	
	
	
}
