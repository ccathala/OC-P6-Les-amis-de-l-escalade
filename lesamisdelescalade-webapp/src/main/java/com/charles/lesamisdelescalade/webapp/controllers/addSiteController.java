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

import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Departement;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.beans.Voie;

@Controller
public class addSiteController {

	@Autowired
	private WebContentManager webContentManager;

	@RequestMapping(value = "/site/ajouter", method = RequestMethod.GET)
	public String addSite(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur utilisateurSession) {

		model.addAttribute("departements", webContentManager.getDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());

		return "ajouter";
	}

	@RequestMapping(value = "/site/processAddSite", method = RequestMethod.POST)
	public String addSite(Model model, @Valid @ModelAttribute(value = "site") Site site, BindingResult result) {

		if (result.hasErrors()) {
			
			
			model.addAttribute("secteur", new Secteur());
			model.addAttribute("voie", new Voie());
			model.addAttribute("departements", webContentManager.getDepartements());
			return "ajouter";
			
		} else {

			if (webContentManager.addSite(site)) {
				model.addAttribute("messageSuccessSite", "Site ajouté avec succès");
			} else {
				model.addAttribute("messageErrorSite", "Erreur - Le site est déjà enregistré");
			}
		}

		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("departements", webContentManager.getDepartements());
		return "ajouter";
	}
	
	@RequestMapping(value = "/site/processChooseDepartement", method = RequestMethod.GET)
	public String chooseDepartement(Model model, @RequestParam(value="departementId") int departementId) {
		
		model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
		model.addAttribute("departements", webContentManager.getDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("departementId", departementId);
		return "ajouter";
		
	}
	
	@RequestMapping(value = "/site/processAddSecteur", method = RequestMethod.POST)
	public String addSecteur(Model model, @Valid @ModelAttribute(value = "secteur") Secteur secteur, BindingResult result) {
		
		if (result.hasErrors()) {
			model.addAttribute("departements", webContentManager.getDepartements());
			return "ajouter";
		}else {
			if (webContentManager.addSecteur(secteur)) {
				model.addAttribute("messageSuccessSecteur", "Secteur ajouté avec succès");
			} else {
				model.addAttribute("messageErrorSecteur", "Erreur - Le secteur est déjà enregistré");
			}
			
		}
		model.addAttribute("departements", webContentManager.getDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		return "ajouter";
	}
	
	@RequestMapping(value = "/site/processChooseDepartementVoie", method = RequestMethod.GET)
	public String chooseDepartementVoie(Model model, @RequestParam(value="departementIdVoie") int departementIdVoie) {
		
		model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementIdVoie));
		model.addAttribute("departements", webContentManager.getDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("departementIdVoie", departementIdVoie);
		return "ajouter";
		
	}
	
	@RequestMapping(value = "/site/processChooseSiteVoie", method = RequestMethod.GET)
	public String chooseSiteVoie(Model model, @RequestParam(value="siteIdVoie") int siteIdVoie) {
		
		int departementIdVoie = webContentManager.getDepartementIdBySiteId(siteIdVoie);
		model.addAttribute("secteurs", webContentManager.getAllSecteursBySite(siteIdVoie));
		model.addAttribute("departements", webContentManager.getDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("siteIdVoie", siteIdVoie);
		model.addAttribute("departementIdVoie",departementIdVoie);
		model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementIdVoie));
		return "ajouter";
		
	}
	
	@RequestMapping(value = "/site/processAddVoie", method = RequestMethod.POST)
	public String addVoie(Model model, @Valid @ModelAttribute(value = "voie") Voie voie, BindingResult result) {
		String error="";
		if (result.hasErrors()) {
			model.addAttribute("departements", webContentManager.getDepartements());
			return "ajouter";
		}else {
			error = webContentManager.addVoie(voie);
			if (error.contentEquals("")) {
				model.addAttribute("messageSuccessVoie", "Secteur ajouté avec succès");
			} else if (error.contentEquals("numero")) {
				model.addAttribute("messageErrorVoie", "Erreur - La voie n°" + voie.getNumero() + " est déja enregistrée sur ce site.");
			} else if (error.contentEquals("nom")) {
				model.addAttribute("messageErrorVoie", "Erreur - La voie nommée" + voie.getNom() + " est déja enregistrée sur ce site.");
			}
			
		}
		model.addAttribute("departements", webContentManager.getDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		return "ajouter";
	}

}
