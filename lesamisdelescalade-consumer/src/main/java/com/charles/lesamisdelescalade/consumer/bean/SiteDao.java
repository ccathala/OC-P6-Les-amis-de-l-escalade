package com.charles.lesamisdelescalade.consumer.bean;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.charles.lesamisdelescalade.model.beans.Site;

public interface SiteDao {

	void addSite(Site site) throws DuplicateKeyException;

	Site findSiteById(int siteId);

	List<Site> findAllSiteByDepartement(int departementId);

	int getSiteIdBySecteurId(int secteurId);

	void addOfficialTagOnSite(int siteId);

	void deleteOfficialTagOnSite(int siteId);

	List<Site> findAllSite();

	List<Site> findAllSiteByCotation(int cotationId);

	List<Site> findAllSiteBySecteurCount(int secteurCount);

	List<Integer> getSecteurCountBySite();

	List<Site> findAllSiteByMultiCritere(Object[] criteresSql, String sql);

	List<Site> findAllSiteByName(String nom);

	
	
	

}
