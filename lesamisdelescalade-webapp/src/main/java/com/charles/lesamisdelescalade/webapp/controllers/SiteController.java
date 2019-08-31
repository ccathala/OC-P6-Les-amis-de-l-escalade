package com.charles.lesamisdelescalade.webapp.controllers;

import java.util.ArrayList;
import java.util.List;
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
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.beans.Voie;

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

		Site site = webContentManager.findSiteById(siteId);
		List<Secteur> secteurs = webContentManager.findSecteursBySite(site.getId());
		List<Voie> voies = webContentManager.findVoiesBySite(siteId);
		List<Longueur> longueurs = new ArrayList<Longueur>();

		for (Secteur secteur : secteurs) {
			secteur.setVoiesCount(webContentManager.getVoieCount(secteur.getId()));
			secteur.setCotationMin(webContentManager.getMinCotation(secteur.getId()));
			secteur.setCotationMax(webContentManager.getMaxCotation(secteur.getId()));
			longueurs.addAll(webContentManager.findLongueursBySecteur(secteur.getId()));
			
		}

		for(Longueur longueur: longueurs) {
			longueur.setCotation(webContentManager.getCotation(longueur.getCotation_id()));
		}
		
		model.addAttribute("site", site);
		model.addAttribute("secteurs", secteurs);
		model.addAttribute("voies", voies);
		model.addAttribute("longueurs", longueurs);
		model.addAttribute("utilisateurSession", utilisateurSession);

		return "site";
	}

}
