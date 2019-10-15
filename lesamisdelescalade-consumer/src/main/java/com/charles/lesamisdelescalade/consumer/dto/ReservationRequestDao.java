package com.charles.lesamisdelescalade.consumer.dto;

import java.util.List;

import com.charles.lesamisdelescalade.model.dto.ReservationRequest;

public interface ReservationRequestDao {

	List<ReservationRequest> findAllReceivedReservationRequestByUtilisateurId(int utilisateurId);

	List<ReservationRequest> findAllSentReservationRequestByUtilisateurId(int utilisateurId);

}
