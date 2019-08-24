package com.charles.lesamisdelescalade.model.beans;

import javax.validation.constraints.NotEmpty;

public class Utilisateur {
	
	private int id;
	
	private String nom;
	
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;
	
	private int role_id;
		
	public Utilisateur() {
		
	}
	
	public Utilisateur(int id, String nom, String email, String password, int role_id) {
		
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
