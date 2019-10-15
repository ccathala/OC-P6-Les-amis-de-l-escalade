package com.charles.lesamisdelescalade.consumer.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charles.lesamisdelescalade.consumer.WebContentDao;
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

@Repository
public class WebContentDaoImpl implements WebContentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	

	/*
	 * =============================================================================
	 * =============== DEPARTEMENT *
	 * =============================================================================
	 * ===============
	 */

	

	/*
	 * =============================================================================
	 * =============== SITE *
	 * =============================================================================
	 * ===============
	 */

	

	/*
	 * =============================================================================
	 * =============== SECTEUR *
	 * =============================================================================
	 * ===============
	 */

	

	/*
	 * =============================================================================
	 * =============== VOIE *
	 * =============================================================================
	 * ===============
	 */

	

	/*
	 * =============================================================================
	 * =============== LONGUEUR *
	 * =============================================================================
	 * ===============
	 */

	

	/*
	 * =============================================================================
	 * =============== COMMENTAIRE *
	 * =============================================================================
	 * ===============
	 */

	@Override
	public List<Commentaire> findAllCommentaireBySite(int siteId) {
		return jdbcTemplate.query("select * from commentaire where site_id=? order by id asc", new Object[] { siteId },
				new BeanPropertyRowMapper<Commentaire>(Commentaire.class));
	}

	@Override
	@Transactional
	public void addCommentaire(Commentaire commentaire) {
		jdbcTemplate.update(
				"insert into commentaire (texte, date, status_id, utilisateur_id, site_id) values(?,current_timestamp(0),?,?,?)",
				commentaire.getTexte(), commentaire.getStatus_id(), commentaire.getUtilisateur_id(),
				commentaire.getSite_id());
	}

	@Override
	@Transactional
	public void updateCommentaire(Commentaire commentaire, int utilisateurId) {
		jdbcTemplate.update("update commentaire set texte= ?, status_id=? where id=?", commentaire.getTexte(),
				commentaire.getStatus_id(), utilisateurId);
	}

	@Override
	@Transactional
	public void updateCommentaireStatus(int commentaireId) {
		jdbcTemplate.update("update commentaire set status_id=? where id=?", 3, commentaireId);
	}

	/*
	 * =============================================================================
	 * =============== UTILISATEUR *
	 * =============================================================================
	 * ===============
	 */

	@Override
	public List<Utilisateur> findAllUtilisateurOnlyIdAndName() {
		return jdbcTemplate.query("select id, nom from utilisateur",
				new BeanPropertyRowMapper<Utilisateur>(Utilisateur.class));
	}

	/*
	 * =============================================================================
	 * =============== TOPO *
	 * =============================================================================
	 * ===============
	 */

	@Override
	@Transactional
	public void addTopo(Topo topo) {
		jdbcTemplate.update("insert into topo (nom, description, date_parution, site_id) values (?,?,?,?)",
				topo.getNom(), topo.getDescription(), topo.getDate_parution(), topo.getSite_id());
	}

	@Override
	public Topo findTopoBySiteIdAndAnneeParution(int siteId, Date dateParution) {
		return (Topo) jdbcTemplate.queryForObject(
				"select date_parution, site_id from topo where date_parution=? and site_id=?",
				new Object[] { dateParution, siteId }, new BeanPropertyRowMapper<Topo>(Topo.class));
	}

	@Override
	public List<ListTopoPageData> findAllTopoAndExtendedData() {
		return jdbcTemplate.query(
				"select "
				+ "departement.code as \"code_postal\", "
				+ "departement.nom as departement, "
				+ "topo.id as topo_id, "
				+ "topo.nom as topo_nom, "
				+ "site.nom as site, "
				+ "topo.description, "
				+ "topo.date_parution::date "
				+ "from topo "
				+ "inner join site on topo.site_id = site.id "
				+ "inner join departement on site.departement_id = departement.id",
				new BeanPropertyRowMapper<ListTopoPageData>(ListTopoPageData.class));
	}

	@Override
	public List<ListTopoPageData> findAllAvailableTopoAndExtendedData(int utilisateurId) {
		return jdbcTemplate.query(
				"select "
				+ "id as possesseur_id, "
				+ "nom as possesseur_nom, "
				+ "topo_id, "
				+ "disponible "
				+ "from utilisateur "
				+ "inner join possesseur_topo on utilisateur.id = possesseur_topo.utilisateur_id "
				+ "where possesseur_topo.disponible = ? "
				+ "and id != ?",
				new Object[] {true, utilisateurId},
				new BeanPropertyRowMapper<ListTopoPageData>(ListTopoPageData.class));
	}

	/*
	 * =============================================================================
	 * =============== RESERVATION
	 * =============================================================================
	 * ===============
	 */

	@Override
	@Transactional
	public void addReservation(ReservationTopo reservationTopo) {
		jdbcTemplate.update(
				"insert into reservation_topo(reservation_topo_id, possesseur_id, demandeur_id, status_id, visible_for_owner, visible_for_requester) values(?,?,?,?,?,?)",
				reservationTopo.getReservation_topo_id(), reservationTopo.getPossesseur_id(),
				reservationTopo.getDemandeur_id(), 1, true, true);
	}

	@Override
	public ReservationTopo findReservationTopoByRequesterIdAndTopoIdAndStatusIsWaiting(int requesterId, int topoId) {
		return jdbcTemplate.queryForObject(
				"select * from reservation_topo where reservation_topo_id=? and demandeur_id = ? and status_id = ?",
				new Object[] { topoId, requesterId, 1 },
				new BeanPropertyRowMapper<ReservationTopo>(ReservationTopo.class));
	}
	
	@Override
	@Transactional
	public void setReservationVisibilityForOwnerToFalse(int reservationRequestId) {
		jdbcTemplate.update("update reservation_topo set visible_for_owner = ? where id = ?" , false, reservationRequestId);
	}
	
	@Override
	@Transactional
	public void setReservationVisibilityForRequesterToFalse(int reservationRequestId) {
		jdbcTemplate.update("update reservation_topo set visible_for_requester = ? where id = ?", false, reservationRequestId);
	}

	/*
	 * =============================================================================
	 * =============== POSSESSEUR TOPO
	 * =============================================================================
	 * ===============
	 */

	@Override
	@Transactional
	public void addPossesseurTopo(PossesseurTopo possesseurTopo) {
		jdbcTemplate.update("INSERT INTO possesseur_topo (topo_id, utilisateur_id, disponible, shared) VALUES(?, ?, ?, ?)",
				possesseurTopo.getTopo_id(), possesseurTopo.getUtilisateur_id(), possesseurTopo.getDisponible(), false);
	}

	@Override
	@Transactional
	public void setTopoAvailability(PossesseurTopo possesseurTopo) {
		jdbcTemplate.update("update possesseur_topo set disponible = ? where topo_id=? and utilisateur_id=?",
				possesseurTopo.getDisponible(), possesseurTopo.getTopo_id(), possesseurTopo.getUtilisateur_id());
	}
	
	@Override
	@Transactional
	public void setTopoSharedState(PossesseurTopo possesseurTopo) {
		jdbcTemplate.update("update possesseur_topo set shared = ? where topo_id=? and utilisateur_id=?",
				possesseurTopo.getShared(), possesseurTopo.getTopo_id(), possesseurTopo.getUtilisateur_id());
	}

	@Override
	@Transactional
	public void deleteOwnedTopo(int topoId, int utilisateurId) {
		jdbcTemplate.update("delete from possesseur_topo where topo_id = ? and utilisateur_id = ?", topoId,
				utilisateurId);
	}
	
	@Override
	public List<PossesseurTopo> findAllOwnedTopoByUtilisateurId(int utilisateurId){
		return jdbcTemplate.query("select * from possesseur_topo where utilisateur_id = ?",
				new Object[] {utilisateurId},
				new BeanPropertyRowMapper<PossesseurTopo>(PossesseurTopo.class));
	}

	/*
	 * =============================================================================
	 * =============== DTO ACCOUNTPAGEDATA
	 * =============================================================================
	 * ===============
	 */

	@Override
	public List<AccountPageData> getDataForAccountPageDataBySiteId(int departementId) {
		return jdbcTemplate.query(
				"select topo.id as topo_id, topo.nom as topo_nom, site.nom as site_nom, topo.date_parution::date from topo inner join site on topo.site_id = site.id where site.departement_id = ?",
				new Object[] { departementId }, new BeanPropertyRowMapper<AccountPageData>(AccountPageData.class));
	}

	/*
	 * =============================================================================
	 * =============== DTO MYTOPO
	 * =============================================================================
	 * ===============
	 */

	@Override
	public List<MyTopo> findAllMyTopoByUtilisateurId(int utilisateurId) {
		return jdbcTemplate.query(
				"select topo_id, " 
						+ "utilisateur_id, " 
						+ "topo.nom as topo_nom, "
						+ "site.nom as site, "
						+ "concat(departement.code, ' - ', departement.nom) as departement, "
						+ "topo.date_parution::date, "
						+ "disponible, "
						+ "shared " 
						+ "from topo "
						+ "inner join possesseur_topo on topo.id = possesseur_topo.topo_id "
						+ "inner join site on topo.site_id = site.id "
						+ "inner join departement on site.departement_id = departement.id "
						+ "where possesseur_topo.utilisateur_id=?",
				new Object[] { utilisateurId }, new BeanPropertyRowMapper<MyTopo>(MyTopo.class));
	}

	/*
	 * =============================================================================
	 * =============== DTO RESERVATIONREQUEST
	 * =============================================================================
	 * ===============
	 */

	@Override
	public List<ReservationRequest> findAllReceivedReservationRequestByUtilisateurId(int utilisateurId){
		return jdbcTemplate.query(
				"select distinct reservation_topo.id as reservation_id, "
				+ "reservation_topo_id, "
				+ "topo.nom as reservation_topo_nom, "
				+ "possesseur_id, "
				+ "demandeur_id, "
				+ "utilisateur.nom as demandeur_nom, "
				+ "utilisateur.email as demandeur_email, "
				+ "status_demande_reservation.id as status_id, "
				+ "status_demande_reservation.status as status, "
				+ "concat(departement.code, ' - ', departement.nom) as departement, topo.date_parution::date, "
				+ "site.nom as site_nom, "
				+ "possesseur_topo.disponible "
				+ "from reservation_topo "
				+ "inner join topo on reservation_topo.reservation_topo_id = topo.id "
				+ "inner join utilisateur on reservation_topo.demandeur_id = utilisateur.id "
				+ "inner join site on topo.site_id = site.id "
				+ "inner join departement on site.departement_id = departement.id "
				+ "inner join status_demande_reservation on reservation_topo.status_id = status_demande_reservation.id "
				+ "inner join possesseur_topo on reservation_topo.reservation_topo_id = possesseur_topo.topo_id "
				+ "where reservation_topo.possesseur_id = ? "
				+ "and visible_for_owner = ?", 
				new Object[] {utilisateurId, true}, 
				new BeanPropertyRowMapper<ReservationRequest>(ReservationRequest.class));
	}
	
	@Override
	public List<ReservationRequest> findAllSentReservationRequestByUtilisateurId(int utilisateurId){
		return jdbcTemplate.query(
				"select distinct reservation_topo.id as reservation_id, "
				+ "reservation_topo_id, "
				+ "topo.nom as reservation_topo_nom, "
				+ "possesseur_id, "
				+ "demandeur_id, "
				+ "utilisateur.nom as possesseur_nom, "
				+ "utilisateur.email as possesseur_email, "
				+ "status_demande_reservation.id as status_id, "
				+ "status_demande_reservation.status as status, "
				+ "concat(departement.code, ' - ', departement.nom) as departement, topo.date_parution::date, "
				+ "site.nom as site_nom, "
				+ "possesseur_topo.disponible "
				+ "from reservation_topo "
				+ "inner join topo on reservation_topo.reservation_topo_id = topo.id "
				+ "inner join utilisateur on reservation_topo.possesseur_id = utilisateur.id "
				+ "inner join site on topo.site_id = site.id "
				+ "inner join departement on site.departement_id = departement.id "
				+ "inner join status_demande_reservation on reservation_topo.status_id = status_demande_reservation.id "
				+ "inner join possesseur_topo on reservation_topo.reservation_topo_id = possesseur_topo.topo_id "
				+ "where reservation_topo.demandeur_id = ? "
				+ "and visible_for_requester = ? "
				+ "and possesseur_topo.utilisateur_id != ?", 
				new Object[] {utilisateurId, true, utilisateurId}, 
				new BeanPropertyRowMapper<ReservationRequest>(ReservationRequest.class));
	}
	
	@Override
	@Transactional
	public void updateReservationRequestStatusToAccepted(int reservationRequestId) {
		jdbcTemplate.update("update reservation_topo set status_id = ? where id = ?", 2, reservationRequestId );
	}
	
	@Override
	@Transactional
	public void updateReservationRequestStatusToRefused(int reservationRequestId) {
		jdbcTemplate.update("update reservation_topo set status_id = ? where id = ?", 3, reservationRequestId );
	}
	
	@Override
	@Transactional
	public void updateReservationRequestStatusToEnded(int reservationRequestId) {
		jdbcTemplate.update("update reservation_topo set status_id = ? where id = ?", 5, reservationRequestId );
	}
	
	@Override
	@Transactional
	public void updateReservationRequestStatusToCancelled(int reservationRequestId) {
		jdbcTemplate.update("update reservation_topo set status_id = ? where id = ?", 4, reservationRequestId );
	}

}
