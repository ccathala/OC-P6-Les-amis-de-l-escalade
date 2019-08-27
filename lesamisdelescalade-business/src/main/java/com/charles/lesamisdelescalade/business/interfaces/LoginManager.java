package com.charles.lesamisdelescalade.business.interfaces;

import org.springframework.dao.EmptyResultDataAccessException;

import com.charles.lesamisdelescalade.model.beans.Utilisateur;

public interface LoginManager {
	
	public Utilisateur searchUserByMail(Utilisateur utilisateur) throws EmptyResultDataAccessException;
	public boolean passwordIsCorresponding(Utilisateur utilisateurSession, Utilisateur utilisateurFromDatabase);
	public void fillUserSessionBean(Utilisateur utilisateurSession, Utilisateur utilisateurFromDatabase);
	public String registerNewUser(Utilisateur utilisateurRegister);
	
	

}
