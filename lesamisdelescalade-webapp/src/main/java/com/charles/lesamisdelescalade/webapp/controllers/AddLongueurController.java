package com.charles.lesamisdelescalade.webapp.controllers;

import javax.validation.Valid;
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
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.webapp.utils.AddWebContentFormUtil;

@Controller
public class AddLongueurController {

	@Autowired
	private WebContentManager webContentManager;
	@Autowired
	private AddWebContentFormUtil addWebContentFormUtil;

	@RequestMapping(value = "/site/processChooseDepartementAddLongueur", method = RequestMethod.GET)
	public String chooseDepartementLongueur(Model model,
			@RequestParam(value = "departementIdLongueur") int departementId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur utilisateurSession,
			RedirectAttributes redirectAttributes) {

		if (utilisateurSession == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributesWhenDepartementIsSet(departementId, utilisateurSession));
			return "addWebContent";
		}
	}

	@RequestMapping(value = "/site/processChooseSiteAddLongueur", method = RequestMethod.GET)
	public String chooseSiteLongueur(Model model, @RequestParam(value = "siteIdLongueur") int siteId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur utilisateurSession,
			RedirectAttributes redirectAttributes) {

		if (utilisateurSession == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributesWhenSiteIsSet(siteId, utilisateurSession));
			return "addWebContent";
		}
	}

	@RequestMapping(value = "/site/processChooseSecteurAddLongueur", method = RequestMethod.GET)
	public String chooseSecteurLongueur(Model model, @RequestParam(value = "secteurIdLongueur") int secteurId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur utilisateurSession,
			RedirectAttributes redirectAttributes) {

		if (utilisateurSession == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributesWhenSecteurIsSet(secteurId, utilisateurSession));
			return "addWebContent";
		}
	}

	@RequestMapping(value = "/site/processChooseVoieAddLongueur", method = RequestMethod.GET)
	public String chooseVoieLongueur(Model model, @RequestParam(value = "voieIdLongueur") int voieId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur utilisateurSession,
			RedirectAttributes redirectAttributes) {

		if (utilisateurSession == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributesWhenVoieIsSet(voieId, utilisateurSession));
			return "addWebContent";
		}
	}

	@RequestMapping(value = "/site/processAddLongueur", method = RequestMethod.POST)
	public String addLongueur(Model model, @Valid @ModelAttribute(value = "longueur") Longueur longueur,
			BindingResult result,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur utilisateurSession,
			RedirectAttributes redirectAttributes) {

		if (utilisateurSession == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			int secteurId = webContentManager.getSecteurIdByVoieId(longueur.getVoie_id());
			int siteId = webContentManager.getSiteIdBySecteurId(secteurId);
			int departementId = webContentManager.getDepartementIdBySiteId(siteId);
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
