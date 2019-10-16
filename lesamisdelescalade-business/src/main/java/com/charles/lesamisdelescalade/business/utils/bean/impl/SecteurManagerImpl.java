package com.charles.lesamisdelescalade.business.utils.bean.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.charles.lesamisdelescalade.business.utils.bean.SecteurManager;
import com.charles.lesamisdelescalade.consumer.bean.SecteurDao;
import com.charles.lesamisdelescalade.model.beans.Secteur;

@Service
public class SecteurManagerImpl implements SecteurManager {
	
	@Autowired
	private SecteurDao secteurDao;
	
	@Override
	public List<Secteur> getAllSecteurBySite(int siteId) {
		return secteurDao.findAllSecteurBySite(siteId);
	}

	@Override
	public int getSecteurIdByVoieId(int voieId) {
		return secteurDao.getSecteurIdByVoieId(voieId);
	}

}
