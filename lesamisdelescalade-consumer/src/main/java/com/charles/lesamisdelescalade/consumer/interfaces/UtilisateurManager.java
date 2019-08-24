package com.charles.lesamisdelescalade.consumer.interfaces;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.charles.lesamisdelescalade.model.beans.Utilisateur;

public interface UtilisateurManager {
	
	public void addUtilisateur(Utilisateur utilisateur);
	
	public void editUtilisateur(Utilisateur utilisateur, int utilisateurId);
	
	public void deleteUtilisateur(int utilisateurId);
	
	public Utilisateur find(int utilisateurId);
	
	public Utilisateur findByEmail(String utilisateurEmail) throws EmptyResultDataAccessException;
	
	public List<Utilisateur> findAll();
	

}
