package com.charles.lesamisdelescalade.consumer.model;

import com.charles.lesamisdelescalade.model.beans.ReservationTopo;

public interface ReservationTopoDao {

	void addReservation(ReservationTopo reservationTopo);

	ReservationTopo findReservationTopoByRequesterIdAndTopoIdAndStatusIsWaiting(int requesterId, int topoId);

	void setReservationVisibilityForOwnerToFalse(int reservationRequestId);

	void setReservationVisibilityForRequesterToFalse(int reservationRequestId);

	void updateReservationRequestStatusToAccepted(int reservationRequestId);

	void updateReservationRequestStatusToRefused(int reservationRequestId);

	void updateReservationRequestStatusToEnded(int reservationRequestId);

	void updateReservationRequestStatusToCancelled(int reservationRequestId);

}
