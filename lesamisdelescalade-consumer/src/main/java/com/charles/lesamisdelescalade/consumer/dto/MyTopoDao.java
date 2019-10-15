package com.charles.lesamisdelescalade.consumer.dto;

import java.util.List;

import com.charles.lesamisdelescalade.model.dto.MyTopo;

public interface MyTopoDao {

	List<MyTopo> findAllMyTopoByUtilisateurId(int utilisateurId);

}
