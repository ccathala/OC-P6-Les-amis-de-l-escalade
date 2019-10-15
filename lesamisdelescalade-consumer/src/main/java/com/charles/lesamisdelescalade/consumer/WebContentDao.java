package com.charles.lesamisdelescalade.consumer;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

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


public interface WebContentDao {

	//List<Departement> findAllDepartement();

	//int getDepartementIdBySiteId(int siteId);

//	void addSite(Site site) throws DuplicateKeyException;

//	Site findSite(int siteId);

//	List<Site> findAllSiteByDepartement(int departementId);

//	void addSecteur(Secteur secteur) throws DuplicateKeyException;

//	List<Secteur> findAllSecteurBySite(int siteId);

	List<Voie> findVoieBySite(int siteId);

	int getVoieCountBySecteur(int secteurId);

	String getSecteurMinCotation(int secteurId) throws NullPointerException;

	String getSecteurMaxCotation(int secteurId) throws NullPointerException;

	void addVoie(Voie voie);

	List<Longueur> findLongueurBySite(int siteId);

//	int getSiteIdBySecteurId(int secteurId);

	Voie findVoieByNomAndSecteur(String nom, int secteurId) throws EmptyResultDataAccessException;

	Voie findVoieByNumeroAndSecteur(int numero, int secteurId) throws EmptyResultDataAccessException;

	List<Voie> findAllVoieBySecteur(int secteurId);

	void addLongeur(Longueur longueur);

	Longueur findLongueurByNumeroAndVoie(int numero, int voieId);

//	int getSecteurIdByVoieId(int voieId) ;

	List<Cotation> findAllCotation();

	List<Longueur> findAllLongueurByVoie(int voieId);

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

//	List<Site> findAllSiteByMultiCritere(Object[] criteresSql, String sql);
//
//	List<Site> findAllSiteByName(String nom);

	List<Commentaire> findAllCommentaireBySite(int siteId);

	List<Utilisateur> findAllUtilisateurOnlyIdAndName();

	void addCommentaire(Commentaire commentaire);

	void updateCommentaire(Commentaire commentaire, int utilisateurId);

	void updateCommentaireStatus(int commentaireId);

	void addTopo(Topo topo);

	Topo findTopoBySiteIdAndAnneeParution(int siteId, Date anneeParution);

	List<ListTopoPageData> findAllTopoAndExtendedData();

	List<ListTopoPageData> findAllAvailableTopoAndExtendedData(int utilisateurId);

	void addReservation(ReservationTopo reservationTopo);

	ReservationTopo findReservationTopoByRequesterIdAndTopoIdAndStatusIsWaiting(int requesterId, int topoId);

	List<AccountPageData> getDataForAccountPageDataBySiteId(int departementId);

	void addPossesseurTopo(PossesseurTopo possesseurTopo);

	List<MyTopo> findAllMyTopoByUtilisateurId(int utilisateurId);

	void setTopoAvailability(PossesseurTopo possesseurTopo);

	void deleteOwnedTopo(int topoId, int utilisateurId);

	List<ReservationRequest> findAllReceivedReservationRequestByUtilisateurId(int utilisateurId);

	void updateReservationRequestStatusToAccepted(int reservationRequestId);

	void updateReservationRequestStatusToRefused(int reservationRequestId);

	void updateReservationRequestStatusToEnded(int reservationRequestId);

	void updateReservationRequestStatusToCancelled(int reservationRequestId);

	void setReservationVisibilityForOwnerToFalse(int reservationRequestId);

	void setReservationVisibilityForRequesterToFalse(int reservationRequestId);

	List<ReservationRequest> findAllSentReservationRequestByUtilisateurId(int utilisateurId);

	void setTopoSharedState(PossesseurTopo possesseurTopo);

	List<PossesseurTopo> findAllOwnedTopoByUtilisateurId(int utilisateurId);

	
	
	

}
