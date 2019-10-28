package com.charles.lesamisdelescalade.business.utils.bean.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charles.lesamisdelescalade.business.utils.bean.SiteManager;
import com.charles.lesamisdelescalade.consumer.bean.SiteDao;
import com.charles.lesamisdelescalade.model.beans.Site;

@Service
public class SiteManagerImpl implements SiteManager {
	
	@Autowired
	private SiteDao siteDao;
	
	/**
	 * Find site by id input
	 * 
	 * @param siteId
	 * @return Site
	 */
	
	
	@Override
	public List<Site> findAllSiteByDepartement(int departementId) {
		return siteDao.findAllSiteByDepartement(departementId);
	}

	@Override
	public int getSiteIdBySecteurId(int secteurId) {
		return siteDao.getSiteIdBySecteurId(secteurId);
	}

	@Override
	public void addOfficialTagOnSite(int siteId) {
		siteDao.addOfficialTagOnSite(siteId);
	}

	@Override
	public void deleteOfficialTagOnSite(int siteId) {
		siteDao.deleteOfficialTagOnSite(siteId);
	}

	@Override
	public List<Site> findAllSite() {
		return siteDao.findAllSite();
	}

	@Override
	public List<Site> findAllSiteByCotation(int cotationId) {
		return siteDao.findAllSiteByCotation(cotationId);
	}

	@Override
	public List<Site> findAllSiteBySecteurCount(int secteurCount) {
		return siteDao.findAllSiteBySecteurCount(secteurCount);
	}

	@Override
	public List<Integer> getSecteurCountBySite() {
		return siteDao.getSecteurCountBySite();
	}
	
	@Override
	public void editPicture(int siteId, String pictureUrl) {
		siteDao.editPicture(siteId, pictureUrl);
	}

}
