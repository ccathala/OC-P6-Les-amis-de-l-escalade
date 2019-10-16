package com.charles.lesamisdelescalade.business.utils.bean.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.charles.lesamisdelescalade.business.utils.bean.LongueurManager;
import com.charles.lesamisdelescalade.consumer.bean.LongueurDao;
import com.charles.lesamisdelescalade.model.beans.Longueur;

@Service
public class LongueurManagerImpl implements LongueurManager{
	
	@Autowired
	private LongueurDao longueurDao;
	
	@Override
	public List<Longueur> findAllLongueurByVoie(int voieId) {
		return longueurDao.findAllLongueurByVoie(voieId);
	}

}
