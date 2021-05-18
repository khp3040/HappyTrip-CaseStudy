package com.myapp.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Flight")

public class Flights {
	
	@Id
	@Column(name="Flight_number",nullable=false,unique = true)
	private int flightNumber;	

	@Column(name="Flight_name",nullable=false)
	private String flightName;
	
	@Column(name="Associated_airline",nullable=false)
	private String associatedAirline;
	
	@Column(name="Class_available",nullable=false)
	private String classAvailable;

	@Column(name="Number_of_seats")
	private int numberOfSeats;

	public Flights(int flightNumber, String flightName, String associatedAirline, String classAvailable,
			int numberOfSeats) {
		this.flightNumber = flightNumber;
		this.flightName = flightName;
		this.associatedAirline = associatedAirline;
		this.classAvailable = classAvailable;
		this.numberOfSeats = numberOfSeats;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getAssociatedAirline() {
		return associatedAirline;
	}

	public void setAssociatedAirline(String associatedAirline) {
		this.associatedAirline = associatedAirline;
	}

	public String getClassAvailable() {
		return classAvailable;
	}

	public void setClassAvailable(String classAvailable) {
		this.classAvailable = classAvailable;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Flights() {
		// TODO Auto-generated constructor stub
	}

}


