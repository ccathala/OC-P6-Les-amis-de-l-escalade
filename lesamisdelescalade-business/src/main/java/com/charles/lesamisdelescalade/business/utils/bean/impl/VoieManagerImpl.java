package com.charles.lesamisdelescalade.business.utils.bean.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charles.lesamisdelescalade.business.utils.bean.VoieManager;
import com.charles.lesamisdelescalade.consumer.bean.VoieDao;
import com.charles.lesamisdelescalade.model.beans.Voie;

@Service
public class VoieManagerImpl implements VoieManager{
	
	@Autowired
	private VoieDao voieDao;
	
	
	@Override
	public List<Voie> findAllVoieBySecteur(int secteurId) {
		return voieDao.findAllVoieBySecteur(secteurId);
	}

}
