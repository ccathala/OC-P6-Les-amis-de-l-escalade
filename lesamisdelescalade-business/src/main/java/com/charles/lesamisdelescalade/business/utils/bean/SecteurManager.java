package com.charles.lesamisdelescalade.business.utils.bean;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Secteur;

public interface SecteurManager {

	List<Secteur> getAllSecteurBySite(int siteId);

	int getSecteurIdByVoieId(int voieId);

}
