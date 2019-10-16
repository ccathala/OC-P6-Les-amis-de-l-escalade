package com.charles.lesamisdelescalade.business.utils.bean;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Commentaire;

public interface CommentaireManager {

	void updateCommentaireStatus(int commentaireId);

	void addCommentaire(Commentaire commentaire);

	List<Commentaire> findAllCommentaireBySite(int siteId);

}
