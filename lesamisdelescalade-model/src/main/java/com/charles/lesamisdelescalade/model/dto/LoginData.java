package com.charles.lesamisdelescalade.model.dto;

import com.charles.lesamisdelescalade.model.beans.Utilisateur;

/**
 * DTO LoginData, used to return the result of a login attempt
 * 
 * @author Charles
 *
 */
public class LoginData {
	
	private Utilisateur utilisateur;
	private String loginResult;
	
	public LoginData() {
		super();
		
	}

	public LoginData(Utilisateur utilisateur, String loginResult) {
		super();
		this.utilisateur = utilisateur;
		this.loginResult = loginResult;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}

	@Override
	public String toString() {
		return "LoginData [utilisateur=" + utilisateur + ", loginResult=" + loginResult + "]";
	}
	
	
	

}
