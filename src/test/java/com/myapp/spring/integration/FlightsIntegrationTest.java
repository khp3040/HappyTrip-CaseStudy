package com.myapp.spring.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.Flights;
import com.myapp.spring.repository.FlightsRepository;
import com.myapp.spring.service.FlightService;

@SpringBootTest
@AutoConfigureMockMvc
public class FlightsIntegrationTest {
	
	@Autowired
	private FlightsRepository repository;
	
	@Autowired
	private FlightService service;
	
	@Autowired
	private MockMvc mockMvc;

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
	@DisplayName("Test Find All Flights- GET /admin/flights/findAll")
	public void testfindAllAirlines() throws Exception{

		Flights flight1 = new Flights(1,"QATT","QattarAirways","Business",100);
		
		Flights flight2 = new Flights(2,"AIR","AirIndia","Economy",300);
	
		
		List<Flights> flights = new ArrayList<>();
		flights.add(flight1);
		flights.add(flight2);
		
		
		String json = new ObjectMapper().writeValueAsString(flights);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/flights"))
		//Validate status should be 
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		//Validate Response Body
		.andExpect(content().json(json));
		
	}	

	//Admin should be able to add an flight
	//http://localhost:8888/admin/airlines/add
	@Test
	@DisplayName("Test Add New Airline - POST /admin/flights/add")
	public void testaddNewFlight() throws Exception{

		Flights newFlight = new Flights(3,"EMI","Emirates","Economy",200);
		
		String json = new ObjectMapper().writeValueAsString(newFlight);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/flights")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newFlight)))
		
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(content().json(json));

	}

	@Test
	@DisplayName("Test Add New Airline - Put /admin/flights/add")
	public void testUpdateFlight() throws Exception{

		Flights newFlight = new Flights(1,"QATT","QattarAirways","Business",100);
		
		String json = new ObjectMapper().writeValueAsString(newFlight);
	
		mockMvc.perform(MockMvcRequestBuilders.put("/admin/flights/update")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(json));
	}

	
	

}
