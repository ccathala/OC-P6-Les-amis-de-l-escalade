package com.charles.lesamisdelescalade.consumer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.charles.lesamisdelescalade.consumer.LongueurDao;
import com.charles.lesamisdelescalade.model.beans.Longueur;

@Repository
public class LongueurDaoImpl implements LongueurDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Longueur> findBySecteur(int secteurId) {
		return jdbcTemplate.query(
				"select longueur.id, longueur.numero, longueur.cotation_id, longueur.voie_id from voie inner join longueur on voie.id = longueur.voie_id where secteur_id = ?",
				new Object[] { secteurId }, new BeanPropertyRowMapper<Longueur>(Longueur.class));
	}
	
	@Override
	public String getCotation(int cotationId) {
		return jdbcTemplate.queryForObject("select cotation from cotation where id=?", new Object[] {cotationId}, String.class);
	}

}
