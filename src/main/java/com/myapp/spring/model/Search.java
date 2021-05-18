package com.myapp.spring.model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="flight_search")
public class Search {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FLIGHT_NO")
	private Integer flightNo;
	
	@Column(name = "AIRLINE_NAME",nullable = false)
	private String airlineName;
	
	@Column(name = "FROM_CITY")
	private String fromCity;
	
	@Column(name = "TO_CITY")
	private String toCity;
	
	@Column(name = "DATE")
	private Date date;
	
	@Column(name = "DEP_TIME")
	private  Time depTime;
	
	@Column(name = "ARR_TIME")
	private  Time arrTime;
	
	@Column(name = "COST")
	private Double cost;
	
	@Column(name = "AVL_SEATS")
	private Integer avlSeats;

	public Integer getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(Integer flightNo) {
		this.flightNo = flightNo;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getDepTime() {
		return depTime;
	}

	public void setDepTime(Time depTime) {
		this.depTime = depTime;
	}

	public Time getArrTime() {
		return arrTime;
	}

	public void setArrTime(Time arrTime) {
		this.arrTime = arrTime;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}


	public Integer getAvlSeats() {
		return avlSeats;
	}

	public void setAvlSeats(Integer avlSeats) {
		this.avlSeats = avlSeats;
	}

	public Search(Integer flightNo, String airlineName, String fromCity, String toCity, Date date, Time depTime,
			Time arrTime, Double cost, Integer avlSeats) {
		this.flightNo = flightNo;
		this.airlineName = airlineName;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.date = date;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.cost = cost;
		this.avlSeats = avlSeats;
	}

	public Search() {
	}
}
	

