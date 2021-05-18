package com.myapp.spring.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

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
import com.myapp.spring.model.Flights;


@SpringBootTest
public class FlightsRepositoryTest {

	@Autowired
	private FlightsRepository repository;

	private static File DATA_JSON= Paths.get("src","test","resources","Flights.json").toFile();

	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException{

		Flights flights []=new ObjectMapper().readValue(DATA_JSON,Flights[].class);

		Arrays.stream(flights).forEach(repository::save);
	
	}

	@AfterEach
	public void cleanUp() {
	
		repository.deleteAll();
	
	}

	

	@Test
	@DisplayName("Test Flights saved successfully")
	public void testAirlinesSavedSuccessfully() {
	
		Flights flight = new Flights(3,"BRIT","BritishAirways","Business",100);
		
		Flights savedFlight = repository.save(flight);

		Assertions.assertNotNull(savedFlight,"New Flight should be saved");
	
		Assertions.assertNotNull(savedFlight.getFlightNumber(),"New Flight should have Number");
		
		Assertions.assertEquals(flight.getFlightName(), savedFlight.getFlightName());
		Assertions.assertEquals(flight.getFlightNumber(), savedFlight.getFlightNumber());
		Assertions.assertEquals(flight.getAssociatedAirline(), savedFlight.getAssociatedAirline());
		Assertions.assertEquals(flight.getClassAvailable(), savedFlight.getClassAvailable());
		Assertions.assertEquals(flight.getNumberOfSeats(), savedFlight.getNumberOfSeats());
	
	}
	
	
	
	
}