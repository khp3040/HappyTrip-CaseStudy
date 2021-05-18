package com.myapp.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Route")

public class RouteModel {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Route_id",nullable = false,unique = true)
	private int routeId;
	
	@Column(name="From_City",nullable=false)
	private String fromCity;
	
	@Column(name="To_City",nullable=false)
	private String toCity;
	
	@Column(name="Distance",nullable=false)
	private int distance;


	public RouteModel(int routeId, String fromCity, String toCity, int distance) {
		this.routeId = routeId;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.distance = distance;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
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

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}







	public RouteModel() {}
	

}
