package com.charles.lesamisdelescalade.webapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.charles.lesamisdelescalade.business.interfaces.LoginManager;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;

@Controller
public class RegistrationController {

	/* Dependency injection Interface Login */
	@Autowired
	private LoginManager loginManager;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {

		model.addAttribute("registrationUtilisateur", new Utilisateur());

		return "registration";
	}

	@RequestMapping(value = "/processRegistration", method = RequestMethod.POST)
	public String processRegistration(
			@Valid @ModelAttribute("registrationUtilisateur") Utilisateur registrationUtilisateur, BindingResult result,
			Model model) {

		/* Filter validation field errors */
		if (result.getFieldErrorCount("nom") > 0 || result.getFieldErrorCount("email") > 0
				|| result.getFieldErrorCount("password") > 0 || result.getFieldErrorCount("confirmPassword") > 0) {

			/* if error found */
			return "registration";
		} else {
			
			/* Registration attempt */
			String messageError = loginManager.registerNewUser(registrationUtilisateur);

			if (messageError.isEmpty()) {
				return "registerSuccessful";
			} else {
				model.addAttribute("messageError", messageError);
				return "registration";
			}
		}

	}

}
