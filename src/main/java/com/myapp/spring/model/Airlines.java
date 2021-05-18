package com.myapp.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Airlines")

public class Airlines {
	
	
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="Airline_code",nullable=false,unique = true)
	private String airlineCode;
	
	@Column(name="Airline_name",nullable=false,unique = true)
	private String airlineName;

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public Airlines(String airlineCode, String airlineName) {
		this.airlineCode = airlineCode;
		this.airlineName = airlineName;
	}

	public Airlines() {
		
	}

}
