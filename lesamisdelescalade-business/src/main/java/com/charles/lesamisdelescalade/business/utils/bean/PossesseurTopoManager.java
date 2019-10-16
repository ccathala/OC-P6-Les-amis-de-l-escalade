package com.charles.lesamisdelescalade.business.utils.bean;

import com.charles.lesamisdelescalade.model.beans.PossesseurTopo;

public interface PossesseurTopoManager {

	void setTopoAvailability(PossesseurTopo possesseurTopo);

	void deleteOwnedTopo(int topoId, int utilisateurId);

}
