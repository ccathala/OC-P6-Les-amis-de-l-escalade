package com.charles.lesamisdelescalade.business.utils.bean;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Voie;

public interface VoieManager {

	List<Voie> findAllVoieBySecteur(int secteurId);

}
