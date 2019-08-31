package com.charles.lesamisdelescalade.consumer;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Site;

public interface SiteDao {
	
	public void addSite(Site site);
	 
    public void editSite(Site site, int siteId);
 
    public void deleteSite(int siteId);
 
    public Site find(int siteId);
 
    public List < Site > findAll();

}
