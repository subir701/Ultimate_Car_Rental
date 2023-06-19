package com.masai.DAO;

import java.util.List;

import com.masai.Entity.Avaliable;
import com.masai.Entity.Car;
import com.masai.Entity.Car_Status;
import com.masai.Entity.LoggedInUserId;
import com.masai.Entity.Reservation;
import com.masai.Entity.Transaction;
import com.masai.Entity.Transaction_status;
import com.masai.Entity.User;
import com.masai.Exception.NoCarFoundException;
import com.masai.Exception.NoReservationFoundException;
import com.masai.Exception.NoTransactionFoundException;
import com.masai.Exception.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class TransactionDAOImpl implements TransactionDAO {

	@Override
	public void addTransaction(Transaction transcation, int reservation_id,String model) throws SomethingWentWrongException {
		EntityManager em=null;
		EntityTransaction et=null;
		CarDAO dao= new CarDAOImpl();
		try {
			em=EMUtils.getConnection();
			Car temp=dao.getCarByName(model);
			
			
			Car car =em.find(Car.class, temp.getCar_id());
			Reservation reserve= em.find(Reservation.class, reservation_id);
			User user= em.find(User.class, LoggedInUserId.loggedInUserId);
			
			
			et=em.getTransaction();
			et.begin();
			car.setAvailablity(Avaliable.NO);
			car.setStatus(Car_Status.RESERVED);
			transcation.setCar(car);
			transcation.setReservation(reserve);
			transcation.setUser(user);
			transcation.setPayment_status(Transaction_status.Completed);
			em.persist(transcation);
			et.commit();
		}catch(PersistenceException ex) {
			et.rollback();
			ex.printStackTrace();
			throw new SomethingWentWrongException("Unable to process request, try again later");
		} catch (NoCarFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			em.close();
		}

		
	}

	@Override
	public List<Transaction> getListTranscation() throws SomethingWentWrongException, NoTransactionFoundException {
		EntityManager em=null;
		List<Transaction> list=null;
		try {
			em = EMUtils.getConnection();
			Query query= em.createQuery("FROM Transaction t");
			list= (List<Transaction>)query.getResultList();
			if(list.size()==0) {
				throw new NoTransactionFoundException("No transaction found");
			}
		}catch(IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		}finally {
			em.close();
		}
		return list;
	}

}
