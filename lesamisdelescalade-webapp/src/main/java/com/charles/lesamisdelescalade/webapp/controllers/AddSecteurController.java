package com.charles.lesamisdelescalade.webapp.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Voie;

@Controller
public class AddSecteurController {

	@Autowired
	private WebContentManager webContentManager;

	@RequestMapping(value = "/site/processChooseDepartement", method = RequestMethod.GET)
	public String chooseDepartement(Model model, @RequestParam(value = "departementIdSecteur") int departementId) {

		if(departementId == 0) {
			model.addAttribute("messageErrorSecteur", "Erreur - Aucun département n'a été sélectionné");
		}else {
			model.addAttribute("departementIdSecteur", departementId);
			model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
		}
		
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("longueur", new Longueur());
		model.addAttribute("collapseClassSecteur", "show");
		model.addAttribute("collapseAriaSecteur", true);
		return "addWebContent";

	}

	@RequestMapping(value = "/site/processAddSecteur", method = RequestMethod.POST)
	public String addSecteur(Model model, @Valid @ModelAttribute(value = "secteur") Secteur secteur,
			BindingResult result) {
			
		if (result.hasErrors()) {
			
			if(result.getFieldErrorCount("site_id")>0) {
				model.addAttribute("messageErrorSecteur", "Erreur - Auncun site n'a été sélectionné");
			}else {
				int departementId = webContentManager.getDepartementIdBySiteId(secteur.getSite_id());
				model.addAttribute("departementIdSecteur", departementId);
				model.addAttribute("siteIdSecteur", secteur.getSite_id());
				model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
			}
			
			model.addAttribute("departements", webContentManager.findAllDepartements());
			model.addAttribute("site", new Site());
			model.addAttribute("collapseClassSecteur", "show");
			model.addAttribute("collapseAriaSecteur", true);
			return "addWebContent";
		} else {
			if (webContentManager.addSecteur(secteur)) {
				model.addAttribute("messageSuccessSecteur", "Secteur ajouté avec succès");
			} else {
				int departementId = webContentManager.getDepartementIdBySiteId(secteur.getSite_id());
				model.addAttribute("departementIdSecteur", departementId);
				model.addAttribute("siteIdSecteur", secteur.getSite_id());
				model.addAttribute("messageErrorSecteur", "Erreur - Le secteur est déjà enregistré");
			}

		}
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("longueur", new Longueur());
		model.addAttribute("collapseClassSecteur", "show");
		model.addAttribute("collapseAriaSecteur", true);
		return "addWebContent";

	}
}
