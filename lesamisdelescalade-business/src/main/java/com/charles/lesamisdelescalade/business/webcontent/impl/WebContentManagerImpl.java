package com.charles.lesamisdelescalade.business.webcontent.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.consumer.DepartementDao;
import com.charles.lesamisdelescalade.consumer.LongueurDao;
import com.charles.lesamisdelescalade.consumer.SecteurDao;
import com.charles.lesamisdelescalade.consumer.SiteDao;
import com.charles.lesamisdelescalade.consumer.VoieDao;
import com.charles.lesamisdelescalade.model.beans.Departement;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Voie;
import com.charles.lesamisdelescalade.model.utils.SitePageData;

@Service
public class WebContentManagerImpl implements WebContentManager {
	
	@Autowired
	private SecteurDao secteurDao ;
	
	@Autowired
	private VoieDao voieDao;
	
	@Autowired
	private SiteDao siteDao; 
	
	@Autowired
	private LongueurDao longueurDao;
	
	@Autowired
	private DepartementDao departementDao;
	
	@Override
	public SitePageData getSitePageData(int siteId) {
		SitePageData sitePageData = new SitePageData();
		sitePageData.setSite(findSiteById(siteId));
		sitePageData.setSecteurs(getAllSecteursBySite(siteId));
		sitePageData.setVoies(findVoiesBySite(siteId));
		sitePageData.setLongueurs(findLongueursBySite(siteId));
		
		for (Secteur secteur : sitePageData.getSecteurs()) {
			secteur.setVoiesCount(getVoieCount(secteur.getId()));
			secteur.setCotationMin(getMinCotation(secteur.getId()));
			secteur.setCotationMax(getMaxCotation(secteur.getId()));
		}
		
		return sitePageData;
	}
	
	
	public Site findSiteById(int siteId) {
		return siteDao.find(siteId);
	}

	@Override
	public List<Secteur> getAllSecteursBySite(int siteId) {
		return secteurDao.findAllSecteursBySite(siteId);
	}
	
	
	public List<Voie> findVoiesBySite(int siteId){
		return voieDao.findBySite(siteId);
	}
	
	
	
	public List<Longueur> findLongueursBySite(int siteId){
		return longueurDao.findBySite(siteId);
	}
	
	
	public int getVoieCount(int secteurId) {
		return voieDao.getVoieCount(secteurId);
	}
	
	
	public String getMinCotation(int secteurId) {
		return voieDao.getMinCotation(secteurId);
	}
	
	
	public String getMaxCotation(int secteurId) {
		return voieDao.getMinCotation(secteurId);
	}


	@Override
	public List<Departement> getDepartements() {
		return departementDao.findAll();
	}
	
	@Override
	public Boolean addSite(Site site) {
		Boolean siteAddedWithSuccess;
		try {
			siteDao.addSite(site);
			siteAddedWithSuccess = true; 
		} catch (DuplicateKeyException e) {
			siteAddedWithSuccess = false;
		}
		return siteAddedWithSuccess;
	}
	
	@Override
	public List<Site> getAllSitesByDepartement(int departementId){
		return siteDao.findAllSitesByDepartement(departementId);
	}
	
	@Override
	public Boolean addSecteur(Secteur secteur) {
		Boolean secteurAddedWithSuccess;
		try {
			secteurDao.addSecteur(secteur);
			secteurAddedWithSuccess = true;
		} catch (DuplicateKeyException e) {
			secteurAddedWithSuccess = false;
		}
		return secteurAddedWithSuccess;
	}
	
	@Override
	public String addVoie (Voie voie) {
		String causeError="";
		try {
			voieDao.findVoieByNumeroAndSite(voie.getNumero(), voie.getSecteur_id());
		} catch (EmptyResultDataAccessException e) {
			causeError="numero";
		}
		try {
			voieDao.findVoieByNomAndSite(voie.getNom(), voie.getSecteur_id());
		} catch (EmptyResultDataAccessException e) {
			causeError="nom";
		}
		if(causeError.equals("")) {
			voieDao.addVoie(voie);
		}
		return causeError;
	}
	
	@Override
	public int getDepartementIdBySiteId(int siteId) {
		return siteDao.getDepartementIdBySiteId(siteId);
	}
	
	
	

}
