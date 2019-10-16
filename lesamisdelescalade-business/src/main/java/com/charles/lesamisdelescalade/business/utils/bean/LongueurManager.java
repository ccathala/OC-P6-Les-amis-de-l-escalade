package com.charles.lesamisdelescalade.business.utils.bean;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Longueur;

public interface LongueurManager {

	List<Longueur> findAllLongueurByVoie(int voieId);

}
