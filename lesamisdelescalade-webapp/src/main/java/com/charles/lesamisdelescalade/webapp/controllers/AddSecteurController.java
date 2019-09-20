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
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.webapp.utils.AddWebContentFormUtil;

@Controller
public class AddSecteurController {

	@Autowired
	private WebContentManager webContentManager;

	@Autowired
	private AddWebContentFormUtil addWebContentFormUtil;

	@RequestMapping(value = "/site/processChooseDepartementAddSecteur", method = RequestMethod.GET)
	public String chooseDepartement(Model model, @RequestParam(value = "departementIdSecteur") int departementId) {

		model.addAllAttributes(addWebContentFormUtil.getAddSecteurAttributesWhenDepartementIsSet(departementId));
		return "addWebContent";

	}

	@RequestMapping(value = "/site/processAddSecteur", method = RequestMethod.POST)
	public String addSecteur(Model model, @Valid @ModelAttribute(value = "secteur") Secteur secteur,
			BindingResult result) {

		if (result.hasErrors()) {
			model.addAllAttributes(addWebContentFormUtil.getAddSecteurAttributesWhenValidationErrors(secteur, result));
			return "addWebContent";
		} else {
			Boolean secteurAddedWithSuccess = webContentManager.addSecteur(secteur);
			model.addAllAttributes(addWebContentFormUtil.getAddSecteurAttributes(secteur, secteurAddedWithSuccess));
		}
		return "addWebContent";

	}
}
