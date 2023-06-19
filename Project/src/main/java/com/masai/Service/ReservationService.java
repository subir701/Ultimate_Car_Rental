package com.masai.Service;

import java.util.List;

import com.masai.Entity.Reservation;
import com.masai.Entity.Transaction;

import com.masai.Exception.NoReservationFoundException;
import com.masai.Exception.SomethingWentWrongException;

public interface ReservationService {
	void addReservation(Reservation reservation, String model , Transaction tran)throws SomethingWentWrongException,NoReservationFoundException;
	List<Reservation> getReservationList()throws SomethingWentWrongException,NoReservationFoundException;
	
}
