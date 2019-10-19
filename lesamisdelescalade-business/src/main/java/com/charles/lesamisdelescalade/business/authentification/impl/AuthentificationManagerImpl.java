package com.charles.lesamisdelescalade.business.authentification.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.charles.lesamisdelescalade.business.authentification.AuthentificationManager;
import com.charles.lesamisdelescalade.consumer.bean.UtilisateurDao;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.dto.LoginData;

/**
 * Implementation methods in relation with login functions
 * 
 * @author Charles
 *
 */
@Service
public class AuthentificationManagerImpl implements AuthentificationManager {

	@Autowired
	private UtilisateurDao utilisateurDao;

	// Set logger 
	private static final Logger logger = LoggerFactory.getLogger(AuthentificationManagerImpl.class);
	
	
	/**
	 * Login method, look for input email in database, check password correspondence is email is found
	 * 
	 * @param utilisateur
	 * @return
	 */
	@Override
	public LoginData login(Utilisateur utilisateur) {
		
		logger.info("Tentative de connexion");

		LoginData loginData = new LoginData();
		// Search email corresponding user 
		try {
			Utilisateur utilisateurFromDatabase = utilisateurDao.findByEmail(utilisateur.getEmail());
			if (passwordIsCorresponding(utilisateur, utilisateurFromDatabase)) {
				// Password match
				loginData.setUtilisateur(utilisateurFromDatabase);
				loginData.setLoginResult("success");
				logger.debug("Connexion avec succès de l'utilisateur ID: " + loginData.getUtilisateur().getId() + ".");
				
			} else {
				// Password doesn't match 
				logger.debug("Echec de connexion - Mot de passe incorrect");
				loginData.setUtilisateur(null);
				loginData.setLoginResult("wrong password");
			}
		} catch (EmptyResultDataAccessException e) {
			// no email correspondence
			logger.debug("Echec de connexion - L'adresse saisie n'existe pas");
			loginData.setUtilisateur(null);
			loginData.setLoginResult("wrong email");
		}
		return loginData;
	}

	//Verify password correspondence
	/**
	 * Check password correspondence between input bean utilisateur and from database bean utilisateur 
	 * 
	 * @param utilisateurSession
	 * @param utilisateurFromDatabase
	 * @return
	 */
	private boolean passwordIsCorresponding(Utilisateur utilisateurSession, Utilisateur utilisateurFromDatabase) {

		if (BCrypt.checkpw(utilisateurSession.getPassword(), utilisateurFromDatabase.getPassword())) {
			return true;
		} else {
			return false;
		}

	}

}
