package com.charles.lesamisdelescalade.webapp.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Voie;

@Controller
public class AddSiteController {
	
	@Autowired
	private WebContentManager webContentManager;
	
	@RequestMapping(value = "/site/processAddSite", method = RequestMethod.POST)
	public String addSite(Model model, @Valid @ModelAttribute(value = "site") Site site, BindingResult result) {

		if (result.hasErrors()) {
			
			model.addAttribute("departements", webContentManager.findAllDepartements());
			model.addAttribute("departementIdSite", site.getDepartement_id());
			model.addAttribute("collapseClassSite", "show");
			model.addAttribute("collapseAriaSite", true);
			
			if (result.getFieldErrorCount("departement_id")>0) {
				model.addAttribute("messageErrorSite", "Erreur - Vous devez sélectionner un département.");
			}
			return "addWebContent";
			
		} else {

			if (webContentManager.addSite(site)) {
				model.addAttribute("messageSuccessSite", "Site ajouté avec succès");
			} else {
				model.addAttribute("messageErrorSite", "Erreur - Le site est déjà enregistré");
				model.addAttribute("departementIdSite", site.getDepartement_id());
			}
		}

		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("longueur", new Longueur());
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("collapseClassSite", "show");
		model.addAttribute("collapseAriaSite", true);
		return "addWebContent";
	}

}
