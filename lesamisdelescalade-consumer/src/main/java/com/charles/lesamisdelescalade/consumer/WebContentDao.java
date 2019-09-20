package com.charles.lesamisdelescalade.consumer;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import com.charles.lesamisdelescalade.model.beans.Cotation;
import com.charles.lesamisdelescalade.model.beans.Departement;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Voie;


public interface WebContentDao {

	List<Departement> findAllDepartement();

	int getDepartementIdBySiteId(int siteId);

	void addSite(Site site) throws DuplicateKeyException;

	Site findSite(int siteId);

	List<Site> findAllSiteByDepartement(int departementId);

	void addSecteur(Secteur secteur) throws DuplicateKeyException;

	List<Secteur> findAllSecteurBySite(int siteId);

	List<Voie> findVoieBySite(int siteId);

	int getVoieCountBySecteur(int secteurId);

	String getSecteurMinCotation(int secteurId) throws NullPointerException;

	String getSecteurMaxCotation(int secteurId) throws NullPointerException;

	void addVoie(Voie voie);

	List<Longueur> findLongueurBySite(int siteId);

	int getSiteIdBySecteurId(int secteurId);

	Voie findVoieByNomAndSecteur(String nom, int secteurId) throws EmptyResultDataAccessException;

	Voie findVoieByNumeroAndSecteur(int numero, int secteurId) throws EmptyResultDataAccessException;

	List<Voie> findAllVoieBySecteur(int secteurId);

	void addLongeur(Longueur longueur);

	Longueur findLongueurByNumeroAndVoie(int numero, int voieId);

	int getSecteurIdByVoieId(int voieId) ;

	List<Cotation> findAllCotation();

	List<Longueur> findAllLongueurByVoie(int voieId);
	
	

}
