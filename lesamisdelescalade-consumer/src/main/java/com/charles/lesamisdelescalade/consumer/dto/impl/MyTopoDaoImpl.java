package com.charles.lesamisdelescalade.consumer.dto.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.charles.lesamisdelescalade.consumer.dto.MyTopoDao;
import com.charles.lesamisdelescalade.model.dto.MyTopo;

@Repository
public class MyTopoDaoImpl implements MyTopoDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<MyTopo> findAllMyTopoByUtilisateurId(int utilisateurId) {
		return jdbcTemplate.query(
				"select topo_id, " 
						+ "utilisateur_id, " 
						+ "topo.nom as topo_nom, "
						+ "site.nom as site, "
						+ "concat(departement.code, ' - ', departement.nom) as departement, "
						+ "topo.date_parution::date, "
						+ "disponible, "
						+ "shared " 
						+ "from topo "
						+ "inner join possesseur_topo on topo.id = possesseur_topo.topo_id "
						+ "inner join site on topo.site_id = site.id "
						+ "inner join departement on site.departement_id = departement.id "
						+ "where possesseur_topo.utilisateur_id=?",
				new Object[] { utilisateurId }, new BeanPropertyRowMapper<MyTopo>(MyTopo.class));
	}

}
