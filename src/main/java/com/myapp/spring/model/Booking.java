package com.myapp.spring.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name="booking_table")
	public class Booking {
		
		@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="Booking_ID")
		private int id;
		
		@Column(name = "Name")
		private String Name;

		@Column(name = "Date_Of_Birth",nullable = false)
		private String DateOfBirth;
		
		@Column(name = "from_city")
		private String fromcity;
		
		@Column(name = "to_city")
		private String tocity;
		
		@Column(name = "Date")
		private java.sql.Date Date;
		
		@Column(name = "seats")
		private int seats;
		
		@Column(name = "Flight_No")
		private int FlightNo;
		
		public Booking() {
		}

		public Booking(int id, String name, String dateOfBirth, String fromcity, String tocity, java.sql.Date date,
				int seats, int flightNo) {
			super();
			this.id = id;
			Name = name;
			DateOfBirth = dateOfBirth;
			this.fromcity = fromcity;
			this.tocity = tocity;
			Date = date;
			this.seats = seats;
			FlightNo = flightNo;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getDateOfBirth() {
			return DateOfBirth;
		}

		public void setDateOfBirth(String dateOfBirth) {
			DateOfBirth = dateOfBirth;
		}

		public String getFromcity() {
			return fromcity;
		}

		public void setFromcity(String fromcity) {
			this.fromcity = fromcity;
		}

		public String getTocity() {
			return tocity;
		}

		public void setTocity(String tocity) {
			this.tocity = tocity;
		}

		public java.sql.Date getDate() {
			return Date;
		}

		public void setDate(java.sql.Date date) {
			Date = date;
		}

		public int getSeats() {
			return seats;
		}

		public void setSeats(int seats) {
			this.seats = seats;
		}

		public int getFlightNo() {
			return FlightNo;
		}

		public void setFlightNo(int flightNo) {
			FlightNo = flightNo;
		}
		
		
		
	}

		