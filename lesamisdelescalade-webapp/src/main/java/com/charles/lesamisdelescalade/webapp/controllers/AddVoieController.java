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

import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.beans.Voie;
import com.charles.lesamisdelescalade.webapp.utils.AddWebContentFormUtil;

/**
 * Handle add voie content request
 * 
 * @author Charles
 *
 */
@Controller
public class AddVoieController {

	@Autowired
	private WebContentManager webContentManager;
	@Autowired
	private AddWebContentFormUtil addWebContentFormUtil;
	
	// Set logger
	private static final Logger logger = LoggerFactory.getLogger(AddVoieController.class);
	

	/**
	 * Record chosen departement and return addWebContent page
	 * 
	 * @param model
	 * @param departementId
	 * @param sessionUtilisateur
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/site/processChooseDepartementAddVoie", method = RequestMethod.GET)
	public String chooseDepartementVoie(Model model, @RequestParam(value = "departementIdVoie") int departementId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			RedirectAttributes redirectAttributes) {
		
		logger.info("Requête d'accès à l'url /site/processChooseDepartementAddVoie");
		if (sessionUtilisateur == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			logger.warn("Utilisateur non connecté - Accès refusé");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddVoieAttributesWhenDepartementIsSet(departementId, sessionUtilisateur));
			return "addWebContent";
		}
	}

	/**
	 * Record chosen site and return addWebContent page
	 * 
	 * @param model
	 * @param siteId
	 * @param sessionUtilisateur
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/site/processChooseSiteAddVoie", method = RequestMethod.GET)
	public String chooseSiteVoie(Model model, @RequestParam(value = "siteIdVoie") int siteId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			RedirectAttributes redirectAttributes) {

		logger.info("Requête d'accès à l'url /site/processChooseSiteAddVoie");
		if (sessionUtilisateur == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddVoieAttributesWhenSiteIsSet(siteId, sessionUtilisateur));
			return "addWebContent";
		}
	}

	/**
	 * Record chosen secteur and return addWebContent page
	 * 
	 * @param model
	 * @param secteurId
	 * @param sessionUtilisateur
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/site/processChooseSecteurAddVoie", method = RequestMethod.GET)
	public String chooseSecteurVoie(Model model, @RequestParam(value = "secteurIdVoie") int secteurId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			RedirectAttributes redirectAttributes) {

		logger.info("Requête d'accès à l'url /site/processChooseSecteurAddVoie");
		if (sessionUtilisateur == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddVoieAttributesWhenSecteurIsSet(secteurId, sessionUtilisateur));
			return "addWebContent";
		}
	}

	/**
	 * Handle add voie request 
	 * 
	 * @param model
	 * @param voie
	 * @param result
	 * @param sessionUtilisateur
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/site/processAddVoie", method = RequestMethod.POST)
	public String addVoie(Model model, @Valid @ModelAttribute(value = "voie") Voie voie, BindingResult result,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			RedirectAttributes redirectAttributes) {
		
		logger.info("Requête d'accès à l'url /site/processAddVoie");
		if (sessionUtilisateur == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			if (result.hasErrors()) {
				model.addAllAttributes(addWebContentFormUtil.getAddVoieAttributesWhenValidationErrors(voie, sessionUtilisateur));

			} else {
				String error = webContentManager.addVoie(voie);
				model.addAllAttributes(addWebContentFormUtil.getAddVoieAttributes(voie, error, sessionUtilisateur));
			}
		}
		return "addWebContent";
	}
}
