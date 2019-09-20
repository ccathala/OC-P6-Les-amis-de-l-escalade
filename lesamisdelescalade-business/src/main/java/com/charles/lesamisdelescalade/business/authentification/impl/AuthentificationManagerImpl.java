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
import com.charles.lesamisdelescalade.model.dto.AuthResult;

@Service
public class AuthentificationManagerImpl implements AuthentificationManager {

	@Autowired
	private UtilisateurDao utilisateurDao;
	
	/* Logger for LoginManagerImpl class */
	private static final Logger logger = LoggerFactory.getLogger(AuthentificationManagerImpl.class);

	public void addUtilisateur(Utilisateur utilisateur) {
		utilisateurDao.addUtilisateur(utilisateur);
	}

	public AuthResult login(Utilisateur utilisateur) {
		
				
		/* Search email corresponding user */
		try {
			Utilisateur utilisateurFromDatabase = utilisateurDao.findByEmail(utilisateur.getEmail());
			if (passwordIsCorresponding(utilisateur, utilisateurFromDatabase)) {

				/* Password match */
				return new AuthResult(utilisateurFromDatabase, "success");
				
			} else {

				/* Password doesn't match */
				return new AuthResult(null, "wrong password");
			}
		} catch (EmptyResultDataAccessException e) {
			return new AuthResult(null, "wrong email");
		}
		

		
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
