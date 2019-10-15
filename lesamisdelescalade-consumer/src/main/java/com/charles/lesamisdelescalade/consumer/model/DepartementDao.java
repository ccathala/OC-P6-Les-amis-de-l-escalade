package com.charles.lesamisdelescalade.consumer.model;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Departement;

public interface DepartementDao {

	List<Departement> findAllDepartement();

	int getDepartementIdBySiteId(int siteId);

}
