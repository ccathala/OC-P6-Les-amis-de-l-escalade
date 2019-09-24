package com.charles.lesamisdelescalade.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.charles.lesamisdelescalade.model.beans.Utilisateur;

public class UtilisateurDTO extends Utilisateur{
	
	
	private String authStatus;
	
	@NotEmpty
	@Size(min=6, message="doit contenir au minimun 6 caractères")
	private String confirmPassword;

	public UtilisateurDTO() {
		super();
		
	}

	public UtilisateurDTO(int id, String nom, String email, String password, int role_id) {
		super(id, nom, email, password, role_id);
		
	}

	public UtilisateurDTO(String authStatus, String confirmPassword) {
		super();
		this.authStatus = authStatus;
		this.confirmPassword = confirmPassword;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "UtilisateurDTO [authStatus=" + authStatus + ", confirmPassword=" + confirmPassword + "]";
	}
	
	
	
		
	
	
	
	
	

}
