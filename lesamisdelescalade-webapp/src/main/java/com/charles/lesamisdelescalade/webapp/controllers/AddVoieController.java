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
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.beans.Voie;
import com.charles.lesamisdelescalade.webapp.utils.AddWebContentFormUtil;

@Controller
public class AddVoieController {

	@Autowired
	private WebContentManager webContentManager;
	@Autowired
	private AddWebContentFormUtil addWebContentFormUtil;

	@RequestMapping(value = "/site/processChooseDepartementAddVoie", method = RequestMethod.GET)
	public String chooseDepartementVoie(Model model, @RequestParam(value = "departementIdVoie") int departementId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			RedirectAttributes redirectAttributes) {

		if (sessionUtilisateur == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddVoieAttributesWhenDepartementIsSet(departementId, sessionUtilisateur));
			return "addWebContent";
		}
	}

	@RequestMapping(value = "/site/processChooseSiteAddVoie", method = RequestMethod.GET)
	public String chooseSiteVoie(Model model, @RequestParam(value = "siteIdVoie") int siteId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			RedirectAttributes redirectAttributes) {

		if (sessionUtilisateur == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddVoieAttributesWhenSiteIsSet(siteId, sessionUtilisateur));
			return "addWebContent";
		}
	}

	@RequestMapping(value = "/site/processChooseSecteurAddVoie", method = RequestMethod.GET)
	public String chooseSecteurVoie(Model model, @RequestParam(value = "secteurIdVoie") int secteurId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			RedirectAttributes redirectAttributes) {

		if (sessionUtilisateur == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddVoieAttributesWhenSecteurIsSet(secteurId, sessionUtilisateur));
			return "addWebContent";
		}
	}

	@RequestMapping(value = "/site/processAddVoie", method = RequestMethod.POST)
	public String addVoie(Model model, @Valid @ModelAttribute(value = "voie") Voie voie, BindingResult result,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			RedirectAttributes redirectAttributes) {

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
