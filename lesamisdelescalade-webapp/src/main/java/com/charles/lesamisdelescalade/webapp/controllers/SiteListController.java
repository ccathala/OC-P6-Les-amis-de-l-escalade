package com.charles.lesamisdelescalade.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;

@Controller
public class SiteListController {
	
	@Autowired
	private WebContentManager webContentManager;
	
	@RequestMapping(value="/siteList", method = RequestMethod.GET)
	public String siteList(Model model, @SessionAttribute(value="sessionUtilisateur", required = false) Utilisateur sessionUtilisateur) {
		
		model.addAttribute("sessionUtilisateur", sessionUtilisateur);
		model.addAttribute("sites", webContentManager.findAllSite());
		return "siteList";
	}
}

