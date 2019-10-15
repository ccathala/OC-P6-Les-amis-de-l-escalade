package com.charles.lesamisdelescalade.consumer.model.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charles.lesamisdelescalade.consumer.model.UtilisateurDao;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;

@Repository
public class UtilisateurDaoImpl implements UtilisateurDao {

	/* Dependency injection bean JdbcTemplate */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Add new user with role = user
	 */
	@Override
	@Transactional
	public void addUtilisateur(Utilisateur utilisateur) {
		jdbcTemplate.update("INSERT INTO public.utilisateur (nom, email, password, role_id) VALUES (?, ?, ?, ?)",
				utilisateur.getNom(), utilisateur.getEmail(), utilisateur.getPassword(), 2);
	}

	/**
	 * Find user by email adress
	 */
	@Override
	public Utilisateur findByEmail(String utilisateurEmail) throws EmptyResultDataAccessException {
		Utilisateur utilisateur = (Utilisateur) jdbcTemplate.queryForObject(
				"SELECT * FROM public.utilisateur WHERE email=?", new Object[] { utilisateurEmail },
				new BeanPropertyRowMapper<Utilisateur>(Utilisateur.class));
		return utilisateur;
	}

	/**
	 * Find user by username
	 */
	@Override
	public Utilisateur findByUsername(String utilisateurNom) throws EmptyResultDataAccessException {
		Utilisateur utilisateur = (Utilisateur) jdbcTemplate.queryForObject(
				"SELECT * FROM public.utilisateur WHERE nom=?", new Object[] { utilisateurNom },
				new BeanPropertyRowMapper<Utilisateur>(Utilisateur.class));
		return utilisateur;
	}

	/**
	 * Return user list
	 */
	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> utilisateurs = null;
		utilisateurs = jdbcTemplate.query("SELECT * FROM utilisateur",
				new BeanPropertyRowMapper<Utilisateur>(Utilisateur.class));
		return utilisateurs;
	}

	@Override
	public List<Utilisateur> findAllUtilisateurOnlyIdAndName() {
		return jdbcTemplate.query("select id, nom from utilisateur",
				new BeanPropertyRowMapper<Utilisateur>(Utilisateur.class));
	}

}
