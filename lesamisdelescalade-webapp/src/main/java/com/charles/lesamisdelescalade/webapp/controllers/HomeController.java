package com.charles.lesamisdelescalade.webapp.controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;

/**
 * Controller class in relation with home jsp
 * 
 * @author Charles
 *
 */
@Controller
public class HomeController {

	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @param locale
	 * @param model
	 * @param sessionUtilisateur
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@ModelAttribute(value = "messageError") String messageError) {
		
		logger.info("Requête d'accès à l'url /");
		//model.addAttribute("sessionUtilisateur", sessionUtilisateur);
		model.addAttribute("messageError", messageError);
		return "home";
	}

}
