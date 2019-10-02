package com.charles.lesamisdelescalade.webapp.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Topo;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;

@Controller
@Validated
public class AddTopoController {

	@Autowired
	private WebContentManager webContentManager;

	@RequestMapping(value = "/addTopo", method = RequestMethod.GET)
	public String displayAddTopoPage(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			RedirectAttributes redirectAttributes) {

		if (sessionUtilisateur == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {
			model.addAttribute("departements", webContentManager.findAllDepartement());
			model.addAttribute("sessionUtilisateur", sessionUtilisateur);
			return "addTopo";
		}
	}

	@RequestMapping(value = "processSelectDepartementAtAddTopoPage", method = RequestMethod.GET)
	public String processSelectDepartementAtAddTopoPage(
			@RequestParam(value = "departementId") int departementId,
			RedirectAttributes redirectAttributes) {
		if(departementId == 0) {
			return "redirect:/addTopo";
		}else {
			redirectAttributes.addFlashAttribute("newTopo", new Topo());
			redirectAttributes.addFlashAttribute("departementId", departementId);
			redirectAttributes.addFlashAttribute("sites", webContentManager.findAllSiteByDepartement(departementId));
			return "redirect:/addTopo";
		}

	}

	@RequestMapping(value = "processAddTopo", method = RequestMethod.POST)
	public String processAddTopo(Model model, @Valid @ModelAttribute(value="newTopo") Topo newTopo, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			
			if(result.getFieldErrorCount("site_id")>0) {
				model.addAttribute("messageErrorTopo", "Erreur - Aucun site sélectionné.");
			}else {
				int departementId = webContentManager.getDepartementIdBySiteId(newTopo.getSite_id());
				model.addAttribute("departementId", departementId);
				model.addAttribute("sites", webContentManager.findAllSiteByDepartement(departementId));
				model.addAttribute("siteId", newTopo.getSite_id());
			}
			model.addAttribute("departements", webContentManager.findAllDepartement());
			return "addTopo";
		} else {
			if( webContentManager.addTopo(newTopo)) {
				redirectAttributes.addFlashAttribute("messageErrorTopo", "Erreur - Ce topo est déjà enregistré.");
			}else {
				redirectAttributes.addFlashAttribute("messageSuccessTopo", "Topo ajouté avec succès.");
			}
			
			return "redirect:/addTopo";
		}

	}
}
