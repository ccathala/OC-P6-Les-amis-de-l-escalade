package com.charles.lesamisdelescalade.business.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
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

		/* Search User by email in database and return this user */
		return utilisateurManager.findByEmail(utilisateur.getEmail());

	}

	/**
	 * Verify password correspondence
	 */
	@Override
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

	/**
	 * Fill user session bean with missing attributes
	 */
	@Override
	public void fillUserSessionBean(Utilisateur utilisateurSession, Utilisateur utilisateurFromDatabase) {

		utilisateurSession.setId(utilisateurFromDatabase.getId());
		utilisateurSession.setNom(utilisateurFromDatabase.getNom());
		utilisateurSession.setRole_id(utilisateurFromDatabase.getRole_id());

	}
	
}
