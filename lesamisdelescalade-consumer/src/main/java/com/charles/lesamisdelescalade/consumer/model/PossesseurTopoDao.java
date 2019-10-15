package com.charles.lesamisdelescalade.consumer.model;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.PossesseurTopo;

public interface PossesseurTopoDao {

	void addPossesseurTopo(PossesseurTopo possesseurTopo);

	void setTopoAvailability(PossesseurTopo possesseurTopo);

	void setTopoSharedState(PossesseurTopo possesseurTopo);

	void deleteOwnedTopo(int topoId, int utilisateurId);

	List<PossesseurTopo> findAllOwnedTopoByUtilisateurId(int utilisateurId);

}
