package com.masai.Service;

import java.util.List;

import com.masai.DAO.ReservationDAO;
import com.masai.DAO.ReservationDAOImpl;
import com.masai.Entity.Reservation;
import com.masai.Entity.Transaction;
import com.masai.Exception.NoReservationFoundException;
import com.masai.Exception.SomethingWentWrongException;

public class ReservationServiceImpl implements ReservationService {

	@Override
	public void addReservation(Reservation reservation, String model,Transaction tran)
			throws SomethingWentWrongException, NoReservationFoundException {
		ReservationDAO dao= new ReservationDAOImpl();
		
			dao.addReservation(reservation, model,tran);
		

	}

	@Override
	public List<Reservation> getReservationList() throws SomethingWentWrongException, NoReservationFoundException {
		ReservationDAO dao= new ReservationDAOImpl();
		
		return dao.getReservationList();
	}

	

}
