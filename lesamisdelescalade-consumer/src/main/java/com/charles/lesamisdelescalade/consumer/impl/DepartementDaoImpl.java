package com.charles.lesamisdelescalade.consumer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.charles.lesamisdelescalade.model.beans.Departement;

import com.charles.lesamisdelescalade.consumer.DepartementDao;

@Repository
public class DepartementDaoImpl implements DepartementDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Departement> findAll() {
		return jdbcTemplate.query("select * from departement", new BeanPropertyRowMapper<Departement>(Departement.class));
		
		
	}
	
	
}
