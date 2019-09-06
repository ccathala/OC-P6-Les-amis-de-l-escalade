package com.charles.lesamisdelescalade.business.webcontent;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Departement;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Voie;
import com.charles.lesamisdelescalade.model.utils.SitePageData;

public interface WebContentManager {
	
	public SitePageData getSitePageData(int siteId);
	public List<Departement> getDepartements();
	public Boolean addSite(Site site);
	public List<Site> getAllSitesByDepartement(int departementId);
	public Boolean addSecteur(Secteur secteur);
	public List<Secteur> getAllSecteursBySite(int siteId);
	public String addVoie (Voie voie);
	public int getDepartementIdBySiteId(int siteId);

}
