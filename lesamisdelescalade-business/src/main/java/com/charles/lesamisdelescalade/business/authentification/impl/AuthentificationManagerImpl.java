package com.charles.lesamisdelescalade.business.authentification.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.charles.lesamisdelescalade.business.authentification.AuthentificationManager;
import com.charles.lesamisdelescalade.consumer.UtilisateurDao;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.dto.LoginData;

@Service
public class AuthentificationManagerImpl implements AuthentificationManager {

	@Autowired
	private UtilisateurDao utilisateurDao;
	
	/* Logger for LoginManagerImpl class */
	private static final Logger logger = LoggerFactory.getLogger(AuthentificationManagerImpl.class);

	public void addUtilisateur(Utilisateur utilisateur) {
		utilisateurDao.addUtilisateur(utilisateur);
	}

	public LoginData login(Utilisateur utilisateur) {
		
		LoginData loginData = new LoginData();	
		/* Search email corresponding user */
		try {
			Utilisateur utilisateurFromDatabase = utilisateurDao.findByEmail(utilisateur.getEmail());
			if (passwordIsCorresponding(utilisateur, utilisateurFromDatabase)) {

				/* Password match */
				loginData.setUtilisateur(utilisateurFromDatabase);
				loginData.setLoginResult("success");
				
				
				
			} else {

				/* Password doesn't match */
				loginData.setUtilisateur(null);
				loginData.setLoginResult("wrong password");
				
				
			}
		} catch (EmptyResultDataAccessException e) {
			loginData.setUtilisateur(null);
			loginData.setLoginResult("wrong email");
		}
		return loginData;
	}

	/**
	 * Verify password correspondence
	 */
	public boolean passwordIsCorresponding(Utilisateur utilisateurSession, Utilisateur utilisateurFromDatabase) {

		if (BCrypt.checkpw(utilisateurSession.getPassword(), utilisateurFromDatabase.getPassword())) {
			/* Password is correct */

			/* Display connection success log */
			logger.debug("Connexion de l'utilisateur ID: " + utilisateurSession.getId() + ".");

			return true;
		} else {
			/* Password is not correct */

			/* Display connection failed log */
			logger.debug("Echec de connexion - Mot de passe incorrect");

			return false;
		}

	}

	

}
