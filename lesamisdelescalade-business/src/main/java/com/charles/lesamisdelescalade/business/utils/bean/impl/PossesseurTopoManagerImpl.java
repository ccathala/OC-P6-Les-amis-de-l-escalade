package com.charles.lesamisdelescalade.business.utils.bean.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.charles.lesamisdelescalade.business.utils.bean.PossesseurTopoManager;
import com.charles.lesamisdelescalade.consumer.bean.PossesseurTopoDao;
import com.charles.lesamisdelescalade.model.beans.PossesseurTopo;

@Service
public class PossesseurTopoManagerImpl implements PossesseurTopoManager {
	
	@Autowired
	private PossesseurTopoDao possesseurTopoDao;
	
	@Override
	public void setTopoAvailability(PossesseurTopo possesseurTopo) {
		possesseurTopoDao.setTopoAvailability(possesseurTopo);
	}
	
	@Override
	public void deleteOwnedTopo(int topoId, int utilisateurId) {
		possesseurTopoDao.deleteOwnedTopo(topoId, utilisateurId);
	}

}
