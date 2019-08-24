package com.charles.lesamisdelescalade.business.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.charles.lesamisdelescalade.business.interfaces.LoginManager;
import com.charles.lesamisdelescalade.consumer.interfaces.UtilisateurManager;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;


/* Login Package Interface */
@Service
public class LoginImpl implements LoginManager {
	
	/* Logger for LoginImpl class */
	private static final Logger logger = LoggerFactory.getLogger(LoginImpl.class);

	/* Dependency injection Interface Utilisateur */
	@Autowired
	private UtilisateurManager utilisateurManager;

	/**
	 * Search existing user in database, throws exception if input email don't exist
	 */
	@Override
	public Utilisateur searchUserByMail(Utilisateur utilisateur) throws EmptyResultDataAccessException {

		/* Search User by email and fill the bddUtilisateur bean with user data */
		return utilisateurManager.findByEmail(utilisateur.getEmail());

	}

	/**
	 * Verify password correspondence
	 */
	@Override
	public boolean passwordIsCorresponding(Utilisateur utiliteurSession, Utilisateur utilisateurFromDatabase) {

		if (utiliteurSession.getPassword().equals(utilisateurFromDatabase.getPassword())) {
			/* Password is correct */
			
			/* Display connection success log */
			logger.debug("Connexion de l'utilisateur " + utiliteurSession.getEmail() + ".");
			
			return true;
		} else {
			/* Password is not correct */
			
			/* Display connection failed log */
			logger.debug("Echec de connexion - Mot de passe incorrect");
			
			
			return false;
		}

	}
	
	/**
	 * Fill user session bean with missing attributes 
	 */
	@Override
	public void fillUserSessionBean(Utilisateur utiliteurSession, Utilisateur utilisateurFromDatabase) {
		
		utiliteurSession.setId(utilisateurFromDatabase.getId());
		utiliteurSession.setNom(utilisateurFromDatabase.getNom());
		utiliteurSession.setRole_id(utilisateurFromDatabase.getRole_id());
		
	}

}
