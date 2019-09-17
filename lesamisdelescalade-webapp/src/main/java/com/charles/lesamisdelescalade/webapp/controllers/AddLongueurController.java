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
import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Voie;

@Controller
public class AddLongueurController {
	
	@Autowired
	private WebContentManager webContentManager;
	
	@RequestMapping(value = "/site/processChooseDepartementLongueur", method = RequestMethod.GET)
	public String chooseDepartementLongueur(Model model, @RequestParam(value="departementIdLongueur") int departementId) {
		
		model.addAttribute("departementIdLongueur", departementId);
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("longueur", new Longueur());
		model.addAttribute("collapseClassLongueur", "show");
		model.addAttribute("collapseAriaLongueur", true);
		return "addWebContent";
		
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
		model.addAttribute("collapseClassLongueur", "show");
		model.addAttribute("collapseAriaLongueur", true);
		return "addWebContent";
		
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
		model.addAttribute("collapseClassLongueur", "show");
		model.addAttribute("collapseAriaLongueur", true);
		return "addWebContent";
		
	}
	
	@RequestMapping(value = "/site/processChooseVoieLongueur", method = RequestMethod.GET)
	public String chooseVoieLongueur(Model model, @RequestParam(value="voieIdLongueur") int voieId) {
		
		int secteurId = webContentManager.getSecteurIdByVoieId(voieId);
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
		model.addAttribute("voieIdLongueur", voieId);
		model.addAttribute("longueur", new Longueur());
		model.addAttribute("longueurs", webContentManager.findAllLongueurByVoie(voieId));
		model.addAttribute("cotations", webContentManager.findAllCotation());
		model.addAttribute("collapseClassLongueur", "show");
		model.addAttribute("collapseAriaLongueur", true);
		return "addWebContent";
		
		
		
	}
	
	@RequestMapping(value = "/site/processAddLongueur", method = RequestMethod.POST)
	public String addLongueur(Model model, @Valid @ModelAttribute(value = "longueur") Longueur longueur, BindingResult result) {
		
		Boolean isNumberInputAlreadyUsed;
		int secteurId = webContentManager.getSecteurIdByVoieId(longueur.getVoie_id());
		int siteId = webContentManager.getSiteIdBySecteurId(secteurId);
		int departementId = webContentManager.getDepartementIdBySiteId(siteId);
		if (result.hasErrors()) {
			model.addAttribute("departements", webContentManager.findAllDepartements());
			model.addAttribute("departementIdLongueur", departementId);
			model.addAttribute("siteIdLongueur", siteId);
			model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
			model.addAttribute("site", new Site());
			model.addAttribute("secteurs", webContentManager.getAllSecteursBySite(siteId));
			model.addAttribute("secteurIdLongueur", secteurId);
			model.addAttribute("voies", webContentManager.findAllVoieBySecteur(secteurId));
			model.addAttribute("cotations", webContentManager.findAllCotation());
			model.addAttribute("collapseClassLongueur", "show");
			model.addAttribute("collapseAriaLongueur", true);
			return "addWebContent";
		}else {
			isNumberInputAlreadyUsed = webContentManager.addLongueur(longueur);
			if (!isNumberInputAlreadyUsed) {
				model.addAttribute("messageSuccessLongueur", "Longueur ajoutée avec succès");
			} else  {
				model.addAttribute("messageErrorLongueur", "Erreur - La longueur n°" + longueur.getNumero() + " est déja enregistrée sur cette voie.");
				model.addAttribute("departementIdLongueur", departementId);
				model.addAttribute("siteIdLongueur", siteId);
				model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
				model.addAttribute("secteurs", webContentManager.getAllSecteursBySite(siteId));
				model.addAttribute("secteurIdLongueur", secteurId);
				model.addAttribute("voies", webContentManager.findAllVoieBySecteur(secteurId));
				model.addAttribute("cotations", webContentManager.findAllCotation());
			} 
			
		}
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("longueur", new Longueur());
		model.addAttribute("collapseClassLongueur", "show");
		model.addAttribute("collapseAriaLongueur", true);
		return "addWebContent";
		
	}
	
	

}
