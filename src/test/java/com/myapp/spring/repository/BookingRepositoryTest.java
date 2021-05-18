package com.myapp.spring.repository;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.Booking;

@SpringBootTest
public class BookingRepositoryTest {

	@Autowired
	private BookingRepository repository;
	
	private static File DATA_JSON= Paths.get("src","test","resources","bookinginfo.json").toFile();
	
	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException {
	
	Booking BookingInfo []=new ObjectMapper().readValue(DATA_JSON,Booking[].class);
	
	//save each product to database
	Arrays.stream(BookingInfo).forEach(repository::save);
	}

@AfterEach
public void cleanUp() {
	repository.deleteAll();
}


@Test

@DisplayName("Test product saved sucessfully")
public void testProductSavedSucessfully() {
	// given a mock product
	java.sql.Date date = java.sql.Date.valueOf("2021-01-05");
	Booking newuserbooking = new Booking(3,null, "", null, null, date, 4, 0);
	
	Booking savedBooking=repository.save(newuserbooking);
	
	Assertions.assertNotNull(savedBooking, "user booking info should be saved");
	
}
	
//@Test
//
//@DisplayName("Test product updated sucessfully")
//public void testBookedSucessfully() {
//	// given a mock product
//	Booking updatebooktable = new Booking();
//
//	
//	Booking updatedBooking=repository.save(b);
//	
//
//}
	
}

