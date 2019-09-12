package com.charles.lesamisdelescalade.webapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.utils.SitePageData;

/**
 * Handles requests for the application home page.
 */

@Controller
public class SiteController {

	@Autowired
	private WebContentManager webContentManager;

	private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @param locale
	 * @param model
	 * @param utilisateurSession
	 * @return
	 */
	@RequestMapping(value = "/site/{siteId}", method = RequestMethod.GET)
	public String home(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur utilisateurSession,
			@PathVariable(value = "siteId") int siteId) {
		
		SitePageData sitePageData  = webContentManager.getSitePageData(siteId);
		model.addAttribute("site", sitePageData.getSite());
		model.addAttribute("secteurs", sitePageData.getSecteurs());
		model.addAttribute("voies", sitePageData.getVoies());
		model.addAttribute("longueurs", sitePageData.getLongueurs());
		model.addAttribute("utilisateurSession", utilisateurSession);

		return "site";
	}

}
