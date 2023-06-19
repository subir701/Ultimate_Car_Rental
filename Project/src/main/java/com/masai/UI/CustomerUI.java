package com.masai.UI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.masai.Entity.Car;
import com.masai.Entity.LoggedInUserId;
import com.masai.Entity.Reservation;
import com.masai.Entity.Transaction;
import com.masai.Entity.User;
import com.masai.Exception.NoCarFoundException;
import com.masai.Exception.NoCustomerFoundException;
import com.masai.Exception.NoReservationFoundException;
import com.masai.Exception.NoTransactionFoundException;
import com.masai.Exception.SomethingWentWrongException;
import com.masai.Exception.UnableToAddCustomerException;
import com.masai.Exception.UnableToFetchCustomerException;
import com.masai.Service.CarService;
import com.masai.Service.CarServiceImpl;
import com.masai.Service.CustomerService;
import com.masai.Service.CustomerServiceImpl;
import com.masai.Service.ReservationService;
import com.masai.Service.ReservationServiceImpl;
import com.masai.Service.TransactionService;
import com.masai.Service.TranscationServiceImpl;

public class CustomerUI {
	static void registerCustomer(Scanner sc) {
		System.out.println("Enter Full name : ");
		String name=sc.nextLine();
		System.out.println("Enter your age : ");
		int age=sc.nextInt();
		System.out.println("Enter your PAN car Number : ");
		String pan=sc.next();
		System.out.println("Enter your License Number : ");
		String license=sc.next();
		System.out.println("Enter your Phone Number : ");
		int phone=sc.nextInt();
		
		sc.nextLine();
		System.out.println("Enter your address : ");
		String address=sc.nextLine();
		System.out.println("Enter Date of Brith(YYY-MM-DD) : ");
		LocalDate date= LocalDate.parse(sc.next());
		System.out.println("Enter unique Username : ");
		String username=sc.next();
		System.out.println("Enter password : ");
		String password=sc.next();
		
		User customer= new User(name, age, pan, license, phone, address, date, username, password, null, null, null, null);
		CustomerService service= new CustomerServiceImpl();
		try {
			service.addCustomer(customer);
			System.out.println("Successfully Register");
		} catch (UnableToAddCustomerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void loginUser(Scanner sc) {
		System.out.println("Enter username : ");
		String username=sc.next();
		System.out.println("Enter password : ");
		String password= sc.next();
		CustomerService service= new CustomerServiceImpl();
		try {
			service.loginCustomer(username, password);
			System.out.println("Login Successfully");
			userMenu(sc);
		} catch (UnableToFetchCustomerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void changePassword(Scanner sc) {
		System.out.println("Enter old password : ");
		String old=sc.next();
		System.out.println("Enter new password : ");
		String newPass=sc.next();
		CustomerService service= new CustomerServiceImpl();
		try {
			service.changePassword(old, newPass);
			System.out.println("Your password is changed");
		} catch (NoCustomerFoundException | UnableToFetchCustomerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void deleteAccount(Scanner sc) {
		System.out.println("Are sure you want to delete your accont ? ");
		System.out.println("Enter Yes or No");
		String choice=sc.next();
		if(choice.equals("Yes")) {
			CustomerService service= new CustomerServiceImpl();
			try {
				service.deleteCustomer();
				System.out.println("Your account is deleted");
			} catch (UnableToFetchCustomerException e) {
				System.out.println(e.getMessage());
			}
			
		}else {
			return;
		}
	}
	
	static void getCarList() {
		CarService service= new CarServiceImpl();
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
	static void searchCarByName(Scanner sc) {
		System.out.println("Enter model name : ");
		String model=sc.next();
		CarService service= new CarServiceImpl();
		try {
			Car car =service.getCarByName(model);
			System.out.println("+--------+-------------+-------+-----------------------------+------------+---------+-------+---------------------------+------+-----------+");
			System.out.println("| car_id | availablity | brand | location                    | mileage    | model   | rate  | seating_capacity          | year | status    |");
			System.out.println("+--------+-------------+-------+-----------------------------+------------+---------+-------+---------------------------+------+-----------+");
			System.out.println("| "+car.getCar_id()+" | "+car.getAvailablity()+" | "+car.getBrand()+" | "+car.getLocation()+" | "+car.getMileage()+" | "+car.getModel()+" | "+car.getRate()+" | "+car.getSeating_capacity()+" | "+car.getYear()+" | "+car.getStatus()+" |");
		} catch (NoCarFoundException | SomethingWentWrongException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void reservationCar(Scanner sc) {
		System.out.println("Enter car model name : ");
		String model=sc.next();
		System.out.println("Enter start date : ");
		LocalDate start=LocalDate.parse(sc.next());
		System.out.println("Enter end date : ");
		LocalDate end=LocalDate.parse(sc.next());
		LocalDateTime time=LocalDateTime.now();
		
		System.out.println("Enter amount of transcation");
		double amount = sc.nextDouble();
		Reservation reserve= new Reservation(null, null, null, start, end, time);
		Transaction tran= new Transaction(null, null, reserve, amount, null, time);
		ReservationService service= new ReservationServiceImpl();
		try {
			service.addReservation(reserve, model, tran);
		} catch (SomethingWentWrongException | NoReservationFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void reservationList() {
		ReservationService service= new ReservationServiceImpl();
		try {
			List<Reservation> list= service.getReservationList();
			for(Reservation i: list) {
				System.out.println(i);
			}
		} catch (SomethingWentWrongException | NoReservationFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	static void transcationList() {
		TransactionService service= new TranscationServiceImpl();
		try {
			List<Transaction> list= service.getListTranscation();
			System.out.println("Transcation List");
			for(Transaction i:list) {
				System.out.println(i);
			}
		} catch (SomethingWentWrongException | NoTransactionFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	static void displayMenu() {
		System.out.println("1. View all car");
		System.out.println("2. Search car by name");
		System.out.println("3. Reservation ");
		System.out.println("4. Reservation List");
		System.out.println("5. Transaction List");
		System.out.println("6. Change Password");
		System.out.println("7. Delete account");
		System.out.println("0. Log Out");
	}
	
	static void userMenu(Scanner sc) {
		int choice=0;
		do {
			displayMenu();
			System.out.println("Enter your selection :");
			choice=sc.nextInt();
			switch (choice) {
			case 1: 
				getCarList();
				break;
			
			case 2:
				searchCarByName(sc);
				break;
			case 3:
				reservationCar(sc);
				break;
			case 4:
				reservationList();
				break;
			case 5:
				transcationList();
				break;
			case 6:
				changePassword(sc);
				break;
			case 7:
				deleteAccount(sc);
				System.out.println("Loggout");
				break;
			case 0:
				LoggedInUserId.loggedInUserId = -1;	//-1 id cannot belong to any customer
				System.out.println("Bye Bye User");
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		} while (choice !=0);
	}
}
