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
import com.charles.lesamisdelescalade.business.authentification.AuthentificationManager;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.dto.AuthResult;

/**
 * Login page controller
 * 
 * @author Charles
 *
 */
@Controller
@SessionAttributes("sessionUtilisateur")
public class LoginController {

	/* Dependency injection Interface Login */
	@Autowired
	private AuthentificationManager authentificationManager;

	/**
	 * Define bean sessionUtilisateur as a bean Utilisateur
	 * 
	 * @return
	 */
	@ModelAttribute("sessionUtilisateur")
	public Utilisateur setUtilisateurSession() {
		return new Utilisateur();
	}

	/* Logger for LoginController class */
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * Url request handler for /login
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {

		/* Add new bean Utilisateur attribute */
		model.addAttribute("sessionUtilisateur", new Utilisateur());

		/* Return home.jsp view */
		return "login";
	}

	/**
	 * Url request handler for /processLogin
	 * 
	 * @param utilisateurSession
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/processLogin", method = RequestMethod.POST)
	public String processLogin(@Valid @ModelAttribute("sessionUtilisateur") Utilisateur utilisateurSession,
			BindingResult result, Model model) {

		/* Form validation */
		if (result.getFieldErrorCount("email") > 0 || result.getFieldErrorCount("password") > 0) {

			/* Form input not valid */

			/* Return login.jsp view */
			return "login";

		} else {

			AuthResult authResult = authentificationManager.login(utilisateurSession);

			if (authResult.getAuthStatus().equals("success")) {

				/* Add session bean Utilisateur attribute */
				model.addAttribute("utilisateurSession", authResult.getUtilisateur());

				/* Return home.jsp view */
				return "home";

			} else if (authResult.getAuthStatus().equals("wrong password")) {

				/* Add error message attribute */
				model.addAttribute("erreur_login", "Echec de connexion - Mot de passe incorrect");

				/* Reset password input */
				utilisateurSession.setPassword("");

				/* Add new bean Utilisateur attribute */
				model.addAttribute("utilisateurSession", utilisateurSession);
				return "login";

			} else if (authResult.getAuthStatus().equals("wrong email")) {

				/* Display debug log */
				logger.debug("Echec de connexion - L'adresse saisie n'existe pas");

				/* Add error message attribute */
				model.addAttribute("erreur_login", "Echec de connexion - L'adresse saisie n'existe pas");

				/* Return login.jsp view */
				return "login";

			}else {
				return null;
			}

		}
		
	}
}
