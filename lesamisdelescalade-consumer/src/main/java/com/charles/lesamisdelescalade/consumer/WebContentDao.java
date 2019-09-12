package com.charles.lesamisdelescalade.consumer;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.charles.lesamisdelescalade.model.beans.Departement;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Voie;


public interface WebContentDao {

	List<Departement> findAllDepartements();

	int getDepartementIdBySiteId(int siteId);

	void addSite(Site site) throws DuplicateKeyException;

	Site findSite(int siteId);

	List<Site> findAllSitesByDepartement(int departementId);

	void addSecteur(Secteur secteur) throws DuplicateKeyException;

	List<Secteur> findAllSecteursBySite(int siteId);

	List<Voie> findVoieBySite(int siteId);

	int getVoieCountBySecteurs(int secteurId);

	String getSecteurMinCotation(int secteurId);

	String getSecteurMaxCotation(int secteurId);

	void addVoie(Voie voie);

	List<Longueur> findLongueurBySite(int siteId);

	int getSiteIdBySecteurId(int secteurId);

	Voie findVoieByNomAndSecteur(String nom, int secteurId) throws EmptyResultDataAccessException;

	Voie findVoieByNumeroAndSecteur(int numero, int secteurId) throws EmptyResultDataAccessException;
	
	

}
