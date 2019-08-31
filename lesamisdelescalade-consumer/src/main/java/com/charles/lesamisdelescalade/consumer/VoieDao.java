package com.charles.lesamisdelescalade.consumer;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Voie;

public interface VoieDao {

	public List<Voie> findBySite(int siteId);
	public int getVoieCount(int secteurId);
	public String getMinCotation(int secteurId);
	public String getMaxCotation(int secteurId);
}
