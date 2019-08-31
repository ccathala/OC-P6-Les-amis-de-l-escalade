package com.charles.lesamisdelescalade.consumer;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Secteur;

public interface SecteurDao {
	
	public List<Secteur> findSecteursBySite(int siteId);


}
