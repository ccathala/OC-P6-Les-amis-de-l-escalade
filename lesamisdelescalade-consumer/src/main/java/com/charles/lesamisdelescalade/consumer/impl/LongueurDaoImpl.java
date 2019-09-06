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
	public List<Longueur> findBySite(int siteId) {
		return jdbcTemplate.query(
				"select longueur.id, longueur.numero, longueur.cotation_id, longueur.voie_id, cotation from cotation inner join longueur on cotation.id = longueur.cotation_id inner join voie on voie.id = longueur.voie_id inner join secteur on secteur.id = voie.secteur_id where secteur.site_id=?",
				new Object[] { siteId }, new BeanPropertyRowMapper<Longueur>(Longueur.class));
	}
	
}
