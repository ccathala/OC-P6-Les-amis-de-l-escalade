package com.charles.lesamisdelescalade.business.utils.bean;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Departement;

public interface DepartementManager {

	List<Departement> findAllDepartement();

	int getDepartementIdBySiteId(int siteId);

}
