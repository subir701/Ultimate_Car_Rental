package com.masai.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name="User_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Car_id")
	private Car car;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Reservation_id")
	private Reservation reservation;
	
	private double amount;
	
	@Enumerated(EnumType.STRING)
	private Transaction_status payment_status;
	
	private LocalDateTime paymentDate;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction( User user, Car car, Reservation reservation, double amount,
			Transaction_status payment_status, LocalDateTime paymentDate) {
		super();
		
		this.user = user;
		this.car = car;
		this.reservation = reservation;
		this.amount = amount;
		this.payment_status = Transaction_status.Completed;
		this.paymentDate = paymentDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Transaction_status getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(Transaction_status payment_status) {
		this.payment_status = payment_status;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", user=" + user.getUserId() + ", car=" + car.getCar_id() + ", reservation=" + reservation.getReservation_id()
				+ ", amount=" + amount + ", payment_status=" + payment_status + ", paymentDate=" + paymentDate + "]";
	}
	
	
}
