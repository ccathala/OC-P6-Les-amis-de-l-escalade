package com.charles.lesamisdelescalade.model.beans;

public class PossesseurTopo {
	
	private int topo_id;
	private int utilisateur_id;
	private Boolean disponible;
	private Boolean shared;
	
	public PossesseurTopo() {
		super();
		
	}

	public PossesseurTopo(int topo_id, int utilisateur_id, Boolean disponible, Boolean shared) {
		super();
		this.topo_id = topo_id;
		this.utilisateur_id = utilisateur_id;
		this.disponible = disponible;
		this.shared = shared;
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

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public Boolean getShared() {
		return shared;
	}

	public void setShared(Boolean shared) {
		this.shared = shared;
	}

	@Override
	public String toString() {
		return "PossesseurTopo [topo_id=" + topo_id + ", utilisateur_id=" + utilisateur_id + ", disponible="
				+ disponible + ", shared=" + shared + "]";
	}

	
	
	

	
}
