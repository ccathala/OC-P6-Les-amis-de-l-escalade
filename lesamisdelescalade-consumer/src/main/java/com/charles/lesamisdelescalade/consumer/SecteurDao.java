package com.charles.lesamisdelescalade.consumer;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.charles.lesamisdelescalade.model.beans.Secteur;

public interface SecteurDao {
	
	public List<Secteur> findAllSecteursBySite(int siteId);
	public void addSecteur(Secteur secteur) throws DuplicateKeyException;


}
