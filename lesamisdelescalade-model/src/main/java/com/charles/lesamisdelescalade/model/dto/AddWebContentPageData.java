package com.charles.lesamisdelescalade.model.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.charles.lesamisdelescalade.model.beans.Cotation;
import com.charles.lesamisdelescalade.model.beans.Departement;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Voie;

/**
 * DTO for AddWebContentController class
 * @author Charles
 *
 */
@Component
public class AddWebContentPageData {
	
	private Site site;
	private Secteur secteur;
	private Voie voie;
	private Longueur longueur;
	private List<Departement> departements;
	private List<Site> sites;
	private List<Secteur> secteurs;
	private List<Voie> voies;
	
	public AddWebContentPageData() {
		super();
	}

	public AddWebContentPageData(Site site, Secteur secteur, Voie voie, Longueur longueur,
			List<Departement> departements, List<Site> sites, List<Secteur> secteurs, List<Voie> voies) {
		super();
		this.site = site;
		this.secteur = secteur;
		this.voie = voie;
		this.longueur = longueur;
		this.departements = departements;
		this.sites = sites;
		this.secteurs = secteurs;
		this.voies = voies;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	public Voie getVoie() {
		return voie;
	}

	public void setVoie(Voie voie) {
		this.voie = voie;
	}

	public Longueur getLongueur() {
		return longueur;
	}

	public void setLongueur(Longueur longueur) {
		this.longueur = longueur;
	}

	public List<Departement> getDepartements() {
		return departements;
	}

	public void setDepartements(List<Departement> departements) {
		this.departements = departements;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public List<Secteur> getSecteurs() {
		return secteurs;
	}

	public void setSecteurs(List<Secteur> secteurs) {
		this.secteurs = secteurs;
	}

	public List<Voie> getVoies() {
		return voies;
	}

	public void setVoies(List<Voie> voies) {
		this.voies = voies;
	}

	@Override
	public String toString() {
		return "AddWebContentPageData [site=" + site + ", secteur=" + secteur + ", voie=" + voie + ", longueur="
				+ longueur + ", departements=" + departements + ", sites=" + sites + ", secteurs=" + secteurs
				+ ", voies=" + voies + "]";
	}
	
	
	
	/* Site */
//	private int departementIdSite;
//	private String collapseClassSite;
//	private Boolean collapseAriaSite;
//	private String messageErrorSite;
//	private String messageSuccessSite;
	
	/* Secteur */
	
//	private int departementIdSecteur;
//	private int siteIdSecteur;
//	private String messageErrorSecteur;
//	private String messageSuccessSecteur;
//	private String collapseClassSecteur;
//	private Boolean collapseAriaSecteur;
	
	/* Voie */
	
//	private int departementIdVoie;
//	private int siteIdVoie;
//	private int secteurIdVoie;
//	private String messageErrorVoie;
//	private String messageSuccessVoie;
//	private String collapseClassVoie;
//	private Boolean collapseAriaVoie;
	
	/* Longueur */
		
//	private int departementIdLongueur;
//	private int siteIdLongueur;
//	private int secteurIdLongueur;
//	private int voieIdLongueur;
//	private int cotationIdLongueur;
//	private String messageErrorLongueur;
//	private String messageSuccessLongueur;
//	private String collapseClassLongueur;
//	private Boolean collapseAriaLongueur;
//	private List<Cotation> cotations;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	

}
