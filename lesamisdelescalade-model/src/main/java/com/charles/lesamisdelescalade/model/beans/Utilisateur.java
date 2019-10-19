package com.charles.lesamisdelescalade.model.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Bean Utilisateur
 * 
 * @author Charles
 *
 */
public class Utilisateur {
	
	private int id;
	
	@NotEmpty
	@Size(min=3, message="doit contenir au minimun 3 caractères")
	private String nom;
	
	@NotEmpty
	@Email(message="saisir une adresse email valide")
	private String email;
	
	@NotEmpty
	@Size(min=6, message="doit contenir au minimun 6 caractères")
	private String password;
	
	private int role_id;

	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur(int id, String nom, String email, String password, int role_id) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.role_id = role_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", email=" + email + ", password=" + password + ", role_id="
				+ role_id + "]";
	}
	
	
		
	
	
	
	

}
