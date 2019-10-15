package com.charles.lesamisdelescalade.consumer.model.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.charles.lesamisdelescalade.consumer.model.LongueurDao;
import com.charles.lesamisdelescalade.model.beans.Cotation;
import com.charles.lesamisdelescalade.model.beans.Longueur;

@Repository
public class LongueurDaoImpl implements LongueurDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
		// TODO refactor
	}

}
