package com.charles.lesamisdelescalade.consumer.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.charles.lesamisdelescalade.consumer.model.SiteDao;
import com.charles.lesamisdelescalade.model.beans.Site;

@Repository
public class SiteDaoImpl implements SiteDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
	public List<Site> findAllSiteBySecteurCount(int secteurCount) {
		return jdbcTemplate.query(
				"select site.* from site inner join secteur on site.id = secteur.site_id group by site.id having count(secteur.id)=?",
				new Object[] { secteurCount }, new BeanPropertyRowMapper<Site>(Site.class));
	}

	@Override
	public List<Integer> getSecteurCountBySite() {
		return jdbcTemplate.queryForList(
				"select distinct count(secteur.id) from site inner join secteur on site.id=secteur.site_id group by site.id order by count asc ",
				Integer.class);
	}

	@Override
	public List<Site> findAllSiteByMultiCritere(Object[] criteresSql, String sql) {
		return jdbcTemplate.query(sql, criteresSql, new BeanPropertyRowMapper<Site>(Site.class));
	}

	@Override
	public List<Site> findAllSiteByName(String nom) {
		return jdbcTemplate.query("select * from site where upper(nom) like upper(?)", new Object[] { nom },
				new BeanPropertyRowMapper<Site>(Site.class));
	}

}
