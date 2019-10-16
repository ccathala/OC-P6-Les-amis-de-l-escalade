package com.charles.lesamisdelescalade.business.webcontent.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.charles.lesamisdelescalade.business.utils.bean.SecteurManager;
import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.consumer.bean.CommentaireDao;
import com.charles.lesamisdelescalade.consumer.bean.DepartementDao;
import com.charles.lesamisdelescalade.consumer.bean.LongueurDao;
import com.charles.lesamisdelescalade.consumer.bean.PossesseurTopoDao;
import com.charles.lesamisdelescalade.consumer.bean.ReservationTopoDao;
import com.charles.lesamisdelescalade.consumer.bean.SecteurDao;
import com.charles.lesamisdelescalade.consumer.bean.SiteDao;
import com.charles.lesamisdelescalade.consumer.bean.TopoDao;
import com.charles.lesamisdelescalade.consumer.bean.UtilisateurDao;
import com.charles.lesamisdelescalade.consumer.bean.VoieDao;
import com.charles.lesamisdelescalade.consumer.dto.AccountPageDataDao;
import com.charles.lesamisdelescalade.consumer.dto.MyTopoDao;
import com.charles.lesamisdelescalade.consumer.dto.ReservationRequestDao;
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
import com.charles.lesamisdelescalade.model.dto.CriteresSql;
import com.charles.lesamisdelescalade.model.dto.ListTopoPageData;
import com.charles.lesamisdelescalade.model.dto.MyTopo;
import com.charles.lesamisdelescalade.model.dto.ReservationRequest;
import com.charles.lesamisdelescalade.model.dto.SitePageData;

/**
 * Implement methods used to add, update and display web content
 * @author Charles
 *
 */
@Service
public class WebContentManagerImpl implements WebContentManager {
	
	@Autowired
	private SiteDao siteDao;
	@Autowired
	private SecteurDao secteurDao;
	@Autowired
	private VoieDao voieDao;
	@Autowired
	private LongueurDao longueurDao;
	@Autowired
	private CommentaireDao commentaireDao;
	@Autowired
	private TopoDao topoDao;
	@Autowired
	private ReservationTopoDao reservationTopoDao;
	@Autowired
	private PossesseurTopoDao possesseurTopoDao;
	@Autowired
	private UtilisateurDao utilisateurDao;
	@Autowired
	private AccountPageDataDao accountPageDataDao;
	@Autowired
	private MyTopoDao MyTopoDao;
	@Autowired
	private ReservationRequestDao reservationRequestDao;
	@Autowired
	private SecteurManager secteurManager;
	
	
	/* Logger for LoginManagerImpl class */
	private static final Logger logger = LoggerFactory.getLogger(WebContentManagerImpl.class);
	
	
	// ==================================================================================================================
	//                                               WebContent Methods
	// ==================================================================================================================
	
	/**
	 * Set reservation status to accepted,
	 * set topo not available,
	 * set topo state to shared
	 * 
	 * @param int 
	 * @param PossesseurTopo 
	 */
	@Override
	public void acceptTopoReservation(int reservationId, PossesseurTopo possesseurTopo ) {
		
		// Set attributes
		possesseurTopo.setDisponible(false);
		possesseurTopo.setShared(true);
		// Update reservation status to accepted
		reservationTopoDao.updateReservationRequestStatusToAccepted(reservationId);
		// Set topo not available
		possesseurTopoDao.setTopoAvailability(possesseurTopo);
		// Set topo state to shared
		possesseurTopoDao.setTopoSharedState(possesseurTopo);
		
	}
	
	/**
	 * Set reservation status to over,
	 * set topo  available,
	 * set topo state to not shared
	 * 
	 * @param int 
	 * @param PossesseurTopo 
	 */
	@Override
	public void setOverTopoReservation(int reservationId, PossesseurTopo possesseurTopo ) {
		
		// Set attributes
		possesseurTopo.setDisponible(true);
		possesseurTopo.setShared(false);
		// Update reservation status to over
		reservationTopoDao.updateReservationRequestStatusToEnded(reservationId);
		// Set topo  available
		possesseurTopoDao.setTopoAvailability(possesseurTopo);
		// Set topo state to not shared
		possesseurTopoDao.setTopoSharedState(possesseurTopo);
	}
	
