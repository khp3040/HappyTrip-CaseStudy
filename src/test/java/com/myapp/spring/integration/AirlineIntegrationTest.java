package com.myapp.spring.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.Airlines;
import com.myapp.spring.repository.AirlineRepository;
import com.myapp.spring.service.AirlineService;

@SpringBootTest
@AutoConfigureMockMvc

public class AirlineIntegrationTest {
	
	@Autowired
	private AirlineRepository repository;
	
	@Autowired
	private AirlineService service;
	
	@Autowired
	private MockMvc mockMvc;

	private static File DATA_JSON= Paths.get("src","test","resources","Airlines.json").toFile();

	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException{

		Airlines airlines []=new ObjectMapper().readValue(DATA_JSON,Airlines[].class);

		Arrays.stream(airlines).forEach(repository::save);
	
	}

	@AfterEach
	public void cleanUp() {
	
		repository.deleteAll();
	
	}

	//Admin is able to view all airlines
	//http://localhost:8888/admin/airlines/findAll
	@Test
	@DisplayName("Test Get All Airlines- GET /admin/airlines")
	public void testfindAllAirlines() throws Exception{

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/airlines"))
		//Validate status should be 
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		//Validate Response Body
		.andExpect(jsonPath("$[0].airlineCode", is("AI12")))
		.andExpect(jsonPath("$[0].airlineName", is("AirIndia")))
		.andExpect(jsonPath("$[1].airlineCode", is("BA12")))
		.andExpect(jsonPath("$[1].airlineName", is("BritishAirlines")));
		
	}
	
	//Admin is able to view all airlines
	//http://localhost:8888/admin/airlines/findAirline/airline:{name}
	@Test
	@DisplayName("Test Find Airline_name - GET /admin/airlines/findAirline/airline:{name}")
	public void testfindAirlinesByName() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/airlines/findAirline/airline:{name}","Kingfisher"))
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.airlineCode", is("K12")))
		.andExpect(jsonPath("$.airlineName", is("Kingfisher")));

		
	}
	
	//Admin is able to view all airlines
	//http://localhost:8888/admin/airlines/find/{Airline_code}
	@Test
	@DisplayName("Test Find Product By Airline_code - GET /admin/airlines/findAirline/code:{code}}")
	public void testfindAirlinesByCode() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/airlines/findAirline/code:{code}","K12"))
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.airlineCode", is("K12")))
		.andExpect(jsonPath("$.airlineName", is("Kingfisher")));
		
		
	}
	
	//Admin should be able to add an airline
	//http://localhost:8888/admin/airlines/add
	@Test
	@DisplayName("Test Add New Airline - POST /admin/airlines/add")
	public void testaddNewAirline() throws Exception{

		Airlines newAirlines = new Airlines("AA12","AmericanAirlines");

		mockMvc.perform(MockMvcRequestBuilders.post("/admin/airlines")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newAirlines)))
		
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.airlineCode", is("AA12")))
		.andExpect(jsonPath("$.airlineName", is("AmericanAirlines")));

	}
	
	//Admin should be able to add an airline
	//http://localhost:8888/admin/airlines/add/bulk
	@Test
	@DisplayName("Test Bulk Insert New Airline - POST /admin/airlines/add/bulk")
	public void testbulkAirlineInsert() throws Exception{

		Airlines newAirlines1 = new Airlines("AA12","AmericanAirlines");
		Airlines newAirlines2 = new Airlines("QA12","QattarAirways");
		
		
		List<Airlines> newAirlines = new ArrayList<>();
		newAirlines.add(newAirlines1);
		newAirlines.add(newAirlines2);
		
		String json = new ObjectMapper().writeValueAsString(newAirlines);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/airlines/bulk")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newAirlines)))
		
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(content().json(json));
		
	}

	//Admin should be able to update an airline
	//http://localhost:8888/admin/airlines/update/{airline_code}
	@Test
	@DisplayName("Test Update Airline")
	public void testUpdateAirline() throws Exception{
		
		Airlines newAirline = new Airlines("K12","Kingfisher");
		
		String json = new ObjectMapper().writeValueAsString(newAirline);
		
		mockMvc.perform(put("/admin/airlines/update")
				// Validate Status should be 200 ok and json response recived
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(json).accept(MediaType.APPLICATION_JSON))
				
				//Validate Response body
				
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.airlineCode",is("K12")))
				.andExpect(jsonPath("$.airlineName",is("Kingfisher")));
	}

}
