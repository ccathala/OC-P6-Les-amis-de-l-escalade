package com.charles.lesamisdelescalade.consumer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charles.lesamisdelescalade.consumer.VoieDao;
import com.charles.lesamisdelescalade.model.beans.Voie;

@Repository
public class VoieDaoImpl implements VoieDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Voie> findBySite(int siteId) {
		return jdbcTemplate.query(
				"select voie.id, voie.nom, voie.numero, voie.secteur_id from secteur inner join voie on secteur.id = voie.secteur_id where site_id = ?",
				new Object[] { siteId }, new BeanPropertyRowMapper<Voie>(Voie.class));

	}

	@Override
	public int getVoieCount(int secteurId) {
		return jdbcTemplate.queryForObject("SELECT COUNT (*) from public.voie where secteur_id = ?",
				new Object[] { secteurId }, Integer.class);
	}

	@Override
	public String getMinCotation(int secteurId) {
		int cotation_id = jdbcTemplate.queryForObject(
				"SELECT MIN(cotation_id) FROM public.voie inner join longueur on voie.id  = longueur.voie_id where secteur_id = ?",
				new Object[] { secteurId }, Integer.class);
		return jdbcTemplate.queryForObject("SELECT cotation FROM public.cotation where id = ?",
				new Object[] { cotation_id }, String.class);
	}

	@Override
	public String getMaxCotation(int secteurId) {
		int cotation_id = jdbcTemplate.queryForObject(
				"SELECT MAX(cotation_id) FROM public.voie inner join longueur on voie.id  = longueur.voie_id where secteur_id = ?",
				new Object[] { secteurId }, Integer.class);
		return jdbcTemplate.queryForObject("SELECT cotation FROM public.cotation where id = ?",
				new Object[] { cotation_id }, String.class);
	}
	
	@Override
	public Voie findVoieByNumeroAndSite(int numero, int siteId) throws EmptyResultDataAccessException {
		return (Voie) jdbcTemplate.queryForList("select * from voie where secteur_id=? and numero=?", new Object[] {siteId}, new Object[] {numero}, new BeanPropertyRowMapper<Voie>(Voie.class));
	}
	
	@Override
	public Voie findVoieByNomAndSite(String nom, int siteId) throws EmptyResultDataAccessException {
		return (Voie) jdbcTemplate.queryForList("select * from voie where secteur_id=? and nom=?", new Object[] {siteId}, new Object[] {nom}, new BeanPropertyRowMapper<Voie>(Voie.class));
	}
	
	@Override
	@Transactional
	public void addVoie(Voie voie) {
		jdbcTemplate.update("INSERT INTO voie (numero, nom, secteur_id) VALUES (?, ?, ?)", voie.getNumero(), voie.getNom(), voie.getSecteur_id());
	}

}
