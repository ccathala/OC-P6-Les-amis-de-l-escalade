package com.charles.lesamisdelescalade.consumer.implementations;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.charles.lesamisdelescalade.consumer.interfaces.IUtilisateur;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;


@Repository
public class UtilisateurImpl implements IUtilisateur {
	
	private static final Logger logger = LoggerFactory.getLogger(UtilisateurImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editUtilisateur(Utilisateur utilisateur, int utilisateurId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUtilisateur(int utilisateurId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Utilisateur find(int utilisateurId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> utilisateurs = null;
		try {
			utilisateurs = jdbcTemplate.query("SELECT * FROM utilisateur",
					new BeanPropertyRowMapper<Utilisateur>(Utilisateur.class));

		} catch (CannotGetJdbcConnectionException e) {
			logger.debug("La connexion à la base de données à échoué.");
			throw e;
		
		}
		return utilisateurs;
	}
}
