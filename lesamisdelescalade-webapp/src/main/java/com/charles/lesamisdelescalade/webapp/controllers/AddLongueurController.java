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
import com.charles.lesamisdelescalade.webapp.utils.AddWebContentFormUtil;

@Controller
public class AddLongueurController {
	
	@Autowired
	private WebContentManager webContentManager;
	@Autowired
	private AddWebContentFormUtil addWebContentFormUtil;
	
	@RequestMapping(value = "/site/processChooseDepartementAddLongueur", method = RequestMethod.GET)
	public String chooseDepartementLongueur(Model model, @RequestParam(value="departementIdLongueur") int departementId) {
		
		model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributesWhenDepartementIsSet(departementId));
		return "addWebContent";
		
	}
	
	@RequestMapping(value = "/site/processChooseSiteAddLongueur", method = RequestMethod.GET)
	public String chooseSiteLongueur(Model model, @RequestParam(value="siteIdLongueur") int siteId) {
		
		model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributesWhenSiteIsSet(siteId));
		return "addWebContent";
		
	}
	
	@RequestMapping(value = "/site/processChooseSecteurAddLongueur", method = RequestMethod.GET)
	public String chooseSecteurLongueur(Model model, @RequestParam(value="secteurIdLongueur") int secteurId) {
		
		model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributesWhenSecteurIsSet(secteurId));
		return "addWebContent";
		
	}
	
	@RequestMapping(value = "/site/processChooseVoieAddLongueur", method = RequestMethod.GET)
	public String chooseVoieLongueur(Model model, @RequestParam(value="voieIdLongueur") int voieId) {
		
		model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributesWhenVoieIsSet(voieId));
		return "addWebContent";

	}
	
	@RequestMapping(value = "/site/processAddLongueur", method = RequestMethod.POST)
	public String addLongueur(Model model, @Valid @ModelAttribute(value = "longueur") Longueur longueur, BindingResult result) {
		
		int secteurId = webContentManager.getSecteurIdByVoieId(longueur.getVoie_id());
		int siteId = webContentManager.getSiteIdBySecteurId(secteurId);
		int departementId = webContentManager.getDepartementIdBySiteId(siteId);
		if (result.hasErrors()) {
			model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributesWhenValidationErrors(longueur, departementId, siteId, secteurId));
			return "addWebContent";
		}else {
			Boolean isNumberInputAlreadyUsed = webContentManager.addLongueur(longueur);
			model.addAllAttributes(addWebContentFormUtil.getAddLongueurAttributes(longueur, departementId, siteId, secteurId, isNumberInputAlreadyUsed));
			return "addWebContent";
		}
	}
	
	

}
