package com.charles.lesamisdelescalade.consumer.bean.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charles.lesamisdelescalade.consumer.bean.PossesseurTopoDao;
import com.charles.lesamisdelescalade.model.beans.PossesseurTopo;

@Repository
public class PossesseurTopoDaoImpl implements PossesseurTopoDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	@Transactional
	public void addPossesseurTopo(PossesseurTopo possesseurTopo) {
		jdbcTemplate.update("INSERT INTO possesseur_topo (topo_id, utilisateur_id, disponible, shared) VALUES(?, ?, ?, ?)",
				possesseurTopo.getTopo_id(), possesseurTopo.getUtilisateur_id(), possesseurTopo.getDisponible(), false);
	}

	@Override
	@Transactional
	public void setTopoAvailability(PossesseurTopo possesseurTopo) {
		jdbcTemplate.update("update possesseur_topo set disponible = ? where topo_id=? and utilisateur_id=?",
				possesseurTopo.getDisponible(), possesseurTopo.getTopo_id(), possesseurTopo.getUtilisateur_id());
	}
	
	@Override
	@Transactional
	public void setTopoSharedState(PossesseurTopo possesseurTopo) {
		jdbcTemplate.update("update possesseur_topo set shared = ? where topo_id=? and utilisateur_id=?",
				possesseurTopo.getShared(), possesseurTopo.getTopo_id(), possesseurTopo.getUtilisateur_id());
	}

	@Override
	@Transactional
	public void deleteOwnedTopo(int topoId, int utilisateurId) {
		jdbcTemplate.update("delete from possesseur_topo where topo_id = ? and utilisateur_id = ?", topoId,
				utilisateurId);
	}
	
	@Override
	public List<PossesseurTopo> findAllOwnedTopoByUtilisateurId(int utilisateurId){
		return jdbcTemplate.query("select * from possesseur_topo where utilisateur_id = ?",
				new Object[] {utilisateurId},
				new BeanPropertyRowMapper<PossesseurTopo>(PossesseurTopo.class));
	}

}
