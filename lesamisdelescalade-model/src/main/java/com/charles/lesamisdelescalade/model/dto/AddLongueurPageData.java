package com.charles.lesamisdelescalade.model.dto;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Cotation;

public class AddLongueurPageData extends AddWebContentPageData {

	private int departementIdLongueur;
	private int siteIdLongueur;
	private int secteurIdLongueur;
	private int voieIdLongueur;
	private int cotationIdLongueur;
	private String messageErrorLongueur;
	private String messageSuccessLongueur;
	private String collapseClassLongueur;
	private Boolean collapseAriaLongueur;
	private List<Cotation> cotations;
	
	public AddLongueurPageData() {
		super();
	}

	public AddLongueurPageData(int departementIdLongueur, int siteIdLongueur, int secteurIdLongueur, int voieIdLongueur,
			int cotationIdLongueur, String messageErrorLongueur, String messageSuccessLongueur,
			String collapseClassLongueur, Boolean collapseAriaLongueur, List<Cotation> cotations) {
		super();
		this.departementIdLongueur = departementIdLongueur;
		this.siteIdLongueur = siteIdLongueur;
		this.secteurIdLongueur = secteurIdLongueur;
		this.voieIdLongueur = voieIdLongueur;
		this.cotationIdLongueur = cotationIdLongueur;
		this.messageErrorLongueur = messageErrorLongueur;
		this.messageSuccessLongueur = messageSuccessLongueur;
		this.collapseClassLongueur = collapseClassLongueur;
		this.collapseAriaLongueur = collapseAriaLongueur;
		this.cotations = cotations;
	}

	public int getDepartementIdLongueur() {
		return departementIdLongueur;
	}

	public void setDepartementIdLongueur(int departementIdLongueur) {
		this.departementIdLongueur = departementIdLongueur;
	}

	public int getSiteIdLongueur() {
		return siteIdLongueur;
	}

	public void setSiteIdLongueur(int siteIdLongueur) {
		this.siteIdLongueur = siteIdLongueur;
	}

	public int getSecteurIdLongueur() {
		return secteurIdLongueur;
	}

	public void setSecteurIdLongueur(int secteurIdLongueur) {
		this.secteurIdLongueur = secteurIdLongueur;
	}

	public int getVoieIdLongueur() {
		return voieIdLongueur;
	}

	public void setVoieIdLongueur(int voieIdLongueur) {
		this.voieIdLongueur = voieIdLongueur;
	}

	public int getCotationIdLongueur() {
		return cotationIdLongueur;
	}

	public void setCotationIdLongueur(int cotationIdLongueur) {
		this.cotationIdLongueur = cotationIdLongueur;
	}

	public String getMessageErrorLongueur() {
		return messageErrorLongueur;
	}

	public void setMessageErrorLongueur(String messageErrorLongueur) {
		this.messageErrorLongueur = messageErrorLongueur;
	}

	public String getMessageSuccessLongueur() {
		return messageSuccessLongueur;
	}

	public void setMessageSuccessLongueur(String messageSuccessLongueur) {
		this.messageSuccessLongueur = messageSuccessLongueur;
	}

	public String getCollapseClassLongueur() {
		return collapseClassLongueur;
	}

	public void setCollapseClassLongueur(String collapseClassLongueur) {
		this.collapseClassLongueur = collapseClassLongueur;
	}

	public Boolean getCollapseAriaLongueur() {
		return collapseAriaLongueur;
	}

	public void setCollapseAriaLongueur(Boolean collapseAriaLongueur) {
		this.collapseAriaLongueur = collapseAriaLongueur;
	}

	public List<Cotation> getCotations() {
		return cotations;
	}

	public void setCotations(List<Cotation> cotations) {
		this.cotations = cotations;
	}

	@Override
	public String toString() {
		return "AddLongueurPageData [departementIdLongueur=" + departementIdLongueur + ", siteIdLongueur="
				+ siteIdLongueur + ", secteurIdLongueur=" + secteurIdLongueur + ", voieIdLongueur=" + voieIdLongueur
				+ ", cotationIdLongueur=" + cotationIdLongueur + ", messageErrorLongueur=" + messageErrorLongueur
				+ ", messageSuccessLongueur=" + messageSuccessLongueur + ", collapseClassLongueur="
				+ collapseClassLongueur + ", collapseAriaLongueur=" + collapseAriaLongueur + ", cotations=" + cotations
				+ "]";
	}
	
	
	
	
	

}
