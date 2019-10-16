package com.charles.lesamisdelescalade.consumer.bean;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Commentaire;

public interface CommentaireDao {

	List<Commentaire> findAllCommentaireBySite(int siteId);

	void addCommentaire(Commentaire commentaire);

	void updateCommentaire(Commentaire commentaire, int utilisateurId);

	void updateCommentaireStatus(int commentaireId);

}
