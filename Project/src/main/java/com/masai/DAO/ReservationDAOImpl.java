package com.masai.DAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.masai.Entity.Avaliable;
import com.masai.Entity.Car;
import com.masai.Entity.Car_Status;
import com.masai.Entity.LoggedInUserId;
import com.masai.Entity.Reservation;
import com.masai.Entity.Transaction;
import com.masai.Entity.User;
import com.masai.Exception.NoCarFoundException;
import com.masai.Exception.NoReservationFoundException;
import com.masai.Exception.SomethingWentWrongException;
import com.masai.Exception.UnableToFetchCustomerException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class ReservationDAOImpl implements ReservationDAO {

	@Override
	public List<Reservation> getReservationList() throws SomethingWentWrongException, NoReservationFoundException {
		EntityManager em=null;
		List<Reservation> list=null;
		try {
			em=EMUtils.getConnection();
			Query query=em.createQuery("FROM Reservation r");
			list=(List<Reservation>)query.getResultList();
			if(list.size()==0) {
				throw new NoReservationFoundException("No reservation found");
			}
			
		}catch(IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		}finally {
			em.close();
		}
		return list;
	}

	

	@Override
	public void addReservation(Reservation reservation, String model, Transaction tran) throws SomethingWentWrongException, NoReservationFoundException {
		EntityManager em=null;
		EntityTransaction et=null;
		Set<Car> setCar=null;
		Set<Reservation> setUser=null;
		Set<Reservation> set=null;
		CarDAO dao= new CarDAOImpl();
		TransactionDAO Trandao = new TransactionDAOImpl();
		try {
			em=EMUtils.getConnection();
			Car car=dao.getCarByName(model);
			
			if(car.getAvailablity()==Avaliable.NO || car.getStatus()==Car_Status.RESERVED) {
				throw new NoReservationFoundException("The car is not available right now");
			}
			Car temp=em.find(Car.class, car.getCar_id());
			User user=em.find(User.class, LoggedInUserId.loggedInUserId);
			et=em.getTransaction();
			tran.setReservation(reservation);
			Trandao.addTransaction(tran, reservation.getReservation_id(),model);
			et.begin();
			reservation.setUser(user);
			reservation.setCar(temp);
			if(car.getReservation()==null ) {
				 
				set= new HashSet<>();
				
				set.add(reservation);
				car.setReservation(set);
				car.setUser(user);
				
			}else {
				
				set=car.getReservation();
				
				set.add(reservation);
				car.setReservation(set);
				car.setUser(user);
				
			}
			if(user.getReservation()==null && user.getCar()==null) {
				
				setUser= new HashSet<>();
				setCar= new HashSet<>();
				
				setUser.add(reservation);
				setCar.add(temp);
				user.setReservation(set);
				user.setCar(setCar);
				
			}else {
				
				setUser= user.getReservation();
				setCar= user.getCar();
				
				set.add(reservation);
				setCar.add(temp);
				user.setReservation(set);
				user.setCar(setCar);
			}
			reservation.setTransaction(tran);
			em.persist(reservation);
			
			et.commit();
		}catch(PersistenceException | NoCarFoundException ex) {
			et.rollback();
			ex.printStackTrace();
			throw new SomethingWentWrongException("Unable to process your request, try again later");
		}
		
	}

}
