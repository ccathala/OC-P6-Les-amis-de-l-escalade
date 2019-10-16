package com.charles.lesamisdelescalade.webapp.utils;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.charles.lesamisdelescalade.business.utils.bean.DepartementManager;
import com.charles.lesamisdelescalade.business.utils.bean.LongueurManager;
import com.charles.lesamisdelescalade.business.utils.bean.SiteManager;
import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.dto.SearchSiteData;

@Component
public class SearchSiteFormUtil {
	
	@Autowired
	private WebContentManager webContentManager;
	@Autowired
	private SiteManager siteManager;
	@Autowired
	private LongueurManager longueurManager;
	@Autowired
	private DepartementManager departementManager;
	
	public HashMap<String, Object> getSearchSiteAttributes(Utilisateur sessionUtilisateur, SearchSiteData searchSiteData, List<Site> sites ){
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("departements", departementManager.findAllDepartement());
		attributes.put("departementId", searchSiteData.getDepartementId());
		attributes.put("cotations", longueurManager.findAllLongueurCotation());
		attributes.put("cotationId", searchSiteData.getCotationId());
		attributes.put("secteurCountList", siteManager.getSecteurCountBySite());
		attributes.put("secteurCount", searchSiteData.getSecteurCount());
		attributes.put("sessionUtilisateur", sessionUtilisateur);
		attributes.put("nom", searchSiteData.getNom());
		if(searchSiteData.getNom()==null && searchSiteData.getDepartementId()==0 && searchSiteData.getCotationId()==0 && searchSiteData.getSecteurCount()==0) {
			attributes.put("searchSiteData", new SearchSiteData());
			attributes.put("sites", siteManager.findAllSite());
		}else {
			attributes.put("searchSiteData", searchSiteData);
			attributes.put("sites", sites);
		}
		return attributes;
	}

}
