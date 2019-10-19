package com.charles.lesamisdelescalade.business.account.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.charles.lesamisdelescalade.business.account.AccountManager;
import com.charles.lesamisdelescalade.consumer.bean.UtilisateurDao;
import com.charles.lesamisdelescalade.model.dto.UtilisateurDTO;

/**
 * Implementation methods in relation with registration functions
 * 
 * @author Charles
 *
 */
@Service
public class AccountManagerImpl implements AccountManager {
	
	// Set logger 
	private static final Logger logger = LoggerFactory.getLogger(AccountManagerImpl.class);

	// Dependency injection  
	@Autowired
	private UtilisateurDao utilisateurDao;

	/**
	 * Check if email and username are already used before to add new user in database
	 * 
	 * @param utilisateurRegister
	 * @return
	 */
	@Override
	public String registerNewUser(UtilisateurDTO utilisateurRegister) {
		
		// TODO méthode trop longue à simplifier

		//Property declaration
		boolean nameIsAlreadyUsed;
		boolean emailIsAlreadyUsed;
		boolean passwordIsWellConfirmed;
		String messageError = "";
		
		logger.info("Tentative d'inscription");

		// Test username, if exception is catch, input username is not already used 
		try {
			utilisateurDao.findByUsername(utilisateurRegister.getNom());
			nameIsAlreadyUsed = true;
			messageError = "Echec de l'inscription - Le nom saisi est déja utilisé";
			logger.debug("Echec de l'inscription - Le nom saisi est déja utilisé");
		} catch (EmptyResultDataAccessException e) {
			nameIsAlreadyUsed = false;
		}

		// Test email, if exception is catch, input email is not already used 
		try {
			utilisateurDao.findByEmail(utilisateurRegister.getEmail());
			emailIsAlreadyUsed = true;
			messageError = "Echec de l'inscription - L'adresse email saisie est déja utilisée";
			logger.debug("Echec de l'inscription - L'adresse email saisie est déja utilisée");
		} catch (EmptyResultDataAccessException e) {
			emailIsAlreadyUsed = false;
		}

		// Check correspondence between password and passwordConfirmation 
		if (utilisateurRegister.getPassword().equals(utilisateurRegister.getConfirmPassword())) {

			// inputs match 
			passwordIsWellConfirmed = true;
		} else {

			// inputs don't match 
			passwordIsWellConfirmed = false;
			messageError = "Echec de l'inscription - Les mots de passe saisis ne sont pas identiques";
			logger.debug("Echec de l'inscription - Les mots de passe saisis ne sont pas identiques");
		}

		// Check that all conditions for creating a new user are reached 
		if (!nameIsAlreadyUsed && !emailIsAlreadyUsed && passwordIsWellConfirmed) {

			// Conditions are reached 
			utilisateurRegister.setPassword(BCrypt.hashpw(utilisateurRegister.getPassword(), BCrypt.gensalt()));
			utilisateurDao.addUtilisateur(utilisateurRegister);
			logger.debug("Succès de l'inscription");

		}
		// Return error message, can be empty 
		return messageError;

	}
	
	
	
}
