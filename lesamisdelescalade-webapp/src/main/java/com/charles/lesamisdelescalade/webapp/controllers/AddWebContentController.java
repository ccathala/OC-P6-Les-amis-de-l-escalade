package com.charles.lesamisdelescalade.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.beans.Voie;
import com.charles.lesamisdelescalade.webapp.utils.AddWebContentFormUtil;

@Controller
public class AddWebContentController {

	@Autowired
	private AddWebContentFormUtil addWebContentFormUtil;

	@RequestMapping(value = "/site/ajouter", method = RequestMethod.GET)
	public String addWebContent(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur, RedirectAttributes redirectAttributes) {
		
		if(sessionUtilisateur == null) {
			redirectAttributes.addFlashAttribute("messageError", "Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		}else {
			model.addAllAttributes(addWebContentFormUtil.getAddWebContentAttributes(sessionUtilisateur));
			return "addWebContent";
		}
		
	}

	

}
