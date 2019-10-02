package com.charles.lesamisdelescalade.business.webcontent;

import java.util.HashMap;
import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Commentaire;
import com.charles.lesamisdelescalade.model.beans.Cotation;
import com.charles.lesamisdelescalade.model.beans.Departement;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Topo;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.beans.Voie;
import com.charles.lesamisdelescalade.model.dto.SitePageData;


public interface WebContentManager {
	
	public SitePageData setSitePageData(int siteId);
	
	public Boolean addSite(Site site);
	
	public List<Site> findAllSiteByDepartement(int departementId);
	
	public Boolean addSecteur(Secteur secteur);
	
	public List<Secteur> getAllSecteurBySite(int siteId);
	
	public String addVoie (Voie voie);
	
	public int getDepartementIdBySiteId(int siteId);
	
	public List<Departement> findAllDepartement();

	int getSiteIdBySecteurId(int secteurId);

	List<Voie> findAllVoieBySecteur(int secteurId);

	Boolean addLongueur(Longueur longueur);

	int getSecteurIdByVoieId(int voieId);

	List<Cotation> findAllCotation();

	List<Longueur> findAllLongueurByVoie(int voieId);

	void addOfficialTagOnSite(int siteId);

	void deleteOfficialTagOnSite(int siteId);

	List<Site> findAllSite();

	List<Site> findAllSiteByCotation(int cotationId);

	List<Site> findAllSiteBySecteurCount(int secteurCount);

	List<Integer> getSecteurCountBySite();

	List<Site> findAllSiteByMultiCritere(int departementId, int cotationId, int secteurCount, String nom);

	List<Commentaire> findAllCommentaireBySite(int siteId);

	HashMap<Integer, String> getHashMapAllUtilisateurOnlyIdAndName();

	void addCommentaire(Commentaire commentaire);

	void updateCommentaire(Commentaire commentaire, Utilisateur utilisateur);

	void updateCommentaireStatus(int commentaireId);

	Boolean addTopo(Topo topo);

	

	

}
