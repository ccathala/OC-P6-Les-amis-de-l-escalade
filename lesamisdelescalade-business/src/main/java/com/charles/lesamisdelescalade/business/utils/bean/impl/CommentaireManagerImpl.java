package com.charles.lesamisdelescalade.business.utils.bean.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.charles.lesamisdelescalade.business.utils.bean.CommentaireManager;
import com.charles.lesamisdelescalade.consumer.bean.CommentaireDao;
import com.charles.lesamisdelescalade.model.beans.Commentaire;

@Service
public class CommentaireManagerImpl implements CommentaireManager {
	
	@Autowired
	private CommentaireDao commentaireDao;
	
	@Override
	public void updateCommentaireStatus(int commentaireId) {
		commentaireDao.updateCommentaireStatus(commentaireId);
	}
	
	@Override
	public void addCommentaire(Commentaire commentaire) {
		commentaireDao.addCommentaire(commentaire);
	}
	
	@Override
	public List<Commentaire> findAllCommentaireBySite(int siteId){
		return commentaireDao.findAllCommentaireBySite(siteId);
	}

}
