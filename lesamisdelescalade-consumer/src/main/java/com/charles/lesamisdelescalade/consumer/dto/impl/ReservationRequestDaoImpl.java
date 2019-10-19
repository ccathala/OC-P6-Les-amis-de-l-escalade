package com.charles.lesamisdelescalade.consumer.dto.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.charles.lesamisdelescalade.consumer.dto.ReservationRequestDao;
import com.charles.lesamisdelescalade.model.dto.ReservationRequest;

@Repository
public class ReservationRequestDaoImpl implements ReservationRequestDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
				+ "and visible_for_owner = ?"
				+ "and possesseur_topo.utilisateur_id != ?", 
				new Object[] {utilisateurId, true, utilisateurId }, 
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

}
