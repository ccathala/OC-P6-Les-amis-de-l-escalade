package com.charles.lesamisdelescalade.consumer.bean;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Cotation;
import com.charles.lesamisdelescalade.model.beans.Longueur;

public interface LongueurDao {

	List<Longueur> findAllLongueurBySite(int siteId);

	Longueur findLongueurByNumeroAndVoie(int numero, int voieId);

	void addLongeur(Longueur longueur);

	List<Longueur> findAllLongueurByVoie(int voieId);

	List<Cotation> findAllLongueurCotation();

}
