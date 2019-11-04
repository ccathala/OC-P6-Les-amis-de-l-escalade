package com.charles.lesamisdelescalade.webapp.controllers;

import javax.validation.Valid;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.charles.lesamisdelescalade.business.utils.bean.DepartementManager;
import com.charles.lesamisdelescalade.business.utils.bean.SecteurManager;
import com.charles.lesamisdelescalade.business.utils.bean.SiteManager;
import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.webapp.utils.AddWebContentFormUtil;

/**
 * Handle add Longueur content request
 * 
 * @author Charles
 *
 */
@Controller
public class AddLongueurController {

	@Autowired
	private WebContentManager webContentManager;
	@Autowired
	private AddWebContentFormUtil addWebContentFormUtil;
	@Autowired
	private SiteManager siteManager;
	@Autowired
	private SecteurManager secteurManager;
	@Autowired
	private DepartementManager departementManager;
	
	// Set logger
	private static final Logger logger = LoggerFactory.getLogger(AddLongueurController.class);
	
	/**
	 * Record departement and return addWebContent page
	 * 
	 * @param model
	 * @param departementId
	 * @param utilisateurSession
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/site/processChooseDepartementAddLongueur", method = RequestMethod.GET)
	public String chooseDepartementLongueur(Model model,
			@RequestParam(value = "departementIdLongueur") int departementId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur utilisateurSession,
			RedirectAttributes redirectAttributes) {
		
		logger.info("Requête d'accès à l'url /site/processChooseDepartementAddLongueur");
		if (utilisateurSession == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			logger.warn("Utilisateur non connecté - Accès refusé");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributesWhenDepartementIsSet(departementId, utilisateurSession));
			return "addWebContent";
		}
	}

	/**
	 * Record site and return addWebContent page
	 * 
	 * @param model
	 * @param siteId
	 * @param utilisateurSession
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/site/processChooseSiteAddLongueur", method = RequestMethod.GET)
	public String chooseSiteLongueur(Model model, @RequestParam(value = "siteIdLongueur") int siteId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur utilisateurSession,
			RedirectAttributes redirectAttributes) {
		
		logger.info("Requête d'accès à l'url /site/processChooseSiteAddLongueur");
		if (utilisateurSession == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributesWhenSiteIsSet(siteId, utilisateurSession));
			return "addWebContent";
		}
	}

	/**
	 * Record secteur and return addWebContent page
	 * 
	 * @param model
	 * @param secteurId
	 * @param utilisateurSession
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/site/processChooseSecteurAddLongueur", method = RequestMethod.GET)
	public String chooseSecteurLongueur(Model model, @RequestParam(value = "secteurIdLongueur") int secteurId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur utilisateurSession,
			RedirectAttributes redirectAttributes) {

		logger.info("Requête d'accès à l'url /site/processChooseSecteurAddLongueur");
		if (utilisateurSession == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributesWhenSecteurIsSet(secteurId, utilisateurSession));
			return "addWebContent";
		}
	}

	/**
	 * Record voie and return addWebContent page
	 * 
	 * @param model
	 * @param voieId
	 * @param utilisateurSession
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/site/processChooseVoieAddLongueur", method = RequestMethod.GET)
	public String chooseVoieLongueur(Model model, @RequestParam(value = "voieIdLongueur") int voieId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur utilisateurSession,
			RedirectAttributes redirectAttributes) {

		logger.info("Requête d'accès à l'url /site/processChooseVoieAddLongueur");
		if (utilisateurSession == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributesWhenVoieIsSet(voieId, utilisateurSession));
			return "addWebContent";
		}
	}

	/**
	 * Handle add longueur request
	 * 
	 * @param model
	 * @param longueur
	 * @param result
	 * @param utilisateurSession
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/site/processAddLongueur", method = RequestMethod.POST)
	public String addLongueur(Model model, @Valid @ModelAttribute(value = "longueur") Longueur longueur,
			BindingResult result,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur utilisateurSession,
			RedirectAttributes redirectAttributes) {

		logger.info("Requête d'accès à l'url /site/processAddLongueur");
		if (utilisateurSession == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			int secteurId = secteurManager.getSecteurIdByVoieId(longueur.getVoie_id());
			int siteId = siteManager.getSiteIdBySecteurId(secteurId);
			int departementId = departementManager.getDepartementIdBySiteId(siteId);
			if (result.hasErrors()) {
				model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributesWhenValidationErrors(longueur,
						departementId, siteId, secteurId, utilisateurSession));
			} else {
				Boolean isNumberInputAlreadyUsed = webContentManager.addLongueur(longueur);
				model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributes(longueur, departementId, siteId,
						secteurId, isNumberInputAlreadyUsed, utilisateurSession));
			}
			return "addWebContent";
		}
	}
}