	/**
	 * Create SQL request according to criteria inputs
	 * 
	 * @param int
	 * @param int
	 * @param int
	 * @return CriteresSql
	 */
	private CriteresSql createSqlRequestToFindAllSiteByMultiCritere(int departementId, int cotationId,
			int secteurCount) {
		
		// Set attributes
		String sql = "";
		ArrayList<Integer> criteres = new ArrayList<Integer>();
		
		// Add this sql request if there is a departement input
		if (departementId > 0) {
			sql = "select * from site where departement_id=? ";
			criteres.add(departementId);
		}
		// Add this sql request if there is a cotation input
		if (cotationId > 0) {
			if (!sql.isEmpty()) {
				sql = sql + "intersect ";
			}
			sql = sql
					+ "select distinct site.* from site inner join secteur on site.id = secteur.site_id inner join voie on secteur.id=voie.secteur_id inner join longueur on voie.id = longueur.voie_id where longueur.cotation_id = ? ";
			criteres.add(cotationId);
		}
		// Add this sql request if there is a secteur count input
		if (secteurCount > 0) {
			if (!sql.isEmpty()) {
				sql = sql + "intersect ";
			}
			sql = sql
					+ "select site.* from site inner join secteur on site.id = secteur.site_id group by site.id having count(secteur.id)=?";
			criteres.add(secteurCount);
		}
		// Concat List into one column array
		Object[] criteresSql = criteres.toArray();
		
		return new CriteresSql(sql, criteresSql);

	}
	
	/**
	 * Convert list of user into HashMap
	 * 
	 * @param List<Utilisateur> 
	 * @return HashMap<Integer, String>
	 */
	private HashMap<Integer, String> convertUtilisateurListToHashMap(List<Utilisateur> utilisateurs){
		HashMap<Integer, String> map= new HashMap<Integer, String>();
		for (Utilisateur u: utilisateurs) map.put(u.getId(), u.getNom());
		return map;
		
	}
	
	/**
	 * Extract list of available topo id from available topo list
	 * 
	 * @param List<ListTopoPageData> 
	 * @return List<Integer> 
	 */
	@Override
	public List<Integer> extractAvalaibleTopoIdList(List<ListTopoPageData> avalaibleTopoAndExtendedDataList){
		List<Integer> avalaibleTopoIdList = new ArrayList<Integer>();
		for(ListTopoPageData avalaibleTopo : avalaibleTopoAndExtendedDataList) avalaibleTopoIdList.add(avalaibleTopo.getTopo_id());
		return avalaibleTopoIdList;
		
	}
	
	/**
	 * Convert Date to String
	 * 
	 * @param Date 
	 * @return String 
	 */
	private String convertDateToString(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return simpleDateFormat.format(date);		
	}

	
	// ==================================================================================================================
	//                                             Bean Model Site Methods
	// ==================================================================================================================

	/**
	 * Find all site according to multi criteria inputs or by name, search by name has priority
	 * 
	 * @param int 
	 * @param int 
	 * @param int 
	 * @param String 
	 * 
	 */
	@Override
	public List<Site> findAllSiteByMultiCritere(int departementId, int cotationId, int secteurCount, String nom) {

		// Check inputs presence
		if (departementId > 0 || cotationId > 0 || secteurCount > 0 || !nom.isEmpty()) {
			
			if (!nom.isEmpty()) {
				// Prior if name input do search by name
				return siteDao.findAllSiteByName("%"+nom+"%");
			} else {
				// Do research by multi criteria
				CriteresSql criteresSql = createSqlRequestToFindAllSiteByMultiCritere(departementId, cotationId,
						secteurCount);
				return siteDao.findAllSiteByMultiCritere(criteresSql.getCriteresSql(), criteresSql.getSql());
			}
		} else {
			// No input return all sites
			return siteDao.findAllSite();
		}
	}
	
	/**
	 * Add new site to database
	 * 
	 * @param Site
	 * @return Boolean
	 */
	@Override
	public Boolean addSite(Site site) {
		
		logger.info("Add site attempt");
		
		// Set attributes
		Boolean siteAddedWithSuccess;
				
		try {
			siteDao.addSite(site);
			// if input site name doesn't exist
			siteAddedWithSuccess = true;
			logger.debug("Site added with success - site id: " + site.getId() + " - site name: " + site.getNom());
		}catch (DuplicateKeyException e) { 
			// if input site name already exist
			siteAddedWithSuccess = false;
			logger.warn("Site add failed - Cause: site name already exist");
		}
		return siteAddedWithSuccess;
	}

