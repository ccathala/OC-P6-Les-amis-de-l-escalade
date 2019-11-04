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
import com.charles.lesamisdelescalade.business.account.AccountManager;
import com.charles.lesamisdelescalade.model.dto.UtilisateurDTO;

/**
 * Controller class in relation with registration jsp
 * 
 * @author Charles
 *
 */
@Controller
public class RegistrationController {

	/* Dependency injection Interface Login */
	@Autowired
	private AccountManager accountManager;
	
	// Set logger
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	/**
	 * Display registration page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String displayRegistrationPage(Model model) {

		logger.info("Requête d'accès à l'url /registration");
		model.addAttribute("registrationUtilisateur", new UtilisateurDTO());
		return "registration";
	}

	/**
	 * Process registration
	 * 
	 * @param registrationUtilisateur
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/processRegistration", method = RequestMethod.POST)
	public String processRegistration(
			@Valid @ModelAttribute("registrationUtilisateur") UtilisateurDTO registrationUtilisateur, BindingResult result,
			Model model) {

		logger.info("Requête d'accès à l'url /processRegistration");
		// Filter validation field errors
		if (result.getFieldErrorCount("nom") > 0 || result.getFieldErrorCount("email") > 0
				|| result.getFieldErrorCount("password") > 0 || result.getFieldErrorCount("confirmPassword") > 0) {

			// if error found 
			return "registration";
		} else {
			
			// Registration attempt 
			String messageError = accountManager.registerNewUser(registrationUtilisateur);

			if (messageError.isEmpty()) {
				return "registerSuccessful";
			} else {
				model.addAttribute("messageError", messageError);
				return "registration";
			}
		}

	}

}
