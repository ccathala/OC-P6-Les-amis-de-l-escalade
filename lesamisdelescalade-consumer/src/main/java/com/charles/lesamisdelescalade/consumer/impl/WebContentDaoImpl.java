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

	

	/*
	 * =============================================================================
	 * =============== RESERVATION
	 * =============================================================================
	 * ===============
	 */

	

	/*
	 * =============================================================================
	 * =============== POSSESSEUR TOPO
	 * =============================================================================
	 * ===============
	 */

	

	/*
	 * =============================================================================
	 * =============== DTO ACCOUNTPAGEDATA
	 * =============================================================================
	 * ===============
	 */

	

	/*
	 * =============================================================================
	 * =============== DTO MYTOPO
	 * =============================================================================
	 * ===============
	 */

	

	/*
	 * =============================================================================
	 * =============== DTO RESERVATIONREQUEST
	 * =============================================================================
	 * ===============
	 */

	
	
	

}
