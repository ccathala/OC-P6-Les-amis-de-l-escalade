package com.charles.lesamisdelescalade.consumer;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.charles.lesamisdelescalade.model.beans.Site;

public interface SiteDao {
	
	public void addSite(Site site)throws DuplicateKeyException;
    public Site find(int siteId);
    public List<Site> findAllSitesByDepartement(int departementId);
    public int getDepartementIdBySiteId(int siteId);
   

}
