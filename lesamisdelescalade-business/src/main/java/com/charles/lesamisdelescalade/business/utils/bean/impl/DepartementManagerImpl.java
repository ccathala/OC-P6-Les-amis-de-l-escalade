package com.charles.lesamisdelescalade.business.utils.bean.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.charles.lesamisdelescalade.business.utils.bean.DepartementManager;
import com.charles.lesamisdelescalade.consumer.bean.DepartementDao;
import com.charles.lesamisdelescalade.model.beans.Departement;

@Service
public class DepartementManagerImpl implements DepartementManager {
	
	@Autowired
	private DepartementDao departementDao;
	
	@Override
	public List<Departement> findAllDepartement() {
		return departementDao.findAllDepartement();
	}

	@Override
	public int getDepartementIdBySiteId(int siteId) {
		return departementDao.getDepartementIdBySiteId(siteId);
	}

}
