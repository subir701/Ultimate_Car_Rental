package com.masai.UI;

import java.util.List;
import java.util.Scanner;

import com.masai.Entity.Car;
import com.masai.Exception.NoCarFoundException;
import com.masai.Exception.SomethingWentWrongException;
import com.masai.Service.CarService;
import com.masai.Service.CarServiceImpl;

public class AdminUI {
	static void addCar(Scanner sc) {
		System.out.println("Enter brand name : ");
		String brand=sc.next();
		System.out.println("Enter model name : ");
		String model=sc.next();
		System.out.println("Enter car manufactured year : ");
		int year=sc.nextInt();
		System.out.println("Enter seating capacity of car : ");
		int seating = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter location of car is parked : ");
		String location=sc.nextLine();
		System.out.println("Enter rental rate of car : ");
		double rate = sc.nextDouble();
		System.out.println("Enter mileage of car : ");
		int mileage = sc.nextInt();
		Car car= new Car(brand, model, year, seating, location, rate, mileage, null, null, null);
		CarService service = new CarServiceImpl();
		try {
			service.addCar(car);
			System.out.println("Car Added Successfully");
		} catch (SomethingWentWrongException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	static void updateCar(Scanner sc) {
		System.out.println("Enter id : ");
		int id=sc.nextInt();
		System.out.println("Enter brand :");
		String brand=sc.next();
		sc.nextLine();
		System.out.println("Enter location of car is parked : ");
		String location=sc.nextLine();
		System.out.println("Enter rental rate of car : ");
		double rate = sc.nextDouble();
		System.out.println("Enter mileage of car : ");
		int mileage = sc.nextInt();
		
		Car car= new Car(brand, null, mileage, 0, location, rate, mileage, null, null, null);
		car.setCar_id(id);
		
		CarService service = new CarServiceImpl();
		
		try {
			service.updateCar(car);
			System.out.println("Car is updated");
		} catch (NoCarFoundException | SomethingWentWrongException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void deleteCar(Scanner sc) {
		System.out.println("Enter car id :");
		int id=sc.nextInt();
		Car car= new Car();
		car.setCar_id(id);
		CarService service = new CarServiceImpl();
		try {
			service.deleteCar(car);
		} catch (NoCarFoundException | SomethingWentWrongException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	static void getListOfCar() {
		CarService service = new CarServiceImpl();
		try {
			List<Car>list=service.getListCar();
			System.out.println("+--------+-------------+-------+------------+-----------------------------+---------+-------+---------+------------------+------+-----------+");
			System.out.println("| car_id | availablity | brand | is_deleted | location                    | mileage | model | rate    | seating_capacity | year | status    |");
			System.out.println("+--------+-------------+-------+------------+-----------------------------+---------+-------+---------+------------------+------+-----------+");
			for(Car i: list) {
				System.out.println("| "+i.getCar_id()+" | "+i.getAvailablity()+" | "+i.getBrand()+" | "+i.getIs_deleted()+" | "+i.getLocation()+"   | "+i.getMileage()+" | "+i.getModel()+" | "+i.getRate()+"    | "+i.getSeating_capacity()+" | "+i.getYear()+" | "+i.getStatus()+"    |");
			}
			System.out.println("+--------+-------------+-------+------------+-----------------------------+---------+-------+---------+------------------+------+-----------+");
			
		} catch (NoCarFoundException | SomethingWentWrongException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
//		addCar(sc);
		getListOfCar();
	}
}
