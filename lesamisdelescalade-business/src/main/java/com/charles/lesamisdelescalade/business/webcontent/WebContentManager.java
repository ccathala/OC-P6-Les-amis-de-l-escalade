package com.charles.lesamisdelescalade.business.webcontent;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Voie;

public interface WebContentManager {
	
	public Site findSiteById(int siteId);
	public List<Secteur> findSecteursBySite(int siteId);
	public List<Voie> findVoiesBySite(int siteId);
	public List<Longueur> findLongueursBySecteur(int secteurId);
	public int getVoieCount(int secteurId);
	public String getMinCotation(int secteurId);
	public String getMaxCotation(int secteurId);
	public String getCotation(int cotationId);

}
