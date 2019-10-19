package com.charles.lesamisdelescalade.webapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import com.charles.lesamisdelescalade.business.utils.bean.CommentaireManager;
import com.charles.lesamisdelescalade.business.utils.bean.SiteManager;
import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Commentaire;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.dto.SitePageData;

/**
 * Controller class in relation with site jsp
 * 
 * @author Charles
 *
 */
@Controller
public class SiteController {

	@Autowired
	private WebContentManager webContentManager;
	@Autowired
	private SiteManager siteManager;
	@Autowired
	private CommentaireManager commentaireManager;

	// Set logger
	private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

	/**
	 * Display chosen site page
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
		
		logger.info("Requête d'accès à l'url /site/" + siteId);
		SitePageData sitePageData = webContentManager.setSitePageData(siteId);
		model.addAttribute("site", sitePageData.getSite());
		model.addAttribute("secteurs", sitePageData.getSecteurs());
		model.addAttribute("voies", sitePageData.getVoies());
		model.addAttribute("longueurs", sitePageData.getLongueurs());
		model.addAttribute("sessionUtilisateur", sessionUtilisateur);
		model.addAttribute("commentaires", commentaireManager.findAllCommentaireBySite(siteId));
		model.addAttribute("commentaire", new Commentaire());
		model.addAttribute("utilisateurs", webContentManager.getHashMapAllUtilisateurOnlyIdAndName());
		return "site";
	}

	/**
	 * Add official tag on current site page, refresh site page
	 * 
	 * @param model
	 * @param sessionUtilisateur
	 * @param siteId
	 * @return
	 */
	@RequestMapping(value = "/addTag/{siteId}", method = RequestMethod.GET)
	public String addTag(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value = "siteId") int siteId) {

		logger.info("Requête d'accès à l'url /addTag/" + siteId);
		siteManager.addOfficialTagOnSite(siteId);
		return "redirect:/site/" + siteId;
	}
	
	/**
	 * Delete official tag on current site page, refresh site page
	 * 
	 * @param model
	 * @param sessionUtilisateur
	 * @param siteId
	 * @return
	 */
	@RequestMapping(value = "/deleteTag/{siteId}", method = RequestMethod.GET)
	public String deleteTag(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value = "siteId") int siteId) {
		
		logger.info("Requête d'accès à l'url /deleteTag/" + siteId);
		siteManager.deleteOfficialTagOnSite(siteId);
		return "redirect:/site/" + siteId;
	}

	/**
	 * Add comment on current site page, refresh site page
	 * 
	 * @param model
	 * @param sessionUtilisateur
	 * @param commentaire
	 * @return
	 */
	@RequestMapping(value = "/site/processAddCommentaire", method = RequestMethod.POST)
	public String addCommentaire(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@ModelAttribute(value = "commentaire") Commentaire commentaire) {

		logger.info("Requête d'accès à l'url /site/processAddCommentaire");
		commentaireManager.addCommentaire(commentaire);
		return "redirect:/site/" + commentaire.getSite_id();
	}
	
	/**
	 * Update comment on current site page, refresh site page
	 * 
	 * @param model
	 * @param sessionUtilisateur
	 * @param commentaire
	 * @param commentaireId
	 * @return
	 */
	@RequestMapping(value = "/site/processUpdateCommentaire/{commentaireId}", method = RequestMethod.POST)
	public String updateCommentaire(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@ModelAttribute(value = "commentaire") Commentaire commentaire,
			@PathVariable(value="commentaireId") int commentaireId){
		
		logger.info("Requête d'accès à l'url /site/processUpdateCommentaire/" + commentaireId);
		webContentManager.updateCommentaire(commentaire, sessionUtilisateur);
		return "redirect:/site/" + commentaire.getSite_id();
	}
	
	/**
	 * Delete comment on current site page, refresh site page
	 * 
	 * @param model
	 * @param sessionUtilisateur
	 * @param siteId
	 * @param commentaireId
	 * @return
	 */
	@RequestMapping(value = "/site/processDeleteCommentaire/{siteId}/{commentaireId}", method = RequestMethod.GET)
	public String deleteCommentaire(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value="siteId") int siteId,
			@PathVariable(value="commentaireId") int commentaireId
			){

		logger.info("Requête d'accès à l'url /site/processDeleteCommentaire/" + siteId + "/" + commentaireId);
		commentaireManager.updateCommentaireStatus(commentaireId);
		return "redirect:/site/" + siteId;
	}

}
