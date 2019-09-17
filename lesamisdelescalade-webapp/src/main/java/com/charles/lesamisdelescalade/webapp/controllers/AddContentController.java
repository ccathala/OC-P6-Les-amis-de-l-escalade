package com.charles.lesamisdelescalade.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.beans.Voie;

@Controller
public class AddContentController {

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

		return "addWebContent";
	}

	/*
	 * =============================================================================
	 * ==================================================== ADD SITE
	 * =============================================================================
	 * ====================================================
	 * 
	 * 
	 * @RequestMapping(value = "/site/processAddSite", method = RequestMethod.POST)
	 * public String addSite(Model model, @Valid @ModelAttribute(value = "site")
	 * Site site, BindingResult result) {
	 * 
	 * if (result.hasErrors()) {
	 * 
	 * model.addAttribute("departements", webContentManager.findAllDepartements());
	 * model.addAttribute("collapseClassSite", "show");
	 * model.addAttribute("collapseAriaSite", true); return "ajouter";
	 * 
	 * } else {
	 * 
	 * if (webContentManager.addSite(site)) {
	 * model.addAttribute("messageSuccessSite", "Site ajouté avec succès"); } else {
	 * model.addAttribute("messageErrorSite",
	 * "Erreur - Le site est déjà enregistré"); } }
	 * 
	 * model.addAttribute("site", new Site()); model.addAttribute("secteur", new
	 * Secteur()); model.addAttribute("voie", new Voie());
	 * model.addAttribute("longueur", new Longueur());
	 * model.addAttribute("departements", webContentManager.findAllDepartements());
	 * model.addAttribute("collapseClassSite", "show");
	 * model.addAttribute("collapseAriaSite", true); return "ajouter"; }
	 * 
	 * 
	 * =============================================================================
	 * ==================================================== ADD SECTOR
	 * =============================================================================
	 * ====================================================
	 * 
	 * 
	 * @RequestMapping(value = "/site/processChooseDepartement", method =
	 * RequestMethod.GET) public String chooseDepartement(Model
	 * model, @RequestParam(value="departementIdSecteur") int departementId) {
	 * 
	 * model.addAttribute("departementIdSecteur", departementId);
	 * model.addAttribute("departements", webContentManager.findAllDepartements());
	 * model.addAttribute("site", new Site()); model.addAttribute("sites",
	 * webContentManager.getAllSitesByDepartement(departementId));
	 * model.addAttribute("secteur", new Secteur()); model.addAttribute("voie", new
	 * Voie()); model.addAttribute("longueur", new Longueur());
	 * model.addAttribute("collapseClassSecteur", "show");
	 * model.addAttribute("collapseAriaSecteur", true); return "ajouter";
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/site/processAddSecteur", method =
	 * RequestMethod.POST) public String addSecteur(Model
	 * model, @Valid @ModelAttribute(value = "secteur") Secteur secteur,
	 * BindingResult result) {
	 * 
	 * if (result.hasErrors()) { int departementId =
	 * webContentManager.getDepartementIdBySiteId(secteur.getSite_id());
	 * model.addAttribute("departements", webContentManager.findAllDepartements());
	 * model.addAttribute("departementId", departementId );
	 * model.addAttribute("site", new Site()); model.addAttribute("sites",
	 * webContentManager.getAllSitesByDepartement(departementId));
	 * model.addAttribute("collapseClassSecteur", "show");
	 * model.addAttribute("collapseAriaSecteur", true); return "ajouter"; }else { if
	 * (webContentManager.addSecteur(secteur)) {
	 * model.addAttribute("messageSuccessSecteur", "Secteur ajouté avec succès"); }
	 * else { model.addAttribute("messageErrorSecteur",
	 * "Erreur - Le secteur est déjà enregistré"); }
	 * 
	 * } model.addAttribute("departements",
	 * webContentManager.findAllDepartements()); model.addAttribute("site", new
	 * Site()); model.addAttribute("secteur", new Secteur());
	 * model.addAttribute("voie", new Voie()); model.addAttribute("longueur", new
	 * Longueur()); model.addAttribute("collapseClassSecteur", "show");
	 * model.addAttribute("collapseAriaSecteur", true); return "ajouter"; }
	 * 
	 * 
	 * =============================================================================
	 * ==================================================== ADD WAY
	 * =============================================================================
	 * ====================================================
	 * 
	 * 
	 * @RequestMapping(value = "/site/processChooseDepartementVoie", method =
	 * RequestMethod.GET) public String chooseDepartementVoie(Model
	 * model, @RequestParam(value="departementIdVoie") int departementId) {
	 * 
	 * model.addAttribute("departementIdVoie", departementId);
	 * model.addAttribute("departements", webContentManager.findAllDepartements());
	 * model.addAttribute("site", new Site()); model.addAttribute("sites",
	 * webContentManager.getAllSitesByDepartement(departementId));
	 * model.addAttribute("secteur", new Secteur()); model.addAttribute("voie", new
	 * Voie()); model.addAttribute("longueur", new Longueur());
	 * model.addAttribute("collapseClassVoie", "show");
	 * model.addAttribute("collapseAriaVoie", true);
	 * 
	 * return "ajouter";
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/site/processChooseSiteVoie", method =
	 * RequestMethod.GET) public String chooseSiteVoie(Model
	 * model, @RequestParam(value="siteIdVoie") int siteId) {
	 * 
	 * int departementId = webContentManager.getDepartementIdBySiteId(siteId);
	 * model.addAttribute("secteurs",
	 * webContentManager.getAllSecteursBySite(siteId));
	 * model.addAttribute("departements", webContentManager.findAllDepartements());
	 * model.addAttribute("site", new Site()); model.addAttribute("secteur", new
	 * Secteur()); model.addAttribute("voie", new Voie());
	 * model.addAttribute("siteIdVoie", siteId);
	 * model.addAttribute("departementIdVoie",departementId);
	 * model.addAttribute("sites",
	 * webContentManager.getAllSitesByDepartement(departementId));
	 * model.addAttribute("longueur", new Longueur());
	 * model.addAttribute("collapseClassVoie", "show");
	 * model.addAttribute("collapseAriaVoie", true); return "ajouter";
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/site/processChooseSecteurVoie", method =
	 * RequestMethod.GET) public String chooseSecteurVoie(Model
	 * model, @RequestParam(value="secteurIdVoie") int secteurId) {
	 * 
	 * int siteId = webContentManager.getSiteIdBySecteurId(secteurId); int
	 * departementId = webContentManager.getDepartementIdBySiteId(siteId);
	 * model.addAttribute("departements", webContentManager.findAllDepartements());
	 * model.addAttribute("departementIdVoie",departementId);
	 * model.addAttribute("site", new Site()); model.addAttribute("siteIdVoie",
	 * siteId); model.addAttribute("sites",
	 * webContentManager.getAllSitesByDepartement(departementId));
	 * model.addAttribute("secteurs",
	 * webContentManager.getAllSecteursBySite(siteId));
	 * model.addAttribute("secteur", new Secteur());
	 * model.addAttribute("secteurIdVoie", secteurId); model.addAttribute("voie",
	 * new Voie()); model.addAttribute("voies",
	 * webContentManager.findAllVoieBySecteur(secteurId));
	 * model.addAttribute("longueur", new Longueur());
	 * model.addAttribute("collapseClassVoie", "show");
	 * model.addAttribute("collapseAriaVoie", true); return "ajouter";
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/site/processAddVoie", method = RequestMethod.POST)
	 * public String addVoie(Model model, @Valid @ModelAttribute(value = "voie")
	 * Voie voie, BindingResult result) { String error=""; if (result.hasErrors()) {
	 * int siteId = webContentManager.getSiteIdBySecteurId(voie.getSecteur_id());
	 * int departementId = webContentManager.getDepartementIdBySiteId(siteId);
	 * model.addAttribute("departements", webContentManager.findAllDepartements());
	 * model.addAttribute("departementIdVoie", departementId);
	 * model.addAttribute("siteIdVoie", siteId); model.addAttribute("sites",
	 * webContentManager.getAllSitesByDepartement(departementId));
	 * model.addAttribute("site", new Site()); model.addAttribute("secteurs",
	 * webContentManager.getAllSecteursBySite(siteId));
	 * model.addAttribute("collapseClassVoie", "show");
	 * model.addAttribute("collapseAriaVoie", true);
	 * 
	 * 
	 * return "ajouter"; }else { error = webContentManager.addVoie(voie); if
	 * (error.contentEquals("")) { model.addAttribute("messageSuccessVoie",
	 * "Voie ajoutée avec succès"); } else if (error.contentEquals("numero")) {
	 * model.addAttribute("messageErrorVoie", "Erreur - La voie n°" +
	 * voie.getNumero() + " est déja enregistrée sur ce site."); } else if
	 * (error.contentEquals("nom")) { model.addAttribute("messageErrorVoie",
	 * "Erreur - La voie nommée " + voie.getNom() +
	 * " est déja enregistrée sur ce site."); }
	 * 
	 * } model.addAttribute("departements",
	 * webContentManager.findAllDepartements()); model.addAttribute("site", new
	 * Site()); model.addAttribute("secteur", new Secteur());
	 * model.addAttribute("voie", new Voie()); model.addAttribute("longueur", new
	 * Longueur()); model.addAttribute("collapseClassVoie", "show");
	 * model.addAttribute("collapseAriaVoie", true); return "ajouter"; }
	 * 
	 * 
	 * =============================================================================
	 * ==================================================== ADD LENGTH
	 * =============================================================================
	 * ====================================================
	 * 
	 * 
	 * 
	 * @RequestMapping(value = "/site/processChooseDepartementLongueur", method =
	 * RequestMethod.GET) public String chooseDepartementLongueur(Model
	 * model, @RequestParam(value="departementIdLongueur") int departementId) {
	 * 
	 * model.addAttribute("departementIdLongueur", departementId);
	 * model.addAttribute("departements", webContentManager.findAllDepartements());
	 * model.addAttribute("site", new Site()); model.addAttribute("sites",
	 * webContentManager.getAllSitesByDepartement(departementId));
	 * model.addAttribute("secteur", new Secteur()); model.addAttribute("voie", new
	 * Voie()); model.addAttribute("longueur", new Longueur());
	 * model.addAttribute("collapseClassLongueur", "show");
	 * model.addAttribute("collapseAriaLongueur", true); return "ajouter";
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/site/processChooseSiteLongueur", method =
	 * RequestMethod.GET) public String chooseSiteLongueur(Model
	 * model, @RequestParam(value="siteIdLongueur") int siteId) {
	 * 
	 * int departementId = webContentManager.getDepartementIdBySiteId(siteId);
	 * model.addAttribute("departements", webContentManager.findAllDepartements());
	 * model.addAttribute("departementIdLongueur",departementId);
	 * model.addAttribute("site", new Site()); model.addAttribute("siteIdLongueur",
	 * siteId); model.addAttribute("sites",
	 * webContentManager.getAllSitesByDepartement(departementId));
	 * model.addAttribute("secteurs",
	 * webContentManager.getAllSecteursBySite(siteId));
	 * model.addAttribute("secteur", new Secteur()); model.addAttribute("voie", new
	 * Voie()); model.addAttribute("longueur", new Longueur());
	 * model.addAttribute("collapseClassLongueur", "show");
	 * model.addAttribute("collapseAriaLongueur", true); return "ajouter";
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/site/processChooseSecteurLongueur", method =
	 * RequestMethod.GET) public String chooseSecteurLongueur(Model
	 * model, @RequestParam(value="secteurIdLongueur") int secteurId) {
	 * 
	 * int siteId = webContentManager.getSiteIdBySecteurId(secteurId); int
	 * departementId = webContentManager.getDepartementIdBySiteId(siteId);
	 * model.addAttribute("departements", webContentManager.findAllDepartements());
	 * model.addAttribute("departementIdLongueur",departementId);
	 * model.addAttribute("site", new Site()); model.addAttribute("siteIdLongueur",
	 * siteId); model.addAttribute("sites",
	 * webContentManager.getAllSitesByDepartement(departementId));
	 * model.addAttribute("secteurs",
	 * webContentManager.getAllSecteursBySite(siteId));
	 * model.addAttribute("secteur", new Secteur());
	 * model.addAttribute("secteurIdLongueur", secteurId);
	 * model.addAttribute("voie", new Voie()); model.addAttribute("voies",
	 * webContentManager.findAllVoieBySecteur(secteurId));
	 * model.addAttribute("longueur", new Longueur());
	 * model.addAttribute("cotations", webContentManager.findAllCotation());
	 * model.addAttribute("collapseClassLongueur", "show");
	 * model.addAttribute("collapseAriaLongueur", true); return "ajouter";
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/site/processChooseVoieLongueur", method =
	 * RequestMethod.GET) public String chooseVoieLongueur(Model
	 * model, @RequestParam(value="voieIdLongueur") int voieId) {
	 * 
	 * int secteurId = webContentManager.getSecteurIdByVoieId(voieId); int siteId =
	 * webContentManager.getSiteIdBySecteurId(secteurId); int departementId =
	 * webContentManager.getDepartementIdBySiteId(siteId);
	 * model.addAttribute("departements", webContentManager.findAllDepartements());
	 * model.addAttribute("departementIdLongueur",departementId);
	 * model.addAttribute("site", new Site()); model.addAttribute("siteIdLongueur",
	 * siteId); model.addAttribute("sites",
	 * webContentManager.getAllSitesByDepartement(departementId));
	 * model.addAttribute("secteurs",
	 * webContentManager.getAllSecteursBySite(siteId));
	 * model.addAttribute("secteur", new Secteur());
	 * model.addAttribute("secteurIdLongueur", secteurId);
	 * model.addAttribute("voie", new Voie()); model.addAttribute("voies",
	 * webContentManager.findAllVoieBySecteur(secteurId));
	 * model.addAttribute("voieIdLongueur", voieId); model.addAttribute("longueur",
	 * new Longueur()); model.addAttribute("longueurs",
	 * webContentManager.findAllLongueurByVoie(voieId));
	 * model.addAttribute("cotations", webContentManager.findAllCotation());
	 * model.addAttribute("collapseClassLongueur", "show");
	 * model.addAttribute("collapseAriaLongueur", true); return "ajouter";
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/site/processAddLongueur", method =
	 * RequestMethod.POST) public String addLongueur(Model
	 * model, @Valid @ModelAttribute(value = "longueur") Longueur longueur,
	 * BindingResult result) {
	 * 
	 * Boolean isNumberInputAlreadyUsed; int secteurId =
	 * webContentManager.getSecteurIdByVoieId(longueur.getVoie_id()); int siteId =
	 * webContentManager.getSiteIdBySecteurId(secteurId); int departementId =
	 * webContentManager.getDepartementIdBySiteId(siteId); if (result.hasErrors()) {
	 * model.addAttribute("departements", webContentManager.findAllDepartements());
	 * model.addAttribute("departementIdLongueur", departementId);
	 * model.addAttribute("siteIdLongueur", siteId); model.addAttribute("sites",
	 * webContentManager.getAllSitesByDepartement(departementId));
	 * model.addAttribute("site", new Site()); model.addAttribute("secteurs",
	 * webContentManager.getAllSecteursBySite(siteId));
	 * model.addAttribute("secteurIdLongueur", secteurId);
	 * model.addAttribute("voies",
	 * webContentManager.findAllVoieBySecteur(secteurId));
	 * model.addAttribute("cotations", webContentManager.findAllCotation());
	 * model.addAttribute("collapseClassLongueur", "show");
	 * model.addAttribute("collapseAriaLongueur", true); return "ajouter"; }else {
	 * isNumberInputAlreadyUsed = webContentManager.addLongueur(longueur); if
	 * (!isNumberInputAlreadyUsed) { model.addAttribute("messageSuccessLongueur",
	 * "Longueur ajoutée avec succès"); } else {
	 * model.addAttribute("messageErrorLongueur", "Erreur - La longueur n°" +
	 * longueur.getNumero() + " est déja enregistrée sur cette voie.");
	 * model.addAttribute("departementIdLongueur", departementId);
	 * model.addAttribute("siteIdLongueur", siteId); model.addAttribute("sites",
	 * webContentManager.getAllSitesByDepartement(departementId));
	 * model.addAttribute("secteurs",
	 * webContentManager.getAllSecteursBySite(siteId));
	 * model.addAttribute("secteurIdLongueur", secteurId);
	 * model.addAttribute("voies",
	 * webContentManager.findAllVoieBySecteur(secteurId));
	 * model.addAttribute("cotations", webContentManager.findAllCotation()); }
	 * 
	 * } model.addAttribute("departements",
	 * webContentManager.findAllDepartements()); model.addAttribute("site", new
	 * Site()); model.addAttribute("secteur", new Secteur());
	 * model.addAttribute("voie", new Voie()); model.addAttribute("longueur", new
	 * Longueur()); model.addAttribute("collapseClassLongueur", "show");
	 * model.addAttribute("collapseAriaLongueur", true); return "ajouter";
	 * 
	 * }
	 */

}
