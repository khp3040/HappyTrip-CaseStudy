package com.myapp.spring.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Profile")
public class Profile {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false,unique=true)
    private int id;
    
    @Column(name="full_name")
    private String fullName;
    
    @Column(name="gender")
    private String gender;
    
  	@Column(name="dob")
    private String dob;
    
    @Column(name="email", nullable=false, length=200)
    private String email;
    
   	@Column(name="address")
    private String address;
    
    @Column(name="state")
    private String state;
    
    @Column(name="city")
    private String city;
    
    @Column(name="country")
    private String country;
    
    
    
    @Column(name="mobile_number")
    private Integer mobileNumber;
    
    @Column(name="pincode")
    private Integer pincode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Integer mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public Profile(int id, String fullName, String gender, String dob, String email, String address, String state,
			String city, String country, Integer mobileNumber, Integer pincode) {
		this.id = id;
		this.fullName = fullName;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
		this.address = address;
		this.state = state;
		this.city = city;
		this.country = country;
		this.mobileNumber = mobileNumber;
		this.pincode = pincode;
	}
public Profile() {}
}