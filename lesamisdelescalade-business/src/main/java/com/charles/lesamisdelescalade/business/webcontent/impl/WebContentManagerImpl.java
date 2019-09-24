package com.charles.lesamisdelescalade.business.webcontent.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.charles.lesamisdelescalade.model.dto.SitePageData;

/**
 * 
 * @author Charles
 *
 */
@Service
public class WebContentManagerImpl implements WebContentManager {

	@Autowired
	private WebContentDao webContentDao;
	
	/* Logger for LoginManagerImpl class */
	private static final Logger logger = LoggerFactory.getLogger(WebContentManagerImpl.class);

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
		sitePageData.setSecteurs(getAllSecteurBySite(siteId));
		sitePageData.setVoies(findVoiesBySite(siteId));
		sitePageData.setLongueurs(findLongueursBySite(siteId));

		for (Secteur secteur : sitePageData.getSecteurs()) {
			secteur.setVoiesCount(getVoieCountBySecteurs(secteur.getId()));
			try {
				secteur.setCotationMin(getMinCotation(secteur.getId()));
				secteur.setCotationMax(getMaxCotation(secteur.getId()));
			} catch (NullPointerException e) {
				secteur.setCotationMin("NA");
				secteur.setCotationMax("NA");
			}
			
			
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

	/**
	 * 
	 */
	@Override
	public List<Site> getAllSiteByDepartement(int departementId) {
		return webContentDao.findAllSiteByDepartement(departementId);
	}

	@Override
	public int getSiteIdBySecteurId(int secteurId) {
		return webContentDao.getSiteIdBySecteurId(secteurId);
	}
	
	@Override
	public void addOfficialTagOnSite(int siteId) {
		webContentDao.addOfficialTagOnSite(siteId);
	}
	
	@Override
	public void deleteOfficialTagOnSite(int siteId) {
		webContentDao.deleteOfficialTagOnSite(siteId);
	}

	/* ========================================================================== */
	/* Secteur data from database */
	/* ========================================================================== */

	@Override
	public List<Secteur> getAllSecteurBySite(int siteId) {
		return webContentDao.findAllSecteurBySite(siteId);
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

	public int getVoieCountBySecteurs(int secteurId)  {
		return webContentDao.getVoieCountBySecteur(secteurId);
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
	public List<Departement> findAllDepartement() {
		return webContentDao.findAllDepartement();
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
		logger.info("Add site attempt");
		try {
			webContentDao.addSite(site);
			siteAddedWithSuccess = true;
			logger.debug("Site added with success - site id: " + site.getId() + " - site name: " + site.getNom());
		} catch (DuplicateKeyException e) {
			siteAddedWithSuccess = false;
			logger.warn("Site add failed - Cause: site name already exist");
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
		logger.info("Add secteur attempt");
		try {
			webContentDao.addSecteur(secteur);
			secteurAddedWithSuccess = true;
			logger.debug("Secteur added with success - secteur id: " + secteur.getId() + " - secteur name: " + secteur.getNom());
		} catch (DuplicateKeyException e) {
			secteurAddedWithSuccess = false;
			logger.warn("Secteur add failed - Cause: secteur name already exist");
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
		logger.info("Add voie attempt");
		try {
			webContentDao.findVoieByNumeroAndSecteur(voie.getNumero(), voie.getSecteur_id());
			causeError = "numero";
			logger.debug("Voie add failed - Cause: voie numero already exist");
		} catch (EmptyResultDataAccessException e) {

		}
		try {
			webContentDao.findVoieByNomAndSecteur(voie.getNom(), voie.getSecteur_id());
			causeError = "nom";
			logger.debug("Voie add failed - Cause: voie name already exist");
		} catch (EmptyResultDataAccessException e) {

		}
		if (causeError.equals("")) {
			webContentDao.addVoie(voie);
			logger.debug("Voie added with success - voie id: " + voie.getId() + " - voie numero: " + voie.getNumero() + " - voie name: " + voie.getNom());
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
		logger.info("Add longueur attempt");
		try {
			webContentDao.findLongueurByNumeroAndVoie(longueur.getNumero(), longueur.getVoie_id());
			NumeroIsAlreadyUsed = true;
			logger.debug("Longueur added with success - longueur id: " + longueur.getId() + " - longueur numero: " + longueur.getNumero());
		} catch (EmptyResultDataAccessException e) {
			webContentDao.addLongeur(longueur);
			NumeroIsAlreadyUsed = false;
			logger.debug("Longueur add failed - Cause: voie numero already exist");
		}

		return NumeroIsAlreadyUsed;
	}

}
