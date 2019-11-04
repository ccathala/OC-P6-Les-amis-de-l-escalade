package com.charles.lesamisdelescalade.webapp.controllers;
 
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.SessionStatus;

import com.charles.lesamisdelescalade.model.beans.Utilisateur;
/**
 * Controller class handle logout request
 * 
 * @author Charles
 *
 */
@Controller
public class LogoutController {
	
	//Logger for LogoutController class 
	private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);

	/**
	 * Close user session
	 * 
	 * @param session
	 * @param sessionUtilisateur
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(SessionStatus status, HttpSession session, @SessionAttribute("sessionUtilisateur") Utilisateur sessionUtilisateur ) {
		
		logger.info("Requête d'accès à l'url /logout");
		//Bean for log use 
		Utilisateur utilisateurLog = new Utilisateur();
		utilisateurLog.setId(sessionUtilisateur.getId());
		
		// Close user session 
		status.setComplete();
		session.invalidate();
		
				
		
		// Diplay deconnection log 
		logger.debug("Déconnexion utilisateur ID: " + utilisateurLog.getId() + ".");
		
		return "home";
		
	}
}
