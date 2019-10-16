package com.charles.lesamisdelescalade.business.utils.bean.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.charles.lesamisdelescalade.business.utils.bean.ReservationTopoManager;
import com.charles.lesamisdelescalade.consumer.bean.ReservationTopoDao;
import com.charles.lesamisdelescalade.consumer.dto.ReservationRequestDao;

@Service
public class ReservationTopoManagerImpl implements ReservationTopoManager{
	
	@Autowired
	private ReservationTopoDao reservationTopoDao;
	
	@Override
	public void setReservationVisibilityForOwnerToFalse(int reservationRequestId) {
		reservationTopoDao.setReservationVisibilityForOwnerToFalse(reservationRequestId);
	}
	
	@Override
	public void setReservationVisibilityForRequesterToFalse(int reservationRequestId) {
		reservationTopoDao.setReservationVisibilityForRequesterToFalse(reservationRequestId);
		
	}
	
	@Override
	public void updateReservationRequestStatusToAccepted(int reservationRequestId) {
		reservationTopoDao.updateReservationRequestStatusToAccepted(reservationRequestId);
		// TODO refactor method name
	}
	
	@Override
	public void updateReservationRequestStatusToRefused(int reservationRequestId) {
		reservationTopoDao.updateReservationRequestStatusToRefused(reservationRequestId);
		// TODO refactor method name
	}
	
	@Override
	public void updateReservationRequestStatusToEnded(int reservationRequestId) {
		reservationTopoDao.updateReservationRequestStatusToEnded(reservationRequestId);
		// TODO refactor method name
	}
	
	@Override
	public void updateReservationRequestStatusToCancelled(int reservationRequestId) {
		reservationTopoDao.updateReservationRequestStatusToCancelled(reservationRequestId);
		// TODO refactor method name
	}

}
