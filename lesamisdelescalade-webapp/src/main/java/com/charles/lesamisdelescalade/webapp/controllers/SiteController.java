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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.dto.SitePageData;

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
	 * @param sessionUtilisateur
	 * @return
	 */
	@RequestMapping(value = "/site/{siteId}", method = RequestMethod.GET)
	public String displaySite(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value = "siteId") int siteId) {

		SitePageData sitePageData = webContentManager.getSitePageData(siteId);
		model.addAttribute("site", sitePageData.getSite());
		model.addAttribute("secteurs", sitePageData.getSecteurs());
		model.addAttribute("voies", sitePageData.getVoies());
		model.addAttribute("longueurs", sitePageData.getLongueurs());
		model.addAttribute("sessionUtilisateur", sessionUtilisateur);
		return "site";
	}

	@RequestMapping(value = "/addTag/{siteId}", method = RequestMethod.GET)
	public String addTag(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value = "siteId") int siteId) {

		webContentManager.addOfficialTagOnSite(siteId);
		return "redirect:/site/" + siteId;
	}
	
	@RequestMapping(value = "/deleteTag/{siteId}", method = RequestMethod.GET)
	public String deleteTag(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value = "siteId") int siteId) {

		webContentManager.deleteOfficialTagOnSite(siteId);
		return "redirect:/site/" + siteId;
	}

}
