package com.charles.lesamisdelescalade.consumer;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.charles.lesamisdelescalade.model.beans.Voie;

public interface VoieDao {

	public List<Voie> findBySite(int siteId);
	public int getVoieCount(int secteurId);
	public String getMinCotation(int secteurId);
	public String getMaxCotation(int secteurId);
	public Voie findVoieByNumeroAndSite(int numero, int siteId) throws EmptyResultDataAccessException;
	public Voie findVoieByNomAndSite(String nom, int siteId) throws EmptyResultDataAccessException;
	public void addVoie(Voie voie);
}
