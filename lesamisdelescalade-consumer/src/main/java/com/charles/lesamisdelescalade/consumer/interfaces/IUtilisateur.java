package com.charles.lesamisdelescalade.consumer.interfaces;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Utilisateur;

public interface IUtilisateur {
	
	public void addUtilisateur(Utilisateur utilisateur);
	
	public void editUtilisateur(Utilisateur utilisateur, int utilisateurId);
	
	public void deleteUtilisateur(int utilisateurId);
	
	public Utilisateur find(int utilisateurId);
	
	public List<Utilisateur> findAll();
	

}
