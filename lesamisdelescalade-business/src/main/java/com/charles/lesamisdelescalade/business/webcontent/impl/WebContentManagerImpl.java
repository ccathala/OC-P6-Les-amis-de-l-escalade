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
	private WebContentDao webContentDao ;
	
	
	
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
	
	
	public Site findSiteById(int siteId) {
		return webContentDao.findSite(siteId);
	}

	@Override
	public List<Secteur> getAllSecteursBySite(int siteId) {
		return webContentDao.findAllSecteursBySite(siteId);
	}
	
	
	public List<Voie> findVoiesBySite(int siteId){
		return webContentDao.findVoieBySite(siteId);
	}
	
	
	
	public List<Longueur> findLongueursBySite(int siteId){
		return webContentDao.findLongueurBySite(siteId);
	}
	
	
	public int getVoieCountBySecteurs(int secteurId) {
		return webContentDao.getVoieCountBySecteurs(secteurId);
	}
	
	
	public String getMinCotation(int secteurId) {
		return webContentDao.getSecteurMinCotation(secteurId);
	}
	
	
	public String getMaxCotation(int secteurId) {
		return webContentDao.getSecteurMaxCotation(secteurId);
	}


	@Override
	public List<Departement> findAllDepartements() {
		return webContentDao.findAllDepartements();
	}
	
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
	
	@Override
	public List<Site> getAllSitesByDepartement(int departementId){
		return webContentDao.findAllSitesByDepartement(departementId);
	}
	
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
	
	@Override
	public String addVoie (Voie voie) {
		String causeError="";
		try {
			webContentDao.findVoieByNumeroAndSecteur(voie.getNumero(), voie.getSecteur_id());
			causeError="numero";
		} catch (EmptyResultDataAccessException e) {
			
		}
		try {
			webContentDao.findVoieByNomAndSecteur(voie.getNom(), voie.getSecteur_id());
			causeError="nom";
		} catch (EmptyResultDataAccessException e) {
			
		}
		if(causeError.equals("")) {
			webContentDao.addVoie(voie);
		}
		return causeError;
	}
	
	@Override
	public int getDepartementIdBySiteId(int siteId) {
		return webContentDao.getDepartementIdBySiteId(siteId);
	}
	
	@Override
	public int getSiteIdBySecteurId(int secteurId) {
		return webContentDao.getSiteIdBySecteurId(secteurId);
	}
	
	@Override
	public List<Voie> findAllVoieBySecteur(int secteurId) {
		return webContentDao.findAllVoieBySecteur(secteurId);
	}
	
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
	
	@Override
	public int getSecteurIdByVoieId(int voieId) {
		return webContentDao.getSecteurIdByVoieId(voieId);
	}
	
	@Override
	public List<Cotation> findAllCotation(){
		return webContentDao.findAllCotation();
	}
	
	
	

}
