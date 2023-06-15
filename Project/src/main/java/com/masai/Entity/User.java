package com.masai.Entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private int age;
	@Column(unique = true, nullable = false)
	private String pancard;
	@Column(nullable = false,name="Driving_License")
	private String license;
	@Column(nullable = false , length = 10)
	private int phoneNumber;
	private String address;
	@Column(name = "Date_of_birth")
	private LocalDate dob;
	@Column(unique = true , nullable = false , length = 20)
	private String username;
	@Column(unique = true, nullable = false, length = 10)
	private String password;
	@Enumerated(EnumType.STRING)
	private Avaliable is_deleted;
	@OneToMany(mappedBy = "Customer" , cascade = CascadeType.ALL)
	private Set<Car> car;
	@OneToMany(mappedBy = "Reservation_User" , cascade = CascadeType.ALL)
	private Set<Reservation> reservation;
	@OneToMany(mappedBy = "Transcarion_User" , cascade = CascadeType.ALL)
	private Set<Transaction> transacation;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public User(int userId, String name, int age, String pancard, String license, int phoneNumber, String address,
			LocalDate dob, String username, String password, Avaliable is_deleted, Set<Car> car) {
		super();
		this.userId = userId;
		this.name = name;
		this.age = age;
		this.pancard = pancard;
		this.license = license;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.dob = dob;
		this.username = username;
		this.password = password;
		this.is_deleted = is_deleted;
		this.car = car;
	}


	public Avaliable getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Avaliable is_deleted) {
		this.is_deleted = is_deleted;
	}

	

	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPancard() {
		return pancard;
	}
	public void setPancard(String pancard) {
		this.pancard = pancard;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
