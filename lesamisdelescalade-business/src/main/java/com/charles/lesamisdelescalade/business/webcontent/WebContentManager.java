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
	
	public SitePageData setSitePageData(int siteId);
	
	public Boolean addSite(Site site);
	
//	public List<Site> findAllSiteByDepartement(int departementId);
	
	public Boolean addSecteur(Secteur secteur);
	
//	public List<Secteur> getAllSecteurBySite(int siteId);
	
	public String addVoie (Voie voie);
	
//	public int getDepartementIdBySiteId(int siteId);
	
//	public List<Departement> findAllDepartement();

//	int getSiteIdBySecteurId(int secteurId);

//	List<Voie> findAllVoieBySecteur(int secteurId);

	Boolean addLongueur(Longueur longueur);

//	int getSecteurIdByVoieId(int voieId);

//	List<Cotation> findAllCotation();

//	List<Longueur> findAllLongueurByVoie(int voieId);

//	void addOfficialTagOnSite(int siteId);
//
//	void deleteOfficialTagOnSite(int siteId);

//	List<Site> findAllSite();
//
//	List<Site> findAllSiteByCotation(int cotationId);
//
//	List<Site> findAllSiteBySecteurCount(int secteurCount);
//
//	List<Integer> getSecteurCountBySite();

	List<Site> findAllSiteByMultiCritere(int departementId, int cotationId, int secteurCount, String nom);

//	List<Commentaire> findAllCommentaireBySite(int siteId);

	HashMap<Integer, String> getHashMapAllUtilisateurOnlyIdAndName();

//	void addCommentaire(Commentaire commentaire);

	void updateCommentaire(Commentaire commentaire, Utilisateur utilisateur);

//	void updateCommentaireStatus(int commentaireId);

	Boolean addTopo(Topo topo);

	List<ListTopoPageData> findAllTopoAndExtendedData();

	List<ListTopoPageData> findAllAvailableTopoAndExtendedData(int utilisateurId);
	
	List<Integer> extractAvalaibleTopoIdList(List<ListTopoPageData> avalaibleTopoAndExtendedDataList);

	Boolean addReservation(ReservationTopo reservationTopo);

	List<AccountPageData> getDataForAccountPageDataBySiteId(int departementId);

	Boolean addPossesseurTopo(PossesseurTopo possesseurTopo);

	List<MyTopo> findAllMyTopoByUtilisateurId(int utilisateurId);

//	void setTopoAvailability(PossesseurTopo possesseurTopo);
//
//	void deleteOwnedTopo(int topoId, int utilisateurId);

	List<ReservationRequest> findAllReceivedReservationRequestByUtilisateurId(int utilisateurId);

//	void updateReservationRequestStatusToAccepted(int reservationRequestId);
//
//	void updateReservationRequestStatusToRefused(int reservationRequestId);
//
//	void updateReservationRequestStatusToEnded(int reservationRequestId);
//
//	void updateReservationRequestStatusToCancelled(int reservationRequestId);

//	void setReservationVisibilityForOwnerToFalse(int reservationRequestId);
//
//	void setReservationVisibilityForRequesterToFalse(int reservationRequestId);

	List<ReservationRequest> findAllSentReservationRequestByUtilisateurId(int utilisateurId);

	void acceptTopoReservation(int reservationId, PossesseurTopo possesseurTopo);

	void setOverTopoReservation(int reservationId, PossesseurTopo possesseurTopo);

}
