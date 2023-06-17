package com.masai.Entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int car_id;
	@Column(nullable = false)
	private String brand;
	@Column(nullable = false)
	private String model;
	@Column(nullable = false)
	private int year;
	@Column(nullable = false)
	private int seating_capacity;
	@Column(nullable = false)
	private String location;
	@Column(nullable = false)
	private double rate;
	@Column(nullable = false)
	private int mileage;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Avaliable availablity;
	@Enumerated(EnumType.STRING)
	private Avaliable is_deleted;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Customer_id")
	private User user;
	
	@OneToMany( cascade = CascadeType.ALL)
	private Set<Reservation> reservation;
	
	@OneToMany( cascade = CascadeType.ALL)
	private Set<Transaction> transcation;
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Car(int car_id, String brand, String model, int year, int seating_capacity, String location, double rate,
			int mileage, Avaliable availablity, Avaliable is_deleted, User user, Set<Reservation> reservation,
			Set<Transaction> transcation) {
		super();
		this.car_id = car_id;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.seating_capacity = seating_capacity;
		this.location = location;
		this.rate = rate;
		this.mileage = mileage;
		this.availablity = availablity;
		this.is_deleted = is_deleted;
		this.user = user;
		this.reservation = reservation;
		this.transcation = transcation;
	}
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getSeating_capacity() {
		return seating_capacity;
	}
	public void setSeating_capacity(int seating_capacity) {
		this.seating_capacity = seating_capacity;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public Avaliable getAvailablity() {
		return availablity;
	}
	public void setAvailablity(Avaliable availablity) {
		this.availablity = availablity;
	}
	public Avaliable getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(Avaliable is_deleted) {
		this.is_deleted = is_deleted;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Reservation> getReservation() {
		return reservation;
	}
	public void setReservation(Set<Reservation> reservation) {
		this.reservation = reservation;
	}
	public Set<Transaction> getTranscation() {
		return transcation;
	}
	public void setTranscation(Set<Transaction> transcation) {
		this.transcation = transcation;
	}
	
	
	
	
	
}
