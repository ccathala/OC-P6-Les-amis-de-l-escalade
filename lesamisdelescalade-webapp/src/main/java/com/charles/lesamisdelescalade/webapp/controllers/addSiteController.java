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
import com.charles.lesamisdelescalade.model.beans.Longueur;
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

		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("longueur", new Longueur());

		return "ajouter";
	}
	
	/*
	=================================================================================================================================		
															ADD SITE	
	=================================================================================================================================
	*/

	@RequestMapping(value = "/site/processAddSite", method = RequestMethod.POST)
	public String addSite(Model model, @Valid @ModelAttribute(value = "site") Site site, BindingResult result) {

		if (result.hasErrors()) {
			
			model.addAttribute("departements", webContentManager.findAllDepartements());
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
		model.addAttribute("longueur", new Longueur());
		model.addAttribute("departements", webContentManager.findAllDepartements());
		return "ajouter";
	}
	
	/*
	=================================================================================================================================		
															ADD SECTOR	
	=================================================================================================================================
	*/
	
	@RequestMapping(value = "/site/processChooseDepartement", method = RequestMethod.GET)
	public String chooseDepartement(Model model, @RequestParam(value="departementIdSecteur") int departementId) {
		
		model.addAttribute("departementIdSecteur", departementId);
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("longueur", new Longueur());
		
		return "ajouter";
		
	}
	
	@RequestMapping(value = "/site/processAddSecteur", method = RequestMethod.POST)
	public String addSecteur(Model model, @Valid @ModelAttribute(value = "secteur") Secteur secteur, BindingResult result) {
		
		if (result.hasErrors()) {
			int departementId = webContentManager.getDepartementIdBySiteId(secteur.getSite_id());
			model.addAttribute("departements", webContentManager.findAllDepartements());
			model.addAttribute("departementId", departementId );
			model.addAttribute("site", new Site());
			model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
			return "ajouter";
		}else {
			if (webContentManager.addSecteur(secteur)) {
				model.addAttribute("messageSuccessSecteur", "Secteur ajouté avec succès");
			} else {
				model.addAttribute("messageErrorSecteur", "Erreur - Le secteur est déjà enregistré");
			}
			
		}
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("longueur", new Longueur());
		return "ajouter";
	}
	
	/*
	=================================================================================================================================		
															ADD WAY	
	=================================================================================================================================
	*/
	
	@RequestMapping(value = "/site/processChooseDepartementVoie", method = RequestMethod.GET)
	public String chooseDepartementVoie(Model model, @RequestParam(value="departementIdVoie") int departementId) {
		
		model.addAttribute("departementIdVoie", departementId);
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("longueur", new Longueur());
		
		return "ajouter";
		
	}
	
	@RequestMapping(value = "/site/processChooseSiteVoie", method = RequestMethod.GET)
	public String chooseSiteVoie(Model model, @RequestParam(value="siteIdVoie") int siteId) {
		
		int departementId = webContentManager.getDepartementIdBySiteId(siteId);
		model.addAttribute("secteurs", webContentManager.getAllSecteursBySite(siteId));
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("siteIdVoie", siteId);
		model.addAttribute("departementIdVoie",departementId);
		model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
		model.addAttribute("longueur", new Longueur());
		return "ajouter";
		
	}
	
	@RequestMapping(value = "/site/processAddVoie", method = RequestMethod.POST)
	public String addVoie(Model model, @Valid @ModelAttribute(value = "voie") Voie voie, BindingResult result) {
		String error="";
		if (result.hasErrors()) {
			int siteId = webContentManager.getSiteIdBySecteurId(voie.getSecteur_id());
			int departementId = webContentManager.getDepartementIdBySiteId(siteId);
			model.addAttribute("departements", webContentManager.findAllDepartements());
			model.addAttribute("departementIdVoie", departementId);
			model.addAttribute("siteIdVoie", siteId);
			model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
			model.addAttribute("site", new Site());
			model.addAttribute("secteurs", webContentManager.getAllSecteursBySite(siteId));
			
			
			return "ajouter";
		}else {
			error = webContentManager.addVoie(voie);
			if (error.contentEquals("")) {
				model.addAttribute("messageSuccessVoie", "Voie ajoutée avec succès");
			} else if (error.contentEquals("numero")) {
				model.addAttribute("messageErrorVoie", "Erreur - La voie n°" + voie.getNumero() + " est déja enregistrée sur ce site.");
			} else if (error.contentEquals("nom")) {
				model.addAttribute("messageErrorVoie", "Erreur - La voie nommée " + voie.getNom() + " est déja enregistrée sur ce site.");
			}
			
		}
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("longueur", new Longueur());
		return "ajouter";
	}
	
	/*
	=================================================================================================================================		
															ADD LENGTH	
	=================================================================================================================================
	*/

	
	@RequestMapping(value = "/site/processChooseDepartementLongueur", method = RequestMethod.GET)
	public String chooseDepartementLongueur(Model model, @RequestParam(value="departementIdLongueur") int departementId) {
		
		model.addAttribute("departementIdLongueur", departementId);
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("longueur", new Longueur());
		return "ajouter";
		
	}
	
	@RequestMapping(value = "/site/processChooseSiteLongueur", method = RequestMethod.GET)
	public String chooseSiteLongueur(Model model, @RequestParam(value="siteIdLongueur") int siteId) {
		
		int departementId = webContentManager.getDepartementIdBySiteId(siteId);
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("departementIdLongueur",departementId);
		model.addAttribute("site", new Site());
		model.addAttribute("siteIdLongueur", siteId);
		model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
		model.addAttribute("secteurs", webContentManager.getAllSecteursBySite(siteId));
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("longueur", new Longueur());
		return "ajouter";
		
	}
	
	@RequestMapping(value = "/site/processChooseSecteurLongueur", method = RequestMethod.GET)
	public String chooseSecteurLongueur(Model model, @RequestParam(value="secteurIdLongueur") int secteurId) {
		
		int siteId = webContentManager.getSiteIdBySecteurId(secteurId);
		int departementId = webContentManager.getDepartementIdBySiteId(siteId);
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("departementIdLongueur",departementId);
		model.addAttribute("site", new Site());
		model.addAttribute("siteIdLongueur", siteId);
		model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
		model.addAttribute("secteurs", webContentManager.getAllSecteursBySite(siteId));
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("secteurIdLongueur", secteurId);
		model.addAttribute("voie", new Voie());
		model.addAttribute("voies", webContentManager.findAllVoieBySecteur(secteurId));
		model.addAttribute("longueur", new Longueur());
		model.addAttribute("cotations", webContentManager.findAllCotation());
		return "ajouter";
		
	}
	
	@RequestMapping(value = "/site/processAddLongueur", method = RequestMethod.POST)
	public String addLongueur(Model model, @Valid @ModelAttribute(value = "longueur") Longueur longueur, BindingResult result) {
		
		Boolean isNumberInputAlreadyUsed;
		if (result.hasErrors()) {
			int secteurId = webContentManager.getSecteurIdByVoieId(longueur.getVoie_id());
			int siteId = webContentManager.getSiteIdBySecteurId(secteurId);
			int departementId = webContentManager.getDepartementIdBySiteId(siteId);
			model.addAttribute("departements", webContentManager.findAllDepartements());
			model.addAttribute("departementIdLongueur", departementId);
			model.addAttribute("siteIdLongueur", siteId);
			model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
			model.addAttribute("site", new Site());
			model.addAttribute("secteurs", webContentManager.getAllSecteursBySite(siteId));
			model.addAttribute("secteurIdLongueur", secteurId);
			model.addAttribute("voies", webContentManager.findAllVoieBySecteur(secteurId));
			model.addAttribute("cotations", webContentManager.findAllCotation());
			
			
			
			return "ajouter";
		}else {
			isNumberInputAlreadyUsed = webContentManager.addLongueur(longueur);
			if (!isNumberInputAlreadyUsed) {
				model.addAttribute("messageSuccessLongueur", "Longueur ajoutée avec succès");
			} else  {
				model.addAttribute("messageErrorLongueur", "Erreur - La longueur n°" + longueur.getNumero() + " est déja enregistrée sur cette voie.");
				
			} 
			
		}
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("longueur", new Longueur());
		return "ajouter";
		
	}
	
	
	
}