	// ==================================================================================================================
	//                                             Bean Model Secteur Methods
	// ==================================================================================================================
	
	/**
	 * Add new secteur
	 * 
	 * @param Secteur
	 * @return Boolean
	 */
	@Override
	public Boolean addSecteur(Secteur secteur) {
		
		logger.info("Add secteur attempt");
		
		// Set Attributes
		Boolean secteurAddedWithSuccess;
		
		try {
			secteurDao.addSecteur(secteur);
			// if input secteur name doesn't exist
			secteurAddedWithSuccess = true;
			logger.debug("Secteur added with success - secteur id: " + secteur.getId() + " - secteur name: "
					+ secteur.getNom());
		} catch (DuplicateKeyException e) {
			// if input secteur name already exist
			secteurAddedWithSuccess = false;
			logger.warn("Secteur add failed - Cause: secteur name already exist");
		}
		return secteurAddedWithSuccess;
	}
	
	/**
	 * Get secteur min cotation
	 * 
	 * @param int
	 * @return String
	 */
	public String getMinCotation(int secteurId) {
		return voieDao.getSecteurMinCotation(secteurId);
		// TODO refactor
	}

	/**
	 * Get secteur max cotation
	 * @param int
	 * @return
	 */
	public String getMaxCotation(int secteurId) {
		return voieDao.getSecteurMaxCotation(secteurId);
		// TODO refactor
	}

	// ==================================================================================================================
	//                                             Bean Model Voie Methods
	// ==================================================================================================================

	/**
	 * Add new voie in database
	 * 
	 * @param voie
	 * @return
	 */
	@Override
	public String addVoie(Voie voie) {
		
		logger.info("Add voie attempt");
		
		// Set attributes
		String causeError = "";
		
		
		try {
			voieDao.findVoieByNumeroAndSecteur(voie.getNumero(), voie.getSecteur_id());
			// if input number already exist
			causeError = "numero";
			logger.debug("Voie add failed - Cause: voie numero already exist");
		} catch (EmptyResultDataAccessException e) {

		}
		try {
			voieDao.findVoieByNomAndSecteur(voie.getNom(), voie.getSecteur_id());
			// if input name already exist
			causeError = "nom";
			logger.debug("Voie add failed - Cause: voie name already exist");
		} catch (EmptyResultDataAccessException e) {

		}
		if (causeError.equals("")) {
			// No input error
			voieDao.addVoie(voie);
			logger.debug("Voie added with success - voie id: " + voie.getId() + " - voie numero: " + voie.getNumero()
					+ " - voie name: " + voie.getNom());
		}
		return causeError;
	}

	// ==================================================================================================================
	//                                             Bean Model Longueur Methods
	// ==================================================================================================================
	
	/**
	 * Add new longueur in database
	 * 
	 * @param longueur
	 * @return
	 */
	@Override
	public Boolean addLongueur(Longueur longueur) {
		
		logger.info("Add longueur attempt");
		
		// Set attributes
		Boolean NumeroIsAlreadyUsed;
		
		try {
			longueurDao.findLongueurByNumeroAndVoie(longueur.getNumero(), longueur.getVoie_id());
			// if input number already exist 
			NumeroIsAlreadyUsed = true;
			logger.debug("Longueur add failed - Cause: voie numero already exist");
					
		} catch (EmptyResultDataAccessException e) {
			// No input error
			longueurDao.addLongeur(longueur);
			NumeroIsAlreadyUsed = false;
			logger.debug("Longueur added with success - longueur id: " + longueur.getId() + " - longueur numero: " + longueur.getNumero());
		}
		return NumeroIsAlreadyUsed;
	}

	// ==================================================================================================================
	//                                             Bean Model Commentaire Methods
	// ==================================================================================================================

	/**
	 * Update commentaire in database
	 * 
	 * @param commentaire
	 * @param utilisateur
	 * @return
	 */
	@Override 
	public void updateCommentaire(Commentaire commentaire, Utilisateur utilisateur) {
		Date dNow = new Date();
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy.MM.dd 'à' hh:mm:ss");
		String enteteCommentaire = "Commentaire modifié par " + utilisateur.getNom() + " le " + ft.format(dNow) + "."  ;
		commentaire.setTexte(commentaire.getTexte() + "<br/>" + enteteCommentaire);
		commentaireDao.updateCommentaire(commentaire, utilisateur.getId());
	}
	
