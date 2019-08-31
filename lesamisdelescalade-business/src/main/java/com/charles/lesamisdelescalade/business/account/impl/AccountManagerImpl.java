package com.charles.lesamisdelescalade.business.account.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.charles.lesamisdelescalade.business.account.AccountManager;
import com.charles.lesamisdelescalade.consumer.UtilisateurDao;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;

@Service
public class AccountManagerImpl implements AccountManager {
	
	/* Logger for LoginImpl class */
	private static final Logger logger = LoggerFactory.getLogger(AccountManagerImpl.class);

	/* Dependency injection Interface Utilisateur */
	@Autowired
	private UtilisateurDao utilisateurDao;

	@Override
	public String registerNewUser(Utilisateur utilisateurRegister) {

		/* Property declaration */
		boolean nameIsAlreadyUsed;
		boolean emailIsAlreadyUsed;
		boolean passwordIsWellConfirmed;
		String messageError = "";

		/* Test unsername, if exception is catch, input username is not already used */
		try {
			utilisateurDao.findByUsername(utilisateurRegister.getNom());
			nameIsAlreadyUsed = true;
			messageError = "Echec de l'inscription - Le nom saisi est déja utilisé";
			logger.info("Registration attempt failed - Username already exist");
		} catch (EmptyResultDataAccessException e) {
			nameIsAlreadyUsed = false;
		}

		/* Test email, if exception is catch, input email is not already used */
		try {
			utilisateurDao.findByEmail(utilisateurRegister.getEmail());
			emailIsAlreadyUsed = true;
			messageError = "Echec de l'inscription - L'adresse email saisie est déja utilisée";
			logger.info("Registration attempt failed - Email address already exist");
		} catch (EmptyResultDataAccessException e) {
			emailIsAlreadyUsed = false;
		}

		/* Check correspondence between password and passwordConfirmation */
		if (utilisateurRegister.getPassword().equals(utilisateurRegister.getConfirmPassword())) {

			/* inputs match */
			passwordIsWellConfirmed = true;
		} else {

			/* inputs don't match */
			passwordIsWellConfirmed = false;
			messageError = "Echec de l'inscription - Les mots de passe saisis ne sont pas identiques";
			logger.info("Registration attempt failed - Password and confirm password don't match");
		}

		/* Check that all conditions for creating a new user are reached */
		if (!nameIsAlreadyUsed && !emailIsAlreadyUsed && passwordIsWellConfirmed) {

			/* Conditions are reached */
			utilisateurRegister.setPassword(BCrypt.hashpw(utilisateurRegister.getPassword(), BCrypt.gensalt()));
			utilisateurDao.addUtilisateur(utilisateurRegister);
			logger.info("Registration successful");

		}
		/* Return error message, can be empty */
		return messageError;

	}
	
}
