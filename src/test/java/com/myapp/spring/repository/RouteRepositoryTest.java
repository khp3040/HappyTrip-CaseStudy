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
import com.myapp.spring.model.RouteModel;

@SpringBootTest
public class RouteRepositoryTest {

	@Autowired
	private RouteRepository repository;
	private static File DATA_JSON = Paths.get("src","test","resources","Route.json").toFile();
	
	@BeforeEach
	public void setUp() throws JsonEOFException,JsonMappingException,IOException{
		RouteModel cities[]= new ObjectMapper().readValue(DATA_JSON, RouteModel[].class);
		Arrays.stream(cities).forEach(repository::save);
	}

	@AfterEach
	public void cleanUp() {
		repository.deleteAll();
	}
	
	@Test
	
	@DisplayName("Test Route saved sucessfully")
	public void testRouteAddedSucessfully() {
		
		RouteModel route =new RouteModel(4, "Mumbai", "Bangalore", 836);
		
		RouteModel cityadded =repository.save(route);
		Assertions.assertNotNull(cityadded," New route should be added");
		
		Assertions.assertNotNull(cityadded.getRouteId()," New route should have id");
		
		
	}	
	
	
}
