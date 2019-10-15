package com.charles.lesamisdelescalade.consumer.model;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.charles.lesamisdelescalade.model.beans.Voie;

public interface VoieDao {

	List<Voie> findVoieBySite(int siteId);

	int getVoieCountBySecteur(int secteurId);

	String getSecteurMinCotation(int secteurId) throws NullPointerException;

	String getSecteurMaxCotation(int secteurId) throws NullPointerException;

	Voie findVoieByNumeroAndSecteur(int numero, int secteurId) throws EmptyResultDataAccessException;

	Voie findVoieByNomAndSecteur(String nom, int secteurId) throws EmptyResultDataAccessException;

	void addVoie(Voie voie);

	List<Voie> findAllVoieBySecteur(int secteurId);

}
