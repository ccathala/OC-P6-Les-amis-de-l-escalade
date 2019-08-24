package com.charles.lesamisdelescalade.webapp.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.charles.lesamisdelescalade.business.interfaces.LoginManager;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;

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
	private LoginManager loginManager;

	/**
	 * Define bean sessionUtilisateur as a bean Utilisateur
	 * 
	 * @return
	 */
	@ModelAttribute("sessionUtilisateur")
	public Utilisateur setcUtilisateur() {
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
		model.addAttribute("utilisateur", new Utilisateur());

		/* Return home.jsp view */
		return "login";
	}

	/**
	 * Url request handler for /processLogin
	 * 
	 * @param cUtilisateur
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/processLogin", method = RequestMethod.POST)
	public String processLogin(@Valid /* @ModelAttribute("sessionUtilisateur") */ Utilisateur cUtilisateur,
			BindingResult bindingResult, Model model) {

		/* Form validation */
		if (bindingResult.hasErrors()) {

			/* Form input not valid */

			/* Return login.jsp view */
			return "login";

		} else {

			/* Form input valid */

			try {
				/* Search email corresponding user */
				Utilisateur utilisateurFromDatabase = loginManager.searchUserByMail(cUtilisateur);

				if (loginManager.passwordIsCorresponding(cUtilisateur, utilisateurFromDatabase)) {

					/* Password match */

					/* Fill user session bean with missing attributes */
					loginManager.fillUserSessionBean(cUtilisateur, utilisateurFromDatabase);

					/* Add session bean Utilisateur attribute */
					model.addAttribute("cUtilisateur", cUtilisateur);

					/* Return home.jsp view */
					return "home";

				} else {

					/* Password mismatch */

					/* Add error message attribute */
					model.addAttribute("erreur_login", "Echec de connexion - Mot de passe incorrect");

					/* Reset password input */
					cUtilisateur.setPassword("");

					/* Add new bean Utilisateur attribute */
					model.addAttribute("cUtilisateur", cUtilisateur);
					return "login";

				}

			} catch (EmptyResultDataAccessException e) {
				/* if email don't exist in the database, the exception is catch */

				/* Display debug log */
				logger.debug("Echec de connexion - L'adresse saisie n'existe pas");

				/* Add error message attribute */
				model.addAttribute("erreur_login", "Echec de connexion - L'adresse saisie n'existe pas");

				/* Return login.jsp view */
				return "login";

			}
		}
	}
}
