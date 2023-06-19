package com.masai.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
import jakarta.persistence.OneToOne;

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reservation_id;
	
	@ManyToOne
	@JoinColumn(name = "Customer_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "Car_id")
	private Car car;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transaction_id")
	private Transaction transaction;
	
	private LocalDate start_date;
	
	private LocalDate end_date;
	
	private LocalDateTime reservation_date_time;
	
	@Enumerated(EnumType.STRING)
	private Avaliable is_cancelled;

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation( User user, Car car, Transaction transaction, LocalDate start_date,
			LocalDate end_date, LocalDateTime reservation_date_time) {
		super();
		
		this.user = user;
		this.car = car;
		this.transaction = transaction;
		this.start_date = start_date;
		this.end_date = end_date;
		this.reservation_date_time = reservation_date_time;
		this.is_cancelled = Avaliable.NO;
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
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

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public LocalDateTime getReservation_date_time() {
		return reservation_date_time;
	}

	public void setReservation_date_time(LocalDateTime reservation_date_time) {
		this.reservation_date_time = reservation_date_time;
	}

	public Avaliable getIs_cancelled() {
		return is_cancelled;
	}

	public void setIs_cancelled(Avaliable is_cancelled) {
		this.is_cancelled = is_cancelled;
	}

	@Override
	public String toString() {
		return "Reservation [reservation_id=" + reservation_id + ", user=" + user.getUserId() + ", car=" + car.getCar_id() + ", transaction="
				+ transaction.getId() + ", start_date=" + start_date + ", end_date=" + end_date + ", reservation_date_time="
				+ reservation_date_time + ", is_cancelled=" + is_cancelled + "]";
	}
	
	
}
