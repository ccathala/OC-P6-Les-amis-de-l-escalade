package com.charles.lesamisdelescalade.webapp.controllers;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.charles.lesamisdelescalade.business.authentification.AuthentificationManager;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.dto.LoginData;

/**
 * Controller class in relation with login jsp
 * 
 * @author Charles
 *
 */
@Controller
@SessionAttributes("sessionUtilisateur")
public class LoginController {

	// Dependency injection
	@Autowired
	private AuthentificationManager authentificationManager;
	
	
	/**
	 * Define bean sessionUtilisateur as a bean Utilisateur
	 * 
	 * @return
	 */


	//Set logger
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * Url request handler for /login
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		
		logger.info("Requête d'accès à l'url /login");
		model.addAttribute("loginUtilisateur", new Utilisateur());
		return "login";
	}

	/**
	 * Url request handler for /processLogin
	 * 
	 * @param sessionUtilisateur
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/processLogin", method = RequestMethod.POST)
	public String processLogin(SessionStatus status, 
			@Valid @ModelAttribute(value="loginUtilisateur") Utilisateur loginUtilisateur,
			BindingResult result, Model model) {
		
		logger.info("Requête d'accès à l'url /processLogin");
		if (result.getFieldErrorCount("email") > 0 || result.getFieldErrorCount("password") > 0) {
			return "login";
		} else {
			LoginData loginData = new LoginData();
			loginData = authentificationManager.login(loginUtilisateur);
			if (loginData.getLoginResult().equals("success")) {
				model.addAttribute("sessionUtilisateur", loginData.getUtilisateur());
				return "home";
			} else if (loginData.getLoginResult().equals("wrong password")) {
				model.addAttribute("erreur_login", "Echec de connexion - Mot de passe incorrect");
				
				return "login";
			} else if (loginData.getLoginResult().equals("wrong email")) {
				model.addAttribute("erreur_login", "Echec de connexion - L'adresse saisie n'existe pas");
				
				return "login";
			} else {
				return null;
			}
		}
	}
}
