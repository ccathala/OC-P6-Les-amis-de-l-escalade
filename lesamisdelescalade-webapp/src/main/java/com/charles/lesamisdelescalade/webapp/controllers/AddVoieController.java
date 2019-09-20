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
import com.charles.lesamisdelescalade.model.beans.Voie;
import com.charles.lesamisdelescalade.webapp.utils.AddWebContentFormUtil;

@Controller
public class AddVoieController {
	
	@Autowired
	private WebContentManager webContentManager;
	@Autowired
	private AddWebContentFormUtil addWebContentFormUtil;
	
	
	@RequestMapping(value = "/site/processChooseDepartementAddVoie", method = RequestMethod.GET)
	public String chooseDepartementVoie(Model model, @RequestParam(value="departementIdVoie") int departementId) {
		
		model.addAllAttributes(addWebContentFormUtil.getAddVoieAttributesWhenDepartementIsSet(departementId));
		return "addWebContent";
		
	}
	
	@RequestMapping(value = "/site/processChooseSiteAddVoie", method = RequestMethod.GET)
	public String chooseSiteVoie(Model model, @RequestParam(value="siteIdVoie") int siteId) {
		
		model.addAllAttributes(addWebContentFormUtil.getAddVoieAttributesWhenSiteIsSet(siteId));
		return "addWebContent";
		
	}
	
	@RequestMapping(value = "/site/processChooseSecteurAddVoie", method = RequestMethod.GET)
	public String chooseSecteurVoie(Model model, @RequestParam(value="secteurIdVoie") int secteurId) {
		
		model.addAllAttributes(addWebContentFormUtil.getAddVoieAttributesWhenSecteurIsSet(secteurId));
		return "addWebContent";
		
	}
	
	@RequestMapping(value = "/site/processAddVoie", method = RequestMethod.POST)
	public String addVoie(Model model, @Valid @ModelAttribute(value = "voie") Voie voie, BindingResult result) {
		
		if (result.hasErrors()) {
			model.addAllAttributes(addWebContentFormUtil.getAddVoieAttributesWhenValidationErrors(voie));
			return "addWebContent";
		}else {
			String error = webContentManager.addVoie(voie);
			model.addAllAttributes(addWebContentFormUtil.getAddVoieAttributes(voie, error));	
		}		
		return "addWebContent";
	}

}
