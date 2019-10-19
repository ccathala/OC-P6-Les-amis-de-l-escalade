package com.charles.lesamisdelescalade.model.dto;

/**
 * DTO SearchSiteData, contains all data needed to display search site page
 * 
 * @author Charles
 *
 */
public class SearchSiteData {

	private String nom;
	private int departementId;
	private int cotationId;
	private int secteurCount;
	
	public SearchSiteData() {
		super();
	}

	public SearchSiteData(String nom, int departementId, int cotationId, int secteurCount) {
		super();
		this.nom = nom;
		this.departementId = departementId;
		this.cotationId = cotationId;
		this.secteurCount = secteurCount;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getDepartementId() {
		return departementId;
	}

	public void setDepartementId(int departementId) {
		this.departementId = departementId;
	}

	public int getCotationId() {
		return cotationId;
	}

	public void setCotationId(int cotationId) {
		this.cotationId = cotationId;
	}

	public int getSecteurCount() {
		return secteurCount;
	}

	public void setSecteurCount(int secteurCount) {
		this.secteurCount = secteurCount;
	}

	@Override
	public String toString() {
		return "SearchSiteData [nom=" + nom + ", departementId=" + departementId + ", cotationId=" + cotationId
				+ ", secteurCount=" + secteurCount + "]";
	}
	
	
	
}
