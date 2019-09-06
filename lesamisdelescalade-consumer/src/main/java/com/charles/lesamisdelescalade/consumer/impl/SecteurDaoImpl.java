package com.charles.lesamisdelescalade.consumer.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charles.lesamisdelescalade.consumer.SecteurDao;
import com.charles.lesamisdelescalade.model.beans.Secteur;

@Repository
public class SecteurDaoImpl implements SecteurDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public void addSecteur(Secteur secteur) throws DuplicateKeyException{
		jdbcTemplate.update("INSERT INTO secteur(nom, description, site_id) VALUES (?, ?, ?)", secteur.getNom(), secteur.getDescription(), secteur.getSite_id());
	}

	@Override
	public List<Secteur> findAllSecteursBySite(int siteId) {
		return jdbcTemplate.query("SELECT * FROM public.secteur where site_id = ?", new Object[] { siteId },
				new BeanPropertyRowMapper<Secteur>(Secteur.class));

	}
	
	
}
