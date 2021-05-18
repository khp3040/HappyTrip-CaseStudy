package com.myapp.spring.web.api;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.Airlines;
import com.myapp.spring.repository.AirlineRepository;
import com.myapp.spring.service.AirlineService;

@SpringBootTest
@AutoConfigureMockMvc
public class AirlineAPITest {
	
	@MockBean
	private AirlineRepository repository;
	

	@MockBean
	private AirlineService service;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	//Admin is able to view all airlines
	//http://localhost:8888/admin/airlines/findAll
	@Test
	@DisplayName("Test Find All Airlines- GET /admin/airlines/findAll")
	public void testfindAllAirlines() throws Exception{

		Airlines airlines1 = new Airlines("AA12","AmericanAirlines");
		
		Airlines airlines2 = new Airlines("QA12","QattarAirways");

		
		List<Airlines> airlines = new ArrayList<>();
		airlines.add(airlines1);
		airlines.add(airlines2);
		
		doReturn(airlines).when(repository).viewairline();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/airlines"))
		//Validate status should be 
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		//Validate Response Body
		.andExpect(jsonPath("$[0].airlineCode", is("AA12")))
		.andExpect(jsonPath("$[0].airlineName", is("AmericanAirlines")))
		.andExpect(jsonPath("$[1].airlineCode", is("QA12")))
		.andExpect(jsonPath("$[1].airlineName", is("QattarAirways")));
		
	}	

	//Admin is able to view all airlines
	//http://localhost:8888/admin/airlines/find/{Airline_name}
	@Test
	@DisplayName("Test Find Product By Airline_name - GET /admin/airlines/find/{Airline_name}")
	public void testfindAirlinesByName() throws Exception{

		Airlines airlines = new Airlines("AA12","AmericanAirlines");
		
		doReturn(Optional.of(airlines)).when(repository).findByAirlineName(airlines.getAirlineName());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/airlines/findAirline/airline:{name}","AmericanAirlines"))
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.airlineCode", is("AA12")))
		.andExpect(jsonPath("$.airlineName", is("AmericanAirlines")));
		
	}
	
	//Admin is able to view all airlines
	//http://localhost:8888/admin/airlines/find/{Airline_code}
	@Test
	@DisplayName("Test Find Product By Airline_code - GET /admin/airlines/find/{Airline_code}")
	public void testfindAirlinesByCode() throws Exception{

		Airlines airlines = new Airlines("AA12","AmericanAirlines");
		
		doReturn(Optional.of(airlines)).when(repository).findByAirlineCode(airlines.getAirlineCode());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/airlines/findAirline/code:{code}","AA12"))
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.airlineCode", is("AA12")))
		.andExpect(jsonPath("$.airlineName", is("AmericanAirlines")));
		
	}
		
	//Admin should be able to add an airline
	//http://localhost:8888/admin/airlines/add
	@Test
	@DisplayName("Test Add New Airline - POST /admin/airlines/add")
	public void testaddNewAirline() throws Exception{

		Airlines newAirlines = new Airlines("AA12","AmericanAirlines");
		Airlines mockAirlines = new Airlines("AA12","AmericanAirlines");

		
		doReturn(mockAirlines).when(repository).save(ArgumentMatchers.any());
		
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
	@DisplayName("Test Bulk Insert New Airline - POST /admin/airlines/bulk")
	public void testbulkAirlineInsert() throws Exception{

		Airlines newAirlines1 = new Airlines("AA12","AmericanAirlines");
		
		Airlines newAirlines2 = new Airlines("QA12","QattarAirways");

		List<Airlines> newAirlines = new ArrayList<>();
		newAirlines.add(newAirlines1);
		newAirlines.add(newAirlines2);
		
		
		doReturn(newAirlines).when(service).saveall(ArgumentMatchers.any());
		
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
	public void testUpdateCity() throws Exception{
		
		Airlines newAirline = new Airlines("K12","Kingfisher");
		
		String json = new ObjectMapper().writeValueAsString(newAirline);
		
		doReturn(newAirline).when(service).updateAirline(ArgumentMatchers.any());
		
		mockMvc.perform(put("/admin/airlines/update")
				// Validate Status should be 200 ok and json response recived
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(json).accept(MediaType.APPLICATION_JSON))
				
				//Validate Response body
				
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.airlineCode",is("K12")))
				.andExpect(jsonPath("$.airlineName",is("Kingfisher")));

}}