	// ==================================================================================================================
	//                                             Bean Model Utilisateur Methods
	// ==================================================================================================================
		
	/**
	 * Convert All user list into HashMap which only user id as key and name as content
	 * 
	 *  @return
	 */
	@Override
	public HashMap<Integer, String> getHashMapAllUtilisateurOnlyIdAndName(){
		return convertUtilisateurListToHashMap(utilisateurDao.findAllUtilisateurOnlyIdAndName());
	}
	
	// ==================================================================================================================
	//                                             Bean Model Topo Methods
	// ==================================================================================================================
	
	/**
	 * Add new Topo in database
	 * 
	 * @param topo
	 * @return
	 */
	@Override
	public Boolean addTopo(Topo topo) {
		
		// Set attributes
		Boolean topoAlreadyExist;
		
		try {
			topoDao.findTopoBySiteIdAndAnneeParution(topo.getSite_id(), topo.getDate_parution());
			// if topo already exist
			topoAlreadyExist = true;
		}catch (EmptyResultDataAccessException e) {
			// if no input error
			topoDao.addTopo(topo);
			topoAlreadyExist = false;
		}
		return topoAlreadyExist;
	}
	
	/**
	 * Get all topo list with extended data needed for account page
	 * 
	 * @return
	 */
	@Override
	public List<ListTopoPageData> findAllTopoAndExtendedData(){
		
		// Set attributes
		List<ListTopoPageData> topoList = topoDao.findAllTopoAndExtendedData();
		
		for(ListTopoPageData listTopoPageData: topoList) {
			// convert each date to string
			listTopoPageData.setDateParution(convertDateToString(listTopoPageData.getDate_parution()));
		}
		return topoList;
	}
	
	/**
	 *  Get all available topo list with extended data except topo owned by user,
	 *  needed for account page
	 *  
	 *  @param utilisateurId
	 *  @return
	 */
	@Override
	public List<ListTopoPageData> findAllAvailableTopoAndExtendedData(int utilisateurId){
		
		// Set attributes
		List<ListTopoPageData> availableTopoAndExtendedDataList = topoDao.findAllAvailableTopoAndExtendedData(utilisateurId);
		List<PossesseurTopo> OwnedTopoList = possesseurTopoDao.findAllOwnedTopoByUtilisateurId(utilisateurId);
		
		// Remove from list owned topo by user
		for(PossesseurTopo ownedTopo : OwnedTopoList) {
			Iterator<ListTopoPageData> AvailableTopoListIterator = availableTopoAndExtendedDataList.iterator();
			while(AvailableTopoListIterator.hasNext()) {
				int iteratorTopoId = AvailableTopoListIterator.next().getTopo_id();
				if(ownedTopo.getTopo_id() == iteratorTopoId) {
					AvailableTopoListIterator.remove();
				}
			}
		}
		return availableTopoAndExtendedDataList;
	}
	
	// ==================================================================================================================
	//                                             Bean Model ReservationTopo Methods
	// ==================================================================================================================
	
	/**
	 * Add new reservation in database
	 * 
	 * @param reservationTopo
	 * @return
	 */
	@Override
	public Boolean addReservation(ReservationTopo reservationTopo ) {
		
		// Set attributes
		Boolean reservationAlreadyAsked;
		
		try {
			reservationTopoDao.findReservationTopoByRequesterIdAndTopoIdAndStatusIsWaiting(
					reservationTopo.getDemandeur_id(), reservationTopo.getReservation_topo_id());
			//reservation for this topo is already done
			reservationAlreadyAsked = true;
		} catch (EmptyResultDataAccessException e) {
			// if reservation for this topo is not already done
			reservationAlreadyAsked = false;
			reservationTopoDao.addReservation(reservationTopo);
		}
		return reservationAlreadyAsked;
	}
	
	
	// ==================================================================================================================
	//                                             Bean PossesseurTopo Methods
	// ==================================================================================================================
	
	/**
	 * Add new topo owner in database
	 * 
	 * @param possesseurTopo
	 * @return
	 */
	@Override
	public Boolean addPossesseurTopo(PossesseurTopo possesseurTopo) {
		
		// Set attributes
		Boolean posseseurTopoAddedSuccesfully;
		possesseurTopo.setDisponible(false);
		
		try {
			possesseurTopoDao.addPossesseurTopo(possesseurTopo);
			// User not already own this topo
			posseseurTopoAddedSuccesfully = true;
		} catch (DuplicateKeyException e) {
			// User already own this topo
			posseseurTopoAddedSuccesfully = false;
		}
		return posseseurTopoAddedSuccesfully;
		
		
	}
		
