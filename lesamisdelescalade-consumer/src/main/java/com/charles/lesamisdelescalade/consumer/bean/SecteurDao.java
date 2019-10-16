package com.charles.lesamisdelescalade.consumer.bean;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.charles.lesamisdelescalade.model.beans.Secteur;

public interface SecteurDao {

	void addSecteur(Secteur secteur) throws DuplicateKeyException;

	List<Secteur> findAllSecteurBySite(int siteId);

	int getSecteurIdByVoieId(int voieId);

}
