package com.myapp.spring.web.api;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.Flights;
import com.myapp.spring.repository.FlightsRepository;
import com.myapp.spring.service.FlightService;

@SpringBootTest
@AutoConfigureMockMvc
public class FlightsAPITest {
	
	@MockBean
	private FlightsRepository repository;
	
	@MockBean
	private FlightService service;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	//Admin is able to view all flights
	//http://localhost:8888/admin/flights/findAll
	@Test
	@DisplayName("Test Find All Flights- GET /admin/flights/findAll")
	public void testfindAllAirlines() throws Exception{

		Flights flight1 = new Flights(1,"QATT","QattarAirways","Business",100);
		
		Flights flight2 = new Flights(2,"AIR","AirIndia","Economy",300);
	
		
		List<Flights> flights = new ArrayList<>();
		flights.add(flight1);
		flights.add(flight2);
		
		doReturn(flights).when(repository).findAll();
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

		Flights newFlight = new Flights(1,"QATT","QattarAirways","Business",100);
		Flights mockFlight = new Flights(1,"QATT","QattarAirways","Business",100);

		doReturn(mockFlight).when(repository).save(ArgumentMatchers.any());
		
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
		
		doReturn(newFlight).when(service).updateFlight(ArgumentMatchers.any());
		
		String json = new ObjectMapper().writeValueAsString(newFlight);
	
		mockMvc.perform(MockMvcRequestBuilders.put("/admin/flights/update")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(json));
	}

	

	

}
