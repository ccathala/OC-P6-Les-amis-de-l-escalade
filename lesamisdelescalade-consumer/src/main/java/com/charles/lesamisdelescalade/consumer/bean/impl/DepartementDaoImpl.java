package com.charles.lesamisdelescalade.consumer.bean.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.charles.lesamisdelescalade.consumer.bean.DepartementDao;
import com.charles.lesamisdelescalade.model.beans.Departement;

@Repository
public class DepartementDaoImpl implements DepartementDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<Departement> findAllDepartement() {
		return jdbcTemplate.query("select * from departement",
				new BeanPropertyRowMapper<Departement>(Departement.class));

	}

	@Override
	public int getDepartementIdBySiteId(int siteId) {
		return jdbcTemplate.queryForObject("SELECT departement_id FROM site where id=?", new Object[] { siteId },
				Integer.class);
	}

}
