package com.masai.Service;

import java.util.List;

import com.masai.DAO.CarDAO;
import com.masai.DAO.CarDAOImpl;
import com.masai.Entity.Car;
import com.masai.Exception.NoCarFoundException;
import com.masai.Exception.SomethingWentWrongException;

public class CarServiceImpl implements CarService {

	@Override
	public void addCar(Car car) throws SomethingWentWrongException {
		CarDAO dao= new CarDAOImpl();
		
			dao.addCar(car);
		

	}

	@Override
	public void updateCar(Car car) throws NoCarFoundException, SomethingWentWrongException {
		CarDAO dao= new CarDAOImpl();
		
			dao.updateCar(car);
		

	}

	@Override
	public List<Car> getListCar() throws NoCarFoundException, SomethingWentWrongException {
		CarDAO dao= new CarDAOImpl();
		return dao.getListCar();
		
	}

	@Override
	public Car getCarByName(String model) throws NoCarFoundException, SomethingWentWrongException {
		CarDAO dao= new CarDAOImpl();
		
		return dao.getCarByName(model);
	}

	@Override
	public void deleteCar(Car car) throws NoCarFoundException, SomethingWentWrongException {
		CarDAO dao= new CarDAOImpl();
		
			dao.deleteCar(car);
		

	}

	@Override
	public List<Car> getListCarUser() throws NoCarFoundException, SomethingWentWrongException {
		
		 CarDAO dao= new CarDAOImpl();
		 return dao.getListCarUser();
	}

}
