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
 * 
 * @author Charles
 *
 */
@Service
public class WebContentManagerImpl implements WebContentManager {
	
	@Autowired
	private DepartementDao departementDao;
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
	//                                               Account Page Data
	// ==================================================================================================================
		
		@Override
		public void acceptTopoReservation(int reservationId, PossesseurTopo possesseurTopo ) {
			possesseurTopo.setDisponible(false);
			possesseurTopo.setShared(true);
			reservationTopoDao.updateReservationRequestStatusToAccepted(reservationId);
			possesseurTopoDao.setTopoAvailability(possesseurTopo);
			possesseurTopoDao.setTopoSharedState(possesseurTopo);
			
		}
		
		@Override
		public void setOverTopoReservation(int reservationId, PossesseurTopo possesseurTopo ) {
			possesseurTopo.setDisponible(true);
			possesseurTopo.setShared(false);
			reservationTopoDao.updateReservationRequestStatusToEnded(reservationId);
			possesseurTopoDao.setTopoAvailability(possesseurTopo);
			possesseurTopoDao.setTopoSharedState(possesseurTopo);
		}

	
	// ==================================================================================================================
	//                                             Bean Model Site Methods
	// ==================================================================================================================

	@Override
	public List<Site> findAllSiteByMultiCritere(int departementId, int cotationId, int secteurCount, String nom) {

		if (departementId > 0 || cotationId > 0 || secteurCount > 0 || !nom.isEmpty()) {
			if (!nom.isEmpty()) {
				return siteDao.findAllSiteByName("%"+nom+"%");

			} else {

				CriteresSql criteresSql = createSqlRequestToFindAllSiteByMultiCritere(departementId, cotationId,
						secteurCount);
				return siteDao.findAllSiteByMultiCritere(criteresSql.getCriteresSql(), criteresSql.getSql());
			}
		} else {
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
		Boolean siteAddedWithSuccess;
		logger.info("Add site attempt");
		try {
			siteDao.addSite(site);
			siteAddedWithSuccess = true;
			logger.debug("Site added with success - site id: " + site.getId() + " - site name: " + site.getNom());
		} catch (DuplicateKeyException e) {
			siteAddedWithSuccess = false;
			logger.warn("Site add failed - Cause: site name already exist");
		}
		return siteAddedWithSuccess;
	}

	// ==================================================================================================================
	//                                             Bean Model Secteur Methods
	// ==================================================================================================================
	
	/**
	 * Add new secteur to database
	 * 
	 * @param Secteur
	 * @return Boolean
	 */
	@Override
	public Boolean addSecteur(Secteur secteur) {
		Boolean secteurAddedWithSuccess;
		logger.info("Add secteur attempt");
		try {
			secteurDao.addSecteur(secteur);
			secteurAddedWithSuccess = true;
			logger.debug("Secteur added with success - secteur id: " + secteur.getId() + " - secteur name: "
					+ secteur.getNom());
		} catch (DuplicateKeyException e) {
			secteurAddedWithSuccess = false;
			logger.warn("Secteur add failed - Cause: secteur name already exist");
		}
		return secteurAddedWithSuccess;
	}
	
	
	public String getMinCotation(int secteurId) {
		return voieDao.getSecteurMinCotation(secteurId);
		// TODO refactor
	}

	public String getMaxCotation(int secteurId) {
		return voieDao.getSecteurMaxCotation(secteurId);
		// TODO refactor
	}

	// ==================================================================================================================
	//                                             Bean Model Voie Methods
	// ==================================================================================================================

	/**
	 * Add new voie to database
	 * 
	 * @param Voie
	 * @return String
	 */
	@Override
	public String addVoie(Voie voie) {
		String causeError = "";
		logger.info("Add voie attempt");
		try {
			voieDao.findVoieByNumeroAndSecteur(voie.getNumero(), voie.getSecteur_id());
			causeError = "numero";
			logger.debug("Voie add failed - Cause: voie numero already exist");
		} catch (EmptyResultDataAccessException e) {

		}
		try {
			voieDao.findVoieByNomAndSecteur(voie.getNom(), voie.getSecteur_id());
			causeError = "nom";
			logger.debug("Voie add failed - Cause: voie name already exist");
		} catch (EmptyResultDataAccessException e) {

		}
		if (causeError.equals("")) {
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
	 * Add new longueur to database
	 * 
	 * @param Longueur
	 * @return Boolean
	 */
	@Override
	public Boolean addLongueur(Longueur longueur) {
		Boolean NumeroIsAlreadyUsed;
		logger.info("Add longueur attempt");
		try {
			longueurDao.findLongueurByNumeroAndVoie(longueur.getNumero(), longueur.getVoie_id());
			NumeroIsAlreadyUsed = true;
			logger.debug("Longueur added with success - longueur id: " + longueur.getId() + " - longueur numero: "
					+ longueur.getNumero());
		} catch (EmptyResultDataAccessException e) {
			longueurDao.addLongeur(longueur);
			NumeroIsAlreadyUsed = false;
			logger.debug("Longueur add failed - Cause: voie numero already exist");
		}

		return NumeroIsAlreadyUsed;
	}

	// ==================================================================================================================
	//                                             Bean Model Departement Methods
	// ==================================================================================================================
	

	
	// ==================================================================================================================
	//                                             Bean Model Commentaire Methods
	// ==================================================================================================================

	@Override 
	public void updateCommentaire(Commentaire commentaire, Utilisateur utilisateur) {
		Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy.MM.dd 'à' hh:mm:ss");
		String enteteCommentaire = "Commentaire modifié par " + utilisateur.getNom() + " le " + ft.format(dNow) + "."  ;
		commentaire.setTexte(commentaire.getTexte() + "<br/>" + enteteCommentaire);
		commentaireDao.updateCommentaire(commentaire, utilisateur.getId());
	}
	

	
	// ==================================================================================================================
	//                                             Bean Model Utilisateur Methods
	// ==================================================================================================================
		
	@Override
	public HashMap<Integer, String> getHashMapAllUtilisateurOnlyIdAndName(){
		return convertUtilisateurListToHashMap(utilisateurDao.findAllUtilisateurOnlyIdAndName());
	}
	
	// ==================================================================================================================
	//                                             Bean Model Topo Methods
	// ==================================================================================================================
	
	@Override
	public Boolean addTopo(Topo topo) {
		Boolean topoAlreadyExist;
		try {
			topoDao.findTopoBySiteIdAndAnneeParution(topo.getSite_id(), topo.getDate_parution());
			topoAlreadyExist = true;
		}catch (EmptyResultDataAccessException e) {
			topoDao.addTopo(topo);
			topoAlreadyExist = false;
		}
		return topoAlreadyExist;
	}
	
	@Override
	public List<ListTopoPageData> findAllTopoAndExtendedData(){
		List<ListTopoPageData> topoList = topoDao.findAllTopoAndExtendedData();
		for(ListTopoPageData listTopoPageData: topoList) {
			listTopoPageData.setDateParution(convertDateToString(listTopoPageData.getDate_parution()));
		
		}
		return topoList;
	}
	
	@Override
	public List<ListTopoPageData> findAllAvailableTopoAndExtendedData(int utilisateurId){
		List<ListTopoPageData> availableTopoAndExtendedDataList = topoDao.findAllAvailableTopoAndExtendedData(utilisateurId);
		List<PossesseurTopo> OwnedTopoList = possesseurTopoDao.findAllOwnedTopoByUtilisateurId(utilisateurId);
		
		
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
	
	@Override
	public Boolean addReservation(ReservationTopo reservationTopo ) {
		Boolean reservationAlreadyAsked;
		try {
			reservationTopoDao.findReservationTopoByRequesterIdAndTopoIdAndStatusIsWaiting(reservationTopo.getDemandeur_id(), reservationTopo.getReservation_topo_id());
			reservationAlreadyAsked = true;
		} catch (EmptyResultDataAccessException e) {
			reservationAlreadyAsked = false;
			reservationTopoDao.addReservation(reservationTopo);
		}
		return reservationAlreadyAsked;
	}
	
	
	// ==================================================================================================================
	//                                             Bean Model PossesseurTopo Methods
	// ==================================================================================================================
	
	@Override
	public Boolean addPossesseurTopo(PossesseurTopo possesseurTopo) {
		Boolean posseseurTopoAddedSuccesfully;
		possesseurTopo.setDisponible(false);
		try {
			possesseurTopoDao.addPossesseurTopo(possesseurTopo);
			posseseurTopoAddedSuccesfully = true;
		} catch (DuplicateKeyException e) {
			posseseurTopoAddedSuccesfully = false;
		}
		return posseseurTopoAddedSuccesfully;
		
		
	}
	
	@Override
	public void setTopoAvailability(PossesseurTopo possesseurTopo) {
		possesseurTopoDao.setTopoAvailability(possesseurTopo);
	}
	
	@Override
	public void deleteOwnedTopo(int topoId, int utilisateurId) {
		possesseurTopoDao.deleteOwnedTopo(topoId, utilisateurId);
	}
	
	/* ========================================================================== */
	/* AccountPageData DTO methods */
	/* ========================================================================== */
	
	@Override
	public List<AccountPageData> getDataForAccountPageDataBySiteId(int departementId){
		List<AccountPageData> accountPageDataList = accountPageDataDao.getDataForAccountPageDataBySiteId(departementId);
		for(AccountPageData accountPageData : accountPageDataList) {
			accountPageData.setDateParution(convertDateToString(accountPageData.getDate_parution()));
		}
		return accountPageDataList;
	}
	
	/* ========================================================================== */
	/* MyTopo DTO methods */
	/* ========================================================================== */
	
	@Override
	public List<MyTopo> findAllMyTopoByUtilisateurId(int utilisateurId){
		List<MyTopo> myTopoList = MyTopoDao.findAllMyTopoByUtilisateurId(utilisateurId);
		for(MyTopo myTopo : myTopoList ) {
			myTopo.setDateParution(convertDateToString(myTopo.getDate_parution()));
		}
		return myTopoList;
	}
	
	/* ========================================================================== */
	/* ReservationRequest DTO methods */
	/* ========================================================================== */
	
	@Override
	public List<ReservationRequest> findAllReceivedReservationRequestByUtilisateurId(int utilisateurId){
		List<ReservationRequest> reservationRequestList = reservationRequestDao.findAllReceivedReservationRequestByUtilisateurId(utilisateurId);
		for(ReservationRequest reservationRequest : reservationRequestList) {
			reservationRequest.setDateParution(convertDateToString(reservationRequest.getDate_parution()));
		}
		return reservationRequestList;
	}
	
	@Override
	public List<ReservationRequest> findAllSentReservationRequestByUtilisateurId(int utilisateurId){
		List<ReservationRequest> reservationRequestList = reservationRequestDao.findAllSentReservationRequestByUtilisateurId(utilisateurId);
		for(ReservationRequest reservationRequest : reservationRequestList) {
			reservationRequest.setDateParution(convertDateToString(reservationRequest.getDate_parution()));
		}
		return reservationRequestList;
	}
	
	
	
	
	
	/* ========================================================================== */
	/* Utils methods */
	/* ========================================================================== */

	
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
	
	/**
	 * 
	 * @param departementId
	 * @param cotationId
	 * @param secteurCount
	 * @return
	 */
	private CriteresSql createSqlRequestToFindAllSiteByMultiCritere(int departementId, int cotationId,
			int secteurCount) {
		String sql = "";
		ArrayList<Integer> criteres = new ArrayList<Integer>();
		if (departementId > 0) {
			sql = "select * from site where departement_id=? ";
			criteres.add(departementId);
		}
		if (cotationId > 0) {
			if (!sql.isEmpty()) {
				sql = sql + "intersect ";
			}
			sql = sql
					+ "select distinct site.* from site inner join secteur on site.id = secteur.site_id inner join voie on secteur.id=voie.secteur_id inner join longueur on voie.id = longueur.voie_id where longueur.cotation_id = ? ";
			criteres.add(cotationId);
		}
		if (secteurCount > 0) {
			if (!sql.isEmpty()) {
				sql = sql + "intersect ";
			}
			sql = sql
					+ "select site.* from site inner join secteur on site.id = secteur.site_id group by site.id having count(secteur.id)=?";
			criteres.add(secteurCount);
		}
		Object[] criteresSql = criteres.toArray();
		return new CriteresSql(sql, criteresSql);

	}
	
	/**
	 * 
	 * @param utilisateurs
	 * @return
	 */
	private HashMap<Integer, String> convertUtilisateurListToHashMap(List<Utilisateur> utilisateurs){
		HashMap<Integer, String> map= new HashMap<Integer, String>();
		for (Utilisateur u: utilisateurs) map.put(u.getId(), u.getNom());
		return map;
		
	}
	
	
	@Override
	public List<Integer> extractAvalaibleTopoIdList(List<ListTopoPageData> avalaibleTopoAndExtendedDataList){
		List<Integer> avalaibleTopoIdList = new ArrayList<Integer>();
		for(ListTopoPageData avalaibleTopo : avalaibleTopoAndExtendedDataList) avalaibleTopoIdList.add(avalaibleTopo.getTopo_id());
		return avalaibleTopoIdList;
		
	}
	
	private String convertDateToString(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return simpleDateFormat.format(date);		
	}
	
	

	
	
	
	
}