	// ==================================================================================================================
	//                                             DTO AccountPageData Methods
	// ==================================================================================================================
	
	/**
	 * Get topo list filtered by departement for topo dropdown on account page
	 * 
	 * @param departementId
	 * @return
	 */
	@Override
	public List<AccountPageData> getTopoListForAccountPageFilteredByDepartementId(int departementId){
		
		// Set attributes
		List<AccountPageData> accountPageDataList = accountPageDataDao.getTopoListForAccountPageFilteredByDepartementId(departementId);
		
		// Convert Date to String for each topo parution date
		for(AccountPageData accountPageData : accountPageDataList) {
			accountPageData.setDateParution(convertDateToString(accountPageData.getDate_parution()));
		}
		return accountPageDataList;
	}
	
	// ==================================================================================================================
	//                                             DTO MyTopo Methods
	// ==================================================================================================================
	
	/**
	 * Get list of all owned topo by user needed for account page
	 * 
	 * @param
	 * @result
	 */
	@Override
	public List<MyTopo> findAllMyTopoByUtilisateurId(int utilisateurId){
		
		// Set attributes
		List<MyTopo> myTopoList = MyTopoDao.findAllMyTopoByUtilisateurId(utilisateurId);
		
		// Convert Date to String for each topo parution date
		for(MyTopo myTopo : myTopoList ) {
			myTopo.setDateParution(convertDateToString(myTopo.getDate_parution()));
		}
		return myTopoList;
	}
	
	// ==================================================================================================================
	//                                             DTO ReservationRequest Methods
	// ==================================================================================================================
	
	/**
	 * Get list of all visible received reservation for user, needed for account page
	 * 
	 * @param utilisateurId
	 * @return
	 */
	@Override
	public List<ReservationRequest> findAllReceivedReservationRequestByUtilisateurId(int utilisateurId){
		
		// Set attributes
		List<ReservationRequest> reservationRequestList = reservationRequestDao.findAllReceivedReservationRequestByUtilisateurId(utilisateurId);
		
		// Convert Date to String for each topo parution date
		for(ReservationRequest reservationRequest : reservationRequestList) {
			reservationRequest.setDateParution(convertDateToString(reservationRequest.getDate_parution()));
		}
		return reservationRequestList;
	}
	
	/**
	 * Get list of all visible sent reservation for user, needed for account page
	 * 
	 * @param utilisateurId
	 * @return
	 */
	@Override
	public List<ReservationRequest> findAllSentReservationRequestByUtilisateurId(int utilisateurId){
		List<ReservationRequest> reservationRequestList = reservationRequestDao.findAllSentReservationRequestByUtilisateurId(utilisateurId);
		for(ReservationRequest reservationRequest : reservationRequestList) {
			reservationRequest.setDateParution(convertDateToString(reservationRequest.getDate_parution()));
		}
		return reservationRequestList;
	}
	
	
	
	// ==================================================================================================================
	//                                             DTO SitePageData Methods
	// ==================================================================================================================
		
	/**
	 * Set bean SitePageData with site, secteur, voie and longueur data according to
	 * the choosen site
	 * 
	 * @param siteId
	 * @return sitePageData
	 */
	@Override
	public SitePageData setSitePageData(int siteId) {
		// TODO amélioration
		SitePageData sitePageData = new SitePageData();
		sitePageData.setSite(siteDao.findSiteById(siteId));
		sitePageData.setSecteurs(secteurManager.getAllSecteurBySite(siteId));
		sitePageData.setVoies(voieDao.findAllVoieBySite(siteId));
		sitePageData.setLongueurs(longueurDao.findAllLongueurBySite(siteId));
		for (Secteur secteur : sitePageData.getSecteurs()) {
			secteur.setVoiesCount(voieDao.getVoieCountBySecteur(secteur.getId()));
			try {
				secteur.setCotationMin(getMinCotation(secteur.getId()));
				secteur.setCotationMax(getMaxCotation(secteur.getId()));
			} catch (NullPointerException e) {
				secteur.setCotationMin("NA");
				secteur.setCotationMax("NA");
			}
		}

		return sitePageData;
	}

}
