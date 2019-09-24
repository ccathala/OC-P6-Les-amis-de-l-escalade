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
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.webapp.utils.AddWebContentFormUtil;

@Controller
public class AddSecteurController {

	@Autowired
	private WebContentManager webContentManager;

	@Autowired
	private AddWebContentFormUtil addWebContentFormUtil;

	@RequestMapping(value = "/site/processChooseDepartementAddSecteur", method = RequestMethod.GET)
	public String chooseDepartement(Model model, @RequestParam(value = "departementIdSecteur") int departementId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			RedirectAttributes redirectAttributes) {

		if (sessionUtilisateur == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddSecteurAttributesWhenDepartementIsSet(departementId, sessionUtilisateur));
			return "addWebContent";
		}
	}

	@RequestMapping(value = "/site/processAddSecteur", method = RequestMethod.POST)
	public String addSecteur(Model model, @Valid @ModelAttribute(value = "secteur") Secteur secteur,
			BindingResult result,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			RedirectAttributes redirectAttributes) {

		if (sessionUtilisateur == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			if (result.hasErrors()) {
				model.addAllAttributes(
						addWebContentFormUtil.getAddSecteurAttributesWhenValidationErrors(secteur, result, sessionUtilisateur));
			} else {
				Boolean secteurAddedWithSuccess = webContentManager.addSecteur(secteur);
				model.addAllAttributes(addWebContentFormUtil.getAddSecteurAttributes(secteur, secteurAddedWithSuccess, sessionUtilisateur));
			}
			return "addWebContent";
		}
	}
}
