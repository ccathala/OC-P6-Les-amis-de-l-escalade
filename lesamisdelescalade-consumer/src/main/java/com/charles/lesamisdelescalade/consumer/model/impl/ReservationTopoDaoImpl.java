package com.charles.lesamisdelescalade.consumer.model.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.charles.lesamisdelescalade.consumer.model.ReservationTopoDao;
import com.charles.lesamisdelescalade.model.beans.ReservationTopo;

@Repository
public class ReservationTopoDaoImpl implements ReservationTopoDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	@Transactional
	public void addReservation(ReservationTopo reservationTopo) {
		jdbcTemplate.update(
				"insert into reservation_topo(reservation_topo_id, possesseur_id, demandeur_id, status_id, visible_for_owner, visible_for_requester) values(?,?,?,?,?,?)",
				reservationTopo.getReservation_topo_id(), reservationTopo.getPossesseur_id(),
				reservationTopo.getDemandeur_id(), 1, true, true);
	}

	@Override
	public ReservationTopo findReservationTopoByRequesterIdAndTopoIdAndStatusIsWaiting(int requesterId, int topoId) {
		return jdbcTemplate.queryForObject(
				"select * from reservation_topo where reservation_topo_id=? and demandeur_id = ? and status_id = ?",
				new Object[] { topoId, requesterId, 1 },
				new BeanPropertyRowMapper<ReservationTopo>(ReservationTopo.class));
	}
	
	@Override
	@Transactional
	public void setReservationVisibilityForOwnerToFalse(int reservationRequestId) {
		jdbcTemplate.update("update reservation_topo set visible_for_owner = ? where id = ?" , false, reservationRequestId);
	}
	
	@Override
	@Transactional
	public void setReservationVisibilityForRequesterToFalse(int reservationRequestId) {
		jdbcTemplate.update("update reservation_topo set visible_for_requester = ? where id = ?", false, reservationRequestId);
	}

}
