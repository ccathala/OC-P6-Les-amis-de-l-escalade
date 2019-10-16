package com.charles.lesamisdelescalade.consumer.bean;

import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;

public interface UtilisateurDao {
	
	public void addUtilisateur(Utilisateur utilisateur);
	
	public Utilisateur findByEmail(String utilisateurEmail) throws EmptyResultDataAccessException;
	
	public Utilisateur findByUsername(String utilisateurNom) throws EmptyResultDataAccessException;
	
	public List<Utilisateur> findAll();
	
	public List<Utilisateur> findAllUtilisateurOnlyIdAndName();
	

}
