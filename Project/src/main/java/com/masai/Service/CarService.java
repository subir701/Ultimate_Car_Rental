package com.masai.Service;

import java.util.List;

import com.masai.Entity.Car;
import com.masai.Exception.NoCarFoundException;
import com.masai.Exception.SomethingWentWrongException;

public interface CarService {
	void addCar(Car car)throws SomethingWentWrongException;
	void updateCar(Car car)throws NoCarFoundException,SomethingWentWrongException;
	List<Car> getListCar()throws NoCarFoundException,SomethingWentWrongException;
	Car getCarByName(String model)throws NoCarFoundException,SomethingWentWrongException;
	void deleteCar(Car car)throws NoCarFoundException,SomethingWentWrongException;
	List<Car> getListCarUser()throws NoCarFoundException,SomethingWentWrongException;
}
