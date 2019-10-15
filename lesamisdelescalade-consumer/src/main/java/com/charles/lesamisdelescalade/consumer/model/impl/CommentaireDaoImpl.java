package com.charles.lesamisdelescalade.consumer.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charles.lesamisdelescalade.consumer.model.CommentaireDao;
import com.charles.lesamisdelescalade.model.beans.Commentaire;

@Repository
public class CommentaireDaoImpl implements CommentaireDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Commentaire> findAllCommentaireBySite(int siteId) {
		return jdbcTemplate.query("select * from commentaire where site_id=? order by id asc", new Object[] { siteId },
				new BeanPropertyRowMapper<Commentaire>(Commentaire.class));
	}

	@Override
	@Transactional
	public void addCommentaire(Commentaire commentaire) {
		jdbcTemplate.update(
				"insert into commentaire (texte, date, status_id, utilisateur_id, site_id) values(?,current_timestamp(0),?,?,?)",
				commentaire.getTexte(), commentaire.getStatus_id(), commentaire.getUtilisateur_id(),
				commentaire.getSite_id());
	}

	@Override
	@Transactional
	public void updateCommentaire(Commentaire commentaire, int utilisateurId) {
		jdbcTemplate.update("update commentaire set texte= ?, status_id=? where id=?", commentaire.getTexte(),
				commentaire.getStatus_id(), utilisateurId);
	}

	@Override
	@Transactional
	public void updateCommentaireStatus(int commentaireId) {
		jdbcTemplate.update("update commentaire set status_id=? where id=?", 3, commentaireId);
	}

}
