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
public class AddVoieController {
	
	@Autowired
	private WebContentManager webContentManager;
	
	
	@RequestMapping(value = "/site/processChooseDepartementVoie", method = RequestMethod.GET)
	public String chooseDepartementVoie(Model model, @RequestParam(value="departementIdVoie") int departementId) {
		
		if(departementId == 0) {
			model.addAttribute("messageErrorVoie", "Erreur - Aucun département n'a été sélectionné");
		}else {
			model.addAttribute("departementIdVoie", departementId);
			model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
		}
		
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("longueur", new Longueur());
		model.addAttribute("collapseClassVoie", "show");
		model.addAttribute("collapseAriaVoie", true);
		return "addWebContent";
		
	}
	
	@RequestMapping(value = "/site/processChooseSiteVoie", method = RequestMethod.GET)
	public String chooseSiteVoie(Model model, @RequestParam(value="siteIdVoie") int siteId) {
		
		if(siteId ==0) {
			model.addAttribute("messageErrorVoie", "Erreur - Aucun site n'a été sélectionné");
		}else {
			int departementId = webContentManager.getDepartementIdBySiteId(siteId);
			model.addAttribute("secteurs", webContentManager.getAllSecteursBySite(siteId));
			model.addAttribute("departementIdVoie",departementId);
			model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
			model.addAttribute("siteIdVoie", siteId);
		}

		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		
		
		
		model.addAttribute("longueur", new Longueur());
		model.addAttribute("collapseClassVoie", "show");
		model.addAttribute("collapseAriaVoie", true);
		return "addWebContent";
		
	}
	
	@RequestMapping(value = "/site/processChooseSecteurVoie", method = RequestMethod.GET)
	public String chooseSecteurVoie(Model model, @RequestParam(value="secteurIdVoie") int secteurId) {
		
		if(secteurId == 0) {
			model.addAttribute("messageErrorVoie", "Erreur - Aucun secteur n'a été sélectionné");
		}else {
			int siteId = webContentManager.getSiteIdBySecteurId(secteurId);
			int departementId = webContentManager.getDepartementIdBySiteId(siteId);
			model.addAttribute("departementIdVoie",departementId);
			model.addAttribute("siteIdVoie", siteId);
			model.addAttribute("sites", webContentManager.getAllSitesByDepartement(departementId));
			model.addAttribute("secteurs", webContentManager.getAllSecteursBySite(siteId));
			model.addAttribute("secteurIdVoie", secteurId);
			model.addAttribute("voies", webContentManager.findAllVoieBySecteur(secteurId));
			
		}
		
		model.addAttribute("departements", webContentManager.findAllDepartements());
		model.addAttribute("site", new Site());
		model.addAttribute("secteur", new Secteur());
		model.addAttribute("voie", new Voie());
		model.addAttribute("longueur", new Longueur());
		model.addAttribute("collapseClassVoie", "show");
		model.addAttribute("collapseAriaVoie", true);
		return "addWebContent";
		
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
			model.addAttribute("secteurIdVoie", webContentManager.getSecteurIdByVoieId(voie.getSecteur_id()));
			model.addAttribute("voies", webContentManager.findAllVoieBySecteur(voie.getSecteur_id()));
			model.addAttribute("collapseClassVoie", "show");
			model.addAttribute("collapseAriaVoie", true);
			
			
			return "addWebContent";
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
		model.addAttribute("collapseClassVoie", "show");
		model.addAttribute("collapseAriaVoie", true);
		return "addWebContent";
	}

}
