package com.charles.lesamisdelescalade.consumer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charles.lesamisdelescalade.consumer.WebContentDao;
import com.charles.lesamisdelescalade.model.beans.Cotation;
import com.charles.lesamisdelescalade.model.beans.Departement;
import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Voie;

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

	@Override
	public List<Departement> findAllDepartement() {
		return jdbcTemplate.query("select * from departement",
				new BeanPropertyRowMapper<Departement>(Departement.class));

	}

	@Override
	public int getDepartementIdBySiteId(int siteId) {
		return jdbcTemplate.queryForObject("SELECT departement_id FROM site where id=?", new Object[] { siteId },
				Integer.class);
	}

	/*
	 * =============================================================================
	 * =============== SITE *
	 * =============================================================================
	 * ===============
	 */

	@Override
	@Transactional
	public void addSite(Site site) throws DuplicateKeyException {
		jdbcTemplate.update("INSERT INTO site (nom, description, departement_id, tag_id) VALUES(?, ?, ?, 1)",
				site.getNom(), site.getDescription(), site.getDepartement_id());

	}

	@Override
	public Site findSite(int siteId) {
		Site site = (Site) jdbcTemplate.queryForObject("SELECT * FROM site where id = ?", new Object[] { siteId },
				new BeanPropertyRowMapper<Site>(Site.class));
		return site;
	}

	@Override
	public List<Site> findAllSiteByDepartement(int departementId) {
		List<Site> sites = jdbcTemplate.query("select * from site where departement_id=?",
				new Object[] { departementId }, new BeanPropertyRowMapper<Site>(Site.class));
		return sites;
	}

	@Override

	public int getSiteIdBySecteurId(int secteurId) {
		return jdbcTemplate.queryForObject("select site_id from secteur where id=?", new Object[] { secteurId },
				Integer.class);
	}

	@Override
	@Transactional
	public void addOfficialTagOnSite(int siteId) {
		jdbcTemplate.update("update site set tag_id=? where id=?", 2, siteId);
	}

	@Override
	@Transactional
	public void deleteOfficialTagOnSite(int siteId) {
		jdbcTemplate.update("update site set tag_id=? where id=?", 1, siteId);
	}

	@Override
	public List<Site> findAllSite() {
		return jdbcTemplate.query("select * from site order by id asc", new BeanPropertyRowMapper<Site>(Site.class));
	}

	@Override
	public List<Site> findAllSiteByCotation(int cotationId) {
		return jdbcTemplate.query(
				"select distinct site.* from site inner join secteur on site.id = secteur.site_id inner join voie on secteur.id=voie.secteur_id inner join longueur on voie.id = longueur.voie_id where longueur.cotation_id = ?",
				new Object[] { cotationId }, new BeanPropertyRowMapper<Site>(Site.class));
	}
	
	@Override
	public List<Site> findAllSiteBySecteurCount(int secteurCount){
		return jdbcTemplate.query("select site.* from site inner join secteur on site.id = secteur.site_id group by site.id having count(secteur.id)=?", new Object[] {secteurCount}, new BeanPropertyRowMapper<Site>(Site.class));
	}

	/*
	 * =============================================================================
	 * =============== SECTEUR *
	 * =============================================================================
	 * ===============
	 */

	@Override
	@Transactional
	public void addSecteur(Secteur secteur) throws DuplicateKeyException {
		jdbcTemplate.update("INSERT INTO secteur(nom, description, site_id) VALUES (?, ?, ?)", secteur.getNom(),
				secteur.getDescription(), secteur.getSite_id());
	}

	@Override
	public List<Secteur> findAllSecteurBySite(int siteId) {
		return jdbcTemplate.query("SELECT * FROM public.secteur where site_id = ?", new Object[] { siteId },
				new BeanPropertyRowMapper<Secteur>(Secteur.class));

	}

	@Override
	public int getSecteurIdByVoieId(int voieId) {
		return jdbcTemplate.queryForObject("select secteur_id from voie where id=?", new Object[] { voieId },
				Integer.class);
	}

	/*
	 * =============================================================================
	 * =============== VOIE *
	 * =============================================================================
	 * ===============
	 */

	@Override
	public List<Voie> findVoieBySite(int siteId) {
		return jdbcTemplate.query(
				"select voie.id, voie.nom, voie.numero, voie.secteur_id from secteur inner join voie on secteur.id = voie.secteur_id where site_id = ?",
				new Object[] { siteId }, new BeanPropertyRowMapper<Voie>(Voie.class));

	}

	@Override
	public int getVoieCountBySecteur(int secteurId) {
		return jdbcTemplate.queryForObject("SELECT COUNT (*) from public.voie where secteur_id = ?",
				new Object[] { secteurId }, Integer.class);
	}

	@Override
	public String getSecteurMinCotation(int secteurId) throws NullPointerException {
		int cotation_id = jdbcTemplate.queryForObject(
				"SELECT MIN(cotation_id) FROM public.voie inner join longueur on voie.id  = longueur.voie_id where secteur_id = ?",
				new Object[] { secteurId }, Integer.class);
		return jdbcTemplate.queryForObject("SELECT cotation FROM public.cotation where id = ?",
				new Object[] { cotation_id }, String.class);
	}

	@Override
	public String getSecteurMaxCotation(int secteurId) throws NullPointerException {
		int cotation_id = jdbcTemplate.queryForObject(
				"SELECT MAX(cotation_id) FROM public.voie inner join longueur on voie.id  = longueur.voie_id where secteur_id = ?",
				new Object[] { secteurId }, Integer.class);
		return jdbcTemplate.queryForObject("SELECT cotation FROM public.cotation where id = ?",
				new Object[] { cotation_id }, String.class);
	}

	@Override
	public Voie findVoieByNumeroAndSecteur(int numero, int secteurId) throws EmptyResultDataAccessException {
		return (Voie) jdbcTemplate.queryForObject("select * from voie where secteur_id=? and numero=?",
				new Object[] { secteurId, numero }, new BeanPropertyRowMapper<Voie>(Voie.class));
	}

	@Override
	public Voie findVoieByNomAndSecteur(String nom, int secteurId) throws EmptyResultDataAccessException {
		return (Voie) jdbcTemplate.queryForObject("select * from voie where secteur_id=? and nom=?",
				new Object[] { secteurId, nom }, new BeanPropertyRowMapper<Voie>(Voie.class));
	}

	@Override
	@Transactional
	public void addVoie(Voie voie) {
		jdbcTemplate.update("INSERT INTO voie (numero, nom, secteur_id) VALUES (?, ?, ?)", voie.getNumero(),
				voie.getNom(), voie.getSecteur_id());
	}

	@Override
	public List<Voie> findAllVoieBySecteur(int secteurId) {
		return jdbcTemplate.query("SELECT * FROM public.voie where secteur_id = ? order by numero asc",
				new Object[] { secteurId }, new BeanPropertyRowMapper<Voie>(Voie.class));

	}

	/*
	 * =============================================================================
	 * =============== LONGUEUR *
	 * =============================================================================
	 * ===============
	 */

	@Override
	public List<Longueur> findLongueurBySite(int siteId) {
		return jdbcTemplate.query(
				"select longueur.id, longueur.numero, longueur.cotation_id, longueur.voie_id, cotation from cotation inner join longueur on cotation.id = longueur.cotation_id inner join voie on voie.id = longueur.voie_id inner join secteur on secteur.id = voie.secteur_id where secteur.site_id=?",
				new Object[] { siteId }, new BeanPropertyRowMapper<Longueur>(Longueur.class));
	}

	@Override
	public Longueur findLongueurByNumeroAndVoie(int numero, int voieId) {
		return (Longueur) jdbcTemplate.queryForObject("select * from longueur where numero=? and voie_id=?",
				new Object[] { numero, voieId }, new BeanPropertyRowMapper<Longueur>(Longueur.class));
	}

	@Override
	@Transactional
	public void addLongeur(Longueur longueur) {
		jdbcTemplate.update("INSERT INTO longueur (numero, cotation_id, voie_id) VALUES (?, ?, ?)",
				longueur.getNumero(), longueur.getCotation_id(), longueur.getVoie_id());
	}

	@Override
	public List<Longueur> findAllLongueurByVoie(int voieId) {
		return jdbcTemplate.query(
				"select longueur.id, longueur.numero, longueur.cotation_id, longueur.voie_id, cotation from cotation inner join longueur on cotation.id = longueur.cotation_id where voie_id=? order by numero ASC",
				new Object[] { voieId }, new BeanPropertyRowMapper<Longueur>(Longueur.class));

	}

	@Override
	public List<Cotation> findAllCotation() {
		List<Cotation> cotations = jdbcTemplate.query("select * from cotation",
				new BeanPropertyRowMapper<Cotation>(Cotation.class));
		return cotations;
	}

}
