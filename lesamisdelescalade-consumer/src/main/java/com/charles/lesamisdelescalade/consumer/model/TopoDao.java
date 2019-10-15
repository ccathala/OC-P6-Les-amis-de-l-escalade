package com.charles.lesamisdelescalade.consumer.model;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.charles.lesamisdelescalade.model.beans.Topo;
import com.charles.lesamisdelescalade.model.dto.ListTopoPageData;

@Repository
public interface TopoDao {

	void addTopo(Topo topo);

	Topo findTopoBySiteIdAndAnneeParution(int siteId, Date dateParution);

	List<ListTopoPageData> findAllTopoAndExtendedData();

	List<ListTopoPageData> findAllAvailableTopoAndExtendedData(int utilisateurId);

}
