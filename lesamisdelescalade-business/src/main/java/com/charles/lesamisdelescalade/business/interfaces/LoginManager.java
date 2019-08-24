package com.charles.lesamisdelescalade.business.interfaces;

import org.springframework.dao.EmptyResultDataAccessException;

import com.charles.lesamisdelescalade.model.beans.Utilisateur;

public interface LoginManager {
	
	public Utilisateur searchUserByMail(Utilisateur utilisateur) throws EmptyResultDataAccessException;
	public boolean passwordIsCorresponding(Utilisateur utiliteurSession, Utilisateur utilisateurFromDatabase);
	public void fillUserSessionBean(Utilisateur utiliteurSession, Utilisateur utilisateurFromDatabase);
	
	

}
