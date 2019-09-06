package com.charles.lesamisdelescalade.consumer;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Longueur;

public interface LongueurDao {
	
	public List<Longueur> findBySite(int siteId);

}
