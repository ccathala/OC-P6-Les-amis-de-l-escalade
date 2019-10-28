package com.charles.lesamisdelescalade.business.utils.bean;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Site;

public interface SiteManager {

	

	int getSiteIdBySecteurId(int secteurId);

	void addOfficialTagOnSite(int siteId);

	void deleteOfficialTagOnSite(int siteId);

	List<Site> findAllSite();
	
	List<Site> findAllSiteByDepartement(int departementId);

	List<Site> findAllSiteByCotation(int cotationId);

	List<Site> findAllSiteBySecteurCount(int secteurCount);

	List<Integer> getSecteurCountBySite();
	// TODO rename method

	void editPicture(int siteId, String pictureUrl);

	

}
