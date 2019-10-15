package com.charles.lesamisdelescalade.consumer.model;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Cotation;
import com.charles.lesamisdelescalade.model.beans.Longueur;

public interface LongueurDao {

	List<Longueur> findLongueurBySite(int siteId);

	Longueur findLongueurByNumeroAndVoie(int numero, int voieId);

	void addLongeur(Longueur longueur);

	List<Longueur> findAllLongueurByVoie(int voieId);

	List<Cotation> findAllCotation();

}
