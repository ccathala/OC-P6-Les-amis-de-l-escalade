package com.charles.lesamisdelescalade.consumer.bean.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charles.lesamisdelescalade.consumer.bean.TopoDao;
import com.charles.lesamisdelescalade.model.beans.Topo;
import com.charles.lesamisdelescalade.model.dto.ListTopoPageData;

@Repository
public class TopoDaoImpl implements TopoDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	@Transactional
	public void addTopo(Topo topo) {
		jdbcTemplate.update("insert into topo (nom, description, date_parution, site_id) values (?,?,?,?)",
				topo.getNom(), topo.getDescription(), topo.getDate_parution(), topo.getSite_id());
	}

	@Override
	public Topo findTopoBySiteIdAndAnneeParution(int siteId, Date dateParution) {
		return (Topo) jdbcTemplate.queryForObject(
				"select date_parution, site_id from topo where date_parution=? and site_id=?",
				new Object[] { dateParution, siteId }, new BeanPropertyRowMapper<Topo>(Topo.class));
	}

	@Override
	public List<ListTopoPageData> findAllTopoAndExtendedData() {
		return jdbcTemplate.query(
				"select "
				+ "departement.code as \"code_postal\", "
				+ "departement.nom as departement, "
				+ "topo.id as topo_id, "
				+ "topo.nom as topo_nom, "
				+ "site.nom as site, "
				+ "topo.description, "
				+ "topo.date_parution::date "
				+ "from topo "
				+ "inner join site on topo.site_id = site.id "
				+ "inner join departement on site.departement_id = departement.id",
				new BeanPropertyRowMapper<ListTopoPageData>(ListTopoPageData.class));
		
		// TODO move to ListTopoPageData DTO class
	}

	@Override
	public List<ListTopoPageData> findAllAvailableTopoAndExtendedData(int utilisateurId) {
		return jdbcTemplate.query(
				"select "
				+ "id as possesseur_id, "
				+ "nom as possesseur_nom, "
				+ "topo_id, "
				+ "disponible "
				+ "from utilisateur "
				+ "inner join possesseur_topo on utilisateur.id = possesseur_topo.utilisateur_id "
				+ "where possesseur_topo.disponible = ? "
				+ "and id != ?",
				new Object[] {true, utilisateurId},
				new BeanPropertyRowMapper<ListTopoPageData>(ListTopoPageData.class));
		
		// TODO move to ListTopoPageData DTO class
	}

}
