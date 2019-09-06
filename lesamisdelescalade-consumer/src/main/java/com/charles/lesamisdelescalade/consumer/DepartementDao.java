package com.charles.lesamisdelescalade.consumer;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Departement;


public interface DepartementDao {
	
	public List<Departement> findAll();

}
