package com.charles.lesamisdelescalade.consumer.dto.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.charles.lesamisdelescalade.consumer.dto.AccountPageDataDao;
import com.charles.lesamisdelescalade.model.dto.AccountPageData;

@Repository
public class AccountPagaDataDaoImpl implements AccountPageDataDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<AccountPageData> getDataForAccountPageDataBySiteId(int departementId) {
		return jdbcTemplate.query(
				"select topo.id as topo_id, topo.nom as topo_nom, site.nom as site_nom, topo.date_parution::date from topo inner join site on topo.site_id = site.id where site.departement_id = ?",
				new Object[] { departementId }, new BeanPropertyRowMapper<AccountPageData>(AccountPageData.class));
		// TODO refactor name
	}

}
