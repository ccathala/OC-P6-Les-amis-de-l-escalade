package com.charles.lesamisdelescalade.business.webcontent.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.consumer.LongueurDao;
import com.charles.lesamisdelescalade.consumer.SecteurDao;
import com.charles.lesamisdelescalade.consumer.SiteDao;
import com.charles.lesamisdelescalade.consumer.VoieDao;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Voie;




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
	
	@Override
	public Site findSiteById(int siteId) {
		return siteDao.find(siteId);
	}

	@Override
	public List<Secteur> findSecteursBySite(int siteId) {
		return secteurDao.findSecteursBySite(siteId);
	}
	
	@Override
	public List<Voie> findVoiesBySite(int siteId){
		return voieDao.findBySite(siteId);
	}
	
	@Override
	public List<Longueur> findLongueursBySecteur(int secteurId){
		return longueurDao.findBySecteur(secteurId);
	}
	
	@Override
	public int getVoieCount(int secteurId) {
		return voieDao.getVoieCount(secteurId);
	}
	
	@Override
	public String getMinCotation(int secteurId) {
		return voieDao.getMinCotation(secteurId);
	}
	
	@Override
	public String getMaxCotation(int secteurId) {
		return voieDao.getMinCotation(secteurId);
	}
	
	@Override
	public String getCotation(int cotationId) {
		return longueurDao.getCotation(cotationId);
	}

}
