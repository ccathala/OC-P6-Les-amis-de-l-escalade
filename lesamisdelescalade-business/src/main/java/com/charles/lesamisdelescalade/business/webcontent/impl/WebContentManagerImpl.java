package com.charles.lesamisdelescalade.business.webcontent.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.consumer.WebContentDao;
import com.charles.lesamisdelescalade.model.beans.Cotation;
import com.charles.lesamisdelescalade.model.beans.Departement;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Voie;
import com.charles.lesamisdelescalade.model.utils.SitePageData;

@Service
public class WebContentManagerImpl implements WebContentManager {

	@Autowired
	private WebContentDao webContentDao;

	/* ========================================================================== */
	/* Display site page */
	/* ========================================================================== */

	/**
	 * Set bean SitePageData with site, secteur, voie and longueur data according to
	 * the choosen site
	 * 
	 * @param siteId
	 * @return sitePageData
	 */
	@Override
	public SitePageData getSitePageData(int siteId) {
		SitePageData sitePageData = new SitePageData();
		sitePageData.setSite(findSiteById(siteId));
		sitePageData.setSecteurs(getAllSecteursBySite(siteId));
		sitePageData.setVoies(findVoiesBySite(siteId));
		sitePageData.setLongueurs(findLongueursBySite(siteId));

		for (Secteur secteur : sitePageData.getSecteurs()) {
			secteur.setVoiesCount(getVoieCountBySecteurs(secteur.getId()));
			secteur.setCotationMin(getMinCotation(secteur.getId()));
			secteur.setCotationMax(getMaxCotation(secteur.getId()));
		}

		return sitePageData;
	}

	/* ========================================================================== */
	/* Site data from database */
	/* ========================================================================== */

	/**
	 * Find site by id input
	 * 
	 * @param siteId
	 * @return Site
	 */
	public Site findSiteById(int siteId) {
		return webContentDao.findSite(siteId);
	}

	@Override
	public List<Site> getAllSitesByDepartement(int departementId) {
		return webContentDao.findAllSitesByDepartement(departementId);
	}

	@Override
	public int getSiteIdBySecteurId(int secteurId) {
		return webContentDao.getSiteIdBySecteurId(secteurId);
	}

	/* ========================================================================== */
	/* Secteur data from database */
	/* ========================================================================== */

	@Override
	public List<Secteur> getAllSecteursBySite(int siteId) {
		return webContentDao.findAllSecteursBySite(siteId);
	}

	@Override
	public int getSecteurIdByVoieId(int voieId) {
		return webContentDao.getSecteurIdByVoieId(voieId);
	}

	/* ========================================================================== */
	/* Voie data from database */
	/* ========================================================================== */

	public List<Voie> findVoiesBySite(int siteId) {
		return webContentDao.findVoieBySite(siteId);
	}

	public int getVoieCountBySecteurs(int secteurId) {
		return webContentDao.getVoieCountBySecteurs(secteurId);
	}

	@Override
	public List<Voie> findAllVoieBySecteur(int secteurId) {
		return webContentDao.findAllVoieBySecteur(secteurId);
	}

	/* ========================================================================== */
	/* Longueur data from database */
	/* ========================================================================== */

	public List<Longueur> findLongueursBySite(int siteId) {
		return webContentDao.findLongueurBySite(siteId);
	}

	@Override
	public List<Longueur> findAllLongueurByVoie(int voieId) {
		return webContentDao.findAllLongueurByVoie(voieId);
	}

	/* ========================================================================== */
	/* Cotation data from database */
	/* ========================================================================== */

	public String getMinCotation(int secteurId) {
		return webContentDao.getSecteurMinCotation(secteurId);
	}

	public String getMaxCotation(int secteurId) {
		return webContentDao.getSecteurMaxCotation(secteurId);
	}

	@Override
	public List<Cotation> findAllCotation() {
		return webContentDao.findAllCotation();
	}

	/* ========================================================================== */
	/* Departement data from database */
	/* ========================================================================== */

	@Override
	public List<Departement> findAllDepartements() {
		return webContentDao.findAllDepartements();
	}

	@Override
	public int getDepartementIdBySiteId(int siteId) {
		return webContentDao.getDepartementIdBySiteId(siteId);
	}

	/* ========================================================================== */
	/* Add web content */
	/* ========================================================================== */

	/**
	 * Add new site to database
	 * @param Site
	 * @return Boolean
	 */
	@Override
	public Boolean addSite(Site site) {
		Boolean siteAddedWithSuccess;
		try {
			webContentDao.addSite(site);
			siteAddedWithSuccess = true;
		} catch (DuplicateKeyException e) {
			siteAddedWithSuccess = false;
		}
		return siteAddedWithSuccess;
	}

	/**
	 * Add new secteur to database
	 * @param Secteur
	 * @return Boolean
	 */
	@Override
	public Boolean addSecteur(Secteur secteur) {
		Boolean secteurAddedWithSuccess;
		try {
			webContentDao.addSecteur(secteur);
			secteurAddedWithSuccess = true;
		} catch (DuplicateKeyException e) {
			secteurAddedWithSuccess = false;
		}
		return secteurAddedWithSuccess;
	}

	/**
	 * Add new voie to database
	 * @param Voie
	 * @return String
	 */
	@Override
	public String addVoie(Voie voie) {
		String causeError = "";
		try {
			webContentDao.findVoieByNumeroAndSecteur(voie.getNumero(), voie.getSecteur_id());
			causeError = "numero";
		} catch (EmptyResultDataAccessException e) {

		}
		try {
			webContentDao.findVoieByNomAndSecteur(voie.getNom(), voie.getSecteur_id());
			causeError = "nom";
		} catch (EmptyResultDataAccessException e) {

		}
		if (causeError.equals("")) {
			webContentDao.addVoie(voie);
		}
		return causeError;
	}

	/**
	 * Add new longueur to database
	 * @param Longueur
	 * @return Boolean
	 */
	@Override
	public Boolean addLongueur(Longueur longueur) {
		Boolean NumeroIsAlreadyUsed;
		try {
			webContentDao.findLongueurByNumeroAndVoie(longueur.getNumero(), longueur.getVoie_id());
			NumeroIsAlreadyUsed = true;
		} catch (EmptyResultDataAccessException e) {
			webContentDao.addLongeur(longueur);
			NumeroIsAlreadyUsed = false;
		}

		return NumeroIsAlreadyUsed;
	}

}
