package com.charles.lesamisdelescalade.consumer.model.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.charles.lesamisdelescalade.consumer.model.SecteurDao;
import com.charles.lesamisdelescalade.model.beans.Secteur;

@Repository
public class SecteurDaoImpl implements SecteurDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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

}
