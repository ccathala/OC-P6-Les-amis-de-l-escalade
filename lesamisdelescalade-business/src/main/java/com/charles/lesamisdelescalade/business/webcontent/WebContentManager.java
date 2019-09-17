package com.charles.lesamisdelescalade.business.webcontent;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Cotation;
import com.charles.lesamisdelescalade.model.beans.Departement;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Voie;
import com.charles.lesamisdelescalade.model.utils.SitePageData;


public interface WebContentManager {
	
	public SitePageData getSitePageData(int siteId);
	
	public Boolean addSite(Site site);
	
	public List<Site> getAllSitesByDepartement(int departementId);
	
	public Boolean addSecteur(Secteur secteur);
	
	public List<Secteur> getAllSecteursBySite(int siteId);
	
	public String addVoie (Voie voie);
	
	public int getDepartementIdBySiteId(int siteId);
	
	public List<Departement> findAllDepartements();

	int getSiteIdBySecteurId(int secteurId);

	List<Voie> findAllVoieBySecteur(int secteurId);

	Boolean addLongueur(Longueur longueur);

	int getSecteurIdByVoieId(int voieId);

	List<Cotation> findAllCotation();

	List<Longueur> findAllLongueurByVoie(int voieId);

}
