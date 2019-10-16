package com.charles.lesamisdelescalade.business.utils.bean;

public interface ReservationTopoManager {

	void setReservationVisibilityForOwnerToFalse(int reservationRequestId);

	void setReservationVisibilityForRequesterToFalse(int reservationRequestId);

	void updateReservationRequestStatusToAccepted(int reservationRequestId);

	void updateReservationRequestStatusToRefused(int reservationRequestId);

	void updateReservationRequestStatusToEnded(int reservationRequestId);

	void updateReservationRequestStatusToCancelled(int reservationRequestId);

}
