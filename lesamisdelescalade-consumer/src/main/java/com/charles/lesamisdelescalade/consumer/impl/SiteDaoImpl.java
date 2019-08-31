package com.charles.lesamisdelescalade.consumer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.charles.lesamisdelescalade.consumer.SiteDao;
import com.charles.lesamisdelescalade.model.beans.Site;

@Repository
public class SiteDaoImpl implements SiteDao {

	/* Dependency injection bean JdbcTemplate */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addSite(Site site) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editSite(Site site, int siteId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSite(int siteId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Site find(int siteId) {
		Site site = (Site) jdbcTemplate.queryForObject("SELECT * FROM public.site where id = ?",
				new Object[] { siteId }, new BeanPropertyRowMapper<Site>(Site.class));
		return site;
	}

	@Override
	public List<Site> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
