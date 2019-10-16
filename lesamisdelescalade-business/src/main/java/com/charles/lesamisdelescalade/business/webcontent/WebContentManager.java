package com.charles.lesamisdelescalade.business.webcontent;

import java.util.HashMap;
import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Commentaire;
import com.charles.lesamisdelescalade.model.beans.Cotation;
import com.charles.lesamisdelescalade.model.beans.Departement;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.PossesseurTopo;
import com.charles.lesamisdelescalade.model.beans.ReservationTopo;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Topo;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.beans.Voie;
import com.charles.lesamisdelescalade.model.dto.AccountPageData;
import com.charles.lesamisdelescalade.model.dto.ListTopoPageData;
import com.charles.lesamisdelescalade.model.dto.MyTopo;
import com.charles.lesamisdelescalade.model.dto.ReservationRequest;
import com.charles.lesamisdelescalade.model.dto.SitePageData;


public interface WebContentManager {
	
	// Site methods
	Boolean addSite(Site site);
	List<Site> findAllSiteByMultiCritere(int departementId, int cotationId, int secteurCount, String nom);
	
	// Secteur methods
	Boolean addSecteur(Secteur secteur);
	
	// Voie methods
	String addVoie (Voie voie);
	
	// Longueur methods
	Boolean addLongueur(Longueur longueur);
	
	// Commentaire methods
	void updateCommentaire(Commentaire commentaire, Utilisateur utilisateur);
	
	// PossesseurTopo
	Boolean addPossesseurTopo(PossesseurTopo possesseurTopo);
	
	// ReservationTopo methods
	Boolean addReservation(ReservationTopo reservationTopo);
	
	// Topo methods 
	Boolean addTopo(Topo topo);
	List<ListTopoPageData> findAllAvailableTopoAndExtendedData(int utilisateurId);
	List<ListTopoPageData> findAllTopoAndExtendedData();
	
	// DTO SitePageData methods
	SitePageData setSitePageData(int siteId);
	
	// DTO AccountPageData
	List<AccountPageData> getTopoListForAccountPageFilteredByDepartementId(int departementId);
	
	// DTO MyTopo
	List<MyTopo> findAllMyTopoByUtilisateurId(int utilisateurId);
	
	// DTO ReservationRequest
	List<ReservationRequest> findAllReceivedReservationRequestByUtilisateurId(int utilisateurId);
	List<ReservationRequest> findAllSentReservationRequestByUtilisateurId(int utilisateurId);

	
	// WebContent methods	
	HashMap<Integer, String> getHashMapAllUtilisateurOnlyIdAndName();
	List<Integer> extractAvalaibleTopoIdList(List<ListTopoPageData> avalaibleTopoAndExtendedDataList);
	void acceptTopoReservation(int reservationId, PossesseurTopo possesseurTopo);
	void setOverTopoReservation(int reservationId, PossesseurTopo possesseurTopo);
		

	

	

	

	

	
	

}
