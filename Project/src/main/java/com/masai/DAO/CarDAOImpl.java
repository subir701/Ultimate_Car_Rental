package com.masai.DAO;

import java.time.LocalDate;
import java.util.List;

import com.masai.Entity.Avaliable;
import com.masai.Entity.Car;
import com.masai.Entity.LoggedInUserId;
import com.masai.Entity.Reservation;
import com.masai.Entity.Transaction;
import com.masai.Entity.User;
import com.masai.Exception.NoCarFoundException;
import com.masai.Exception.SomethingWentWrongException;
import com.masai.Exception.UnableToAddCustomerException;
import com.masai.Exception.UnableToFetchCustomerException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class CarDAOImpl implements CarDAO {

	@Override
	public void addCar(Car car) throws SomethingWentWrongException {
		EntityManager em=null;
		EntityTransaction et=null;
		try {
			em = EMUtils.getConnection();
			Query query= em.createQuery("SELECT COUNT(c) FROM Car c WHERE model LIKE :model");
			query.setParameter("model", car.getModel());
			if((long)query.getSingleResult()>0) {
				throw new SomethingWentWrongException("The model "+car.getModel()+" is already present.");
			}
			et=em.getTransaction();
			et.begin();
			em.persist(car);
			et.commit();
		}catch(PersistenceException ex) {
			et.rollback();
			
			throw new SomethingWentWrongException("Unable to process the request, try again later");
		}finally {
			em.close();
		}

	}

	@Override
	public void updateCar(Car car) throws NoCarFoundException, SomethingWentWrongException {
		EntityManager em= null;
		EntityTransaction et= null;
		
		try {
			em=EMUtils.getConnection();
			Car temp=em.find(Car.class, car.getCar_id());
			if(temp==null) {
				throw new NoCarFoundException("No car is present of given id"+ car.getCar_id());
			}
			if(temp.getModel().equals(car.getModel())) {
				
				Query query= em.createQuery("SELECT COUNT(c) FROM Car c WHERE model LIKE :model");
				query.setParameter("model", car.getModel());
				if((long)query.getSingleResult()>0) {
					throw new SomethingWentWrongException("The model "+car.getModel()+" is already present.");
				}
				
			}
			et=em.getTransaction();
			et.begin();
			temp.setBrand(car.getBrand());
			temp.setLocation(car.getLocation());
			temp.setMileage(car.getMileage());
			temp.setRate(car.getRate());
			
			et.commit();
			
		}catch(IllegalArgumentException | IllegalStateException ex) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to process your request , try again later");
		}finally {
			em.close();
		}

	}

	@Override
	public List<Car> getListCar() throws NoCarFoundException, SomethingWentWrongException {
		EntityManager em=null;
		List<Car> list=null;
		try {
			em=EMUtils.getConnection();
			Query query= em.createQuery("FROM Car c ");
			
			list=(List<Car>)query.getResultList();
			if(list.size()==0) {
				throw new NoCarFoundException("No car is present");
			}
			
		}catch(IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request , try again later");
		}finally {
			em.close();
		}
		return list;
	}

	@Override
	public Car getCarByName(String model) throws NoCarFoundException, SomethingWentWrongException {
		EntityManager em= null;
		List<Car> list=null;
		try {
			em=EMUtils.getConnection();
			Query query=em.createQuery("FROM Car c WHERE model LIKE :model");
			query.setParameter("model", model);
			list=(List<Car>)query.getResultList();
			if(list.size()==0) {
				throw new NoCarFoundException("No car found");
			}
			
		}catch(IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process your request try again later");
		}finally {
			em.close();
		}
		return list.get(0);
	}

	@Override
	public void deleteCar(Car car) throws NoCarFoundException, SomethingWentWrongException {
		EntityManager em=null;
		EntityTransaction et = null;
		try {
			em=EMUtils.getConnection();
			car = em.find(Car.class, car.getCar_id());
			if(car == null) {
				throw new NoCarFoundException("No car found");
			}
			et=em.getTransaction();
			et.begin();
			car.setIs_deleted(Avaliable.YES);
			et.commit();
		}catch(IllegalArgumentException ex) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to process request, try again later");
		}finally {
			em.close();
		}

	}

	@Override
	public List<Car> getListCarUser() throws NoCarFoundException, SomethingWentWrongException {
		EntityManager em=null;
		List<Car> list=null;
		try {
			em=EMUtils.getConnection();
			Query query= em.createQuery("FROM Car c WHERE c.Customer_id = id");
			query.setParameter("id", LoggedInUserId.loggedInUserId);
			list=(List<Car>)query.getResultList();
			if(list.size()==0) {
				throw new NoCarFoundException("No car is present");
			}
			
		}catch(IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request , try again later");
		}finally {
			em.close();
		}
		return list;
		
	}
	

}
