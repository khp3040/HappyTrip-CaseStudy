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

import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.City;

@SpringBootTest
public class CityRepositoryTest {

	@Autowired
	private CityRepository repository;
	private static File DATA_JSON = Paths.get("src","test","resources","Cities.json").toFile();
	
	@BeforeEach
	public void setUp() throws JsonEOFException,JsonMappingException,IOException{
		City cities[]= new ObjectMapper().readValue(DATA_JSON, City[].class);
		Arrays.stream(cities).forEach(repository::save);
	}

	@AfterEach
	public void cleanUp() {
		repository.deleteAll();
	}
	
	@Test
	
	@DisplayName("Test City saved sucessfully")
	public void testCityAddedSucessfully() {
		
		City city =new City("MUM","Mumbai");
		
		City cityadded =repository.save(city);
		Assertions.assertNotNull(cityadded," New City should be added");
		
		Assertions.assertNotNull(cityadded.getCityId()," New City should have id");
		
		Assertions.assertEquals(city.getCityName(), cityadded.getCityName());
		
	}	
	@Test
	@DisplayName("Test city not found for a non existing id")
	public void testCityNotFoundForNonExistingId() {
		
		City city =repository.findBycityId("PUN").orElseGet(()-> new City());
		Assertions.assertNull(city.getCityId(),"City with id PUN not exsit");
		
	}
	
	
}
