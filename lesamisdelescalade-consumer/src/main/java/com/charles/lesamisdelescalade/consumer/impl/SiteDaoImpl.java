package com.charles.lesamisdelescalade.consumer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charles.lesamisdelescalade.consumer.SiteDao;
import com.charles.lesamisdelescalade.model.beans.Site;

@Repository
public class SiteDaoImpl implements SiteDao {

	/* Dependency injection bean JdbcTemplate */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public void addSite(Site site) throws DuplicateKeyException{
		jdbcTemplate.update("INSERT INTO site (nom, description, departement_id, tag_id) VALUES(?, ?, ?, 1)",
				site.getNom(), site.getDescription(), site.getDepartement_id());

	}

	
	@Override
	public Site find(int siteId) {
		Site site = (Site) jdbcTemplate.queryForObject("SELECT * FROM site where id = ?",
				new Object[] { siteId }, new BeanPropertyRowMapper<Site>(Site.class));
		return site;
	}

	@Override
	public List<Site> findAllSitesByDepartement(int departementId){
		List<Site> sites = jdbcTemplate.query("select * from site where departement_id=?", new Object[] {departementId}, new BeanPropertyRowMapper<Site>(Site.class));
		return sites;
	}
	
	@Override
	public int getDepartementIdBySiteId(int siteId) {
		return jdbcTemplate.queryForObject("SELECT departement_id FROM site where id=?", new Object[] {siteId}, Integer.class);
	}
	

}
