package com.myapp.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Cities")

public class City {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="City_id",nullable = false,unique = true)
	private String cityId;
	
	@Column(name="City_name",nullable=false,unique = true)
	private String cityName;
	
	public City(){}
	
	public City(String cityId, String cityName) {
		this.cityId = cityId;
		this.cityName = cityName;
		
	}
	
	public String getCityId() {
		return cityId;
	}
	
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	

}
