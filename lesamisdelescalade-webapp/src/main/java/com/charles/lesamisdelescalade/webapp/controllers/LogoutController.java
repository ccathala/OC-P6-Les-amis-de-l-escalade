package com.charles.lesamisdelescalade.webapp.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.charles.lesamisdelescalade.model.beans.Utilisateur;

@Controller
public class LogoutController {
	
	/* Logger for LogoutController class */
	private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, @SessionAttribute("sessionUtilisateur") Utilisateur utilisateurSession ) {
		
		/* Bean for log use */
		Utilisateur utilisateurLog = new Utilisateur();
		utilisateurLog.setId(utilisateurSession.getId());
		
		/* Close user session */
		session.invalidate();
		
		/* Diplay deconnection log */
		logger.debug("Déconnexion utilisateur ID: " + utilisateurLog.getId() + ".");
		
		return "home";
		
	}
}
