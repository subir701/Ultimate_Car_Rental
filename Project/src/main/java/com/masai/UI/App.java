package com.masai.UI;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
	static void displayAdminMenu() {
		System.out.println("1. Add car");
		System.out.println("2. Update car");
		System.out.println("3. Delete car");
		System.out.println("4. List of Car");
		System.out.println("5. Reservation List");
		System.out.println("6. Transaction List");
		System.out.println("0. Log out");
	}
	static void adminMenu(Scanner sc) {
		int choice=0;
		do {
			displayAdminMenu();
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				AdminUI.addCar(sc);
				break;
			case 2:
				AdminUI.updateCar(sc);
				break;
			case 3:
				AdminUI.deleteCar(sc);
				break;
			case 4:
				AdminUI.getListOfCar();
				break;
			case 5:
				CustomerUI.reservationList();
				break;
			case 6:
				CustomerUI.transcationList();
				break;
			case 0:
				choice=0;
				System.out.println("Bye Bye");
				break;
			}
		} while (choice != 0);
	}
	
	static void adminLogin(Scanner sc) {
		System.out.println("Enter username : ");
		String username=sc.next();
		System.out.println("Enter password : ");
		String password=sc.next();
		if(username.equals("admin") && password.equals("admin")) {
			adminMenu(sc);
		}
	}
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
    	int choice = 0;
    	do {
    		System.out.println("1. Admin Login");
    		System.out.println("2. Customer Login");
    		System.out.println("3. Customer Registration");
    		System.out.println("0. Exit");
    		System.out.print("Enter Selection ");
    		choice = sc.nextInt();
    		switch(choice) {
    			case 1:
    				adminLogin(sc);
    				break;
    			case 2:
    				CustomerUI.loginUser(sc);;
    				break;
    			case 3:
    				CustomerUI.registerCustomer(sc);
    				break;
    			case 0:
    				System.out.println("Thanks for using the services");
    				break;
    			default:
    				System.out.println("Invalid Selection, try again");
    		}
    	}while(choice != 0);
    	sc.close();
    }
}
