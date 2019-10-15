package com.charles.lesamisdelescalade.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Commentaire;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.dto.SitePageData;

/**
 * Handles requests for the application home page.
 */

@Controller
public class SiteController {

	@Autowired
	private WebContentManager webContentManager;

	

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

		SitePageData sitePageData = webContentManager.setSitePageData(siteId);
		model.addAttribute("site", sitePageData.getSite());
		model.addAttribute("secteurs", sitePageData.getSecteurs());
		model.addAttribute("voies", sitePageData.getVoies());
		model.addAttribute("longueurs", sitePageData.getLongueurs());
		model.addAttribute("sessionUtilisateur", sessionUtilisateur);
		model.addAttribute("commentaires", webContentManager.findAllCommentaireBySite(siteId));
		model.addAttribute("commentaire", new Commentaire());
		model.addAttribute("utilisateurs", webContentManager.getHashMapAllUtilisateurOnlyIdAndName());
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

	@RequestMapping(value = "/site/processAddCommentaire", method = RequestMethod.POST)
	public String addCommentaire(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@ModelAttribute(value = "commentaire") Commentaire commentaire) {

		webContentManager.addCommentaire(commentaire);
		return "redirect:/site/" + commentaire.getSite_id();
	}
	
	@RequestMapping(value = "/site/processUpdateCommentaire_{commentaireId}", method = RequestMethod.POST)
	public String updateCommentaire(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@ModelAttribute(value = "commentaire") Commentaire commentaire,
			@PathVariable(value="commentaireId") int commentaireId){

		webContentManager.updateCommentaire(commentaire, sessionUtilisateur);
		return "redirect:/site/" + commentaire.getSite_id();
	}
	
	@RequestMapping(value = "/site/processDeleteCommentaire/{siteId}/{commentaireId}", method = RequestMethod.GET)
	public String deleteCommentaire(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value="siteId") int siteId,
			@PathVariable(value="commentaireId") int commentaireId
			){

		webContentManager.updateCommentaireStatus(commentaireId);
		return "redirect:/site/" + siteId;
	}

}
