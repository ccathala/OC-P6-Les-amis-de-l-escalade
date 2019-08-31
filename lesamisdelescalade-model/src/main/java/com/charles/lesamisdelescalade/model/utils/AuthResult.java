package com.charles.lesamisdelescalade.model.utils;

import com.charles.lesamisdelescalade.model.beans.Utilisateur;

public class AuthResult {
	
	private Utilisateur utilisateur;
	private String authStatus;
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public String getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
	public AuthResult(Utilisateur utilisateur, String authStatus) {
		super();
		this.utilisateur = utilisateur;
		this.authStatus = authStatus;
	}
	public AuthResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AuthResult [utilisateur=" + utilisateur + ", authStatus=" + authStatus + "]";
	}
	
		
	
	
	
	
	

}
