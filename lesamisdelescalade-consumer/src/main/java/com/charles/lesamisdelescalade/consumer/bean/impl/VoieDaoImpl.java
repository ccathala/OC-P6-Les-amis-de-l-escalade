package com.charles.lesamisdelescalade.consumer.bean.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charles.lesamisdelescalade.consumer.bean.VoieDao;
import com.charles.lesamisdelescalade.model.beans.Voie;

@Repository
public class VoieDaoImpl implements VoieDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Voie> findAllVoieBySite(int siteId) {
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

}
