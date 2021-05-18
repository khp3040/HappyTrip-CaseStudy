package com.myapp.spring.web.api;

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
import com.myapp.spring.model.City;
import com.myapp.spring.repository.CityRepository;
import com.myapp.spring.service.CityService;

@SpringBootTest
@AutoConfigureMockMvc

public class CityApiTest {

	@MockBean
	private CityRepository repository;
	
	@MockBean
	private CityService service;
	
	@Autowired
	private MockMvc mockMvc;
	

	@Test
	@DisplayName("Test Add City")
	public void testAddNewCity() throws Exception{
		
		City newcity=new City("MUM","Mumbai");
		City mockcity=new City("MUM","Mumbai");
		
		doReturn(mockcity).when(repository).save(ArgumentMatchers.any());
		
		mockMvc.perform(post("/admin/cities")
				// Validate Status should be 200 ok and json response recived
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(newcity)))
				
				//Validate Response body
				
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.cityId",is("MUM")))
				.andExpect(jsonPath("$.cityName",is("Mumbai")));
	}
	
	@Test
	@DisplayName("Test view all city information")
	public void testGetAllProductsById() throws Exception {
		
		//Prepare Mock Product
		City city1=new City("MUM","Mumbai");
		City city2=new City("PUN","Pune");
	
		List<City> cities = new ArrayList<>();
		cities.add(city1);
		cities.add(city2);
		
		doReturn(cities).when(repository).viewcity();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/cities"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$[0].cityId",is("MUM")))
		.andExpect(jsonPath("$[0].cityName",is("Mumbai")))
		.andExpect(jsonPath("$[1].cityId",is("PUN")))
		.andExpect(jsonPath("$[1].cityName",is("Pune")));
	
	}
	
	@Test
	@DisplayName("Test Update City")
	public void testUpdateCity() throws Exception{
		
		City newcity=new City("MUM","Mumbai");
		
		doReturn(newcity).when(service).updateCity(ArgumentMatchers.any());
		
	    
		 String json = new ObjectMapper().writeValueAsString(newcity);
		
		mockMvc.perform(put("/admin/cities/update")
				// Validate Status should be 200 ok and json response recived
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(json).accept(MediaType.APPLICATION_JSON))
				
				//Validate Response body
				
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.cityId",is("MUM")))
				.andExpect(jsonPath("$.cityName",is("Mumbai")));
	}
	
	@Test
	@DisplayName("Test Add list of cites")
	public void testAddAllCity() throws Exception{
		

		//Prepare Mock Product
		City city1=new City("MUM","Mumbai");
		City city2=new City("PUN","Pune");
	
		List<City> cities = new ArrayList<>();
		cities.add(city1);
		cities.add(city2);
		
		
		doReturn(cities).when(service).saveall(ArgumentMatchers.any());
		
		String json = new ObjectMapper().writeValueAsString(cities);
		
		mockMvc.perform(post("/admin/cities/bulk")
				// Validate Status should be 200 ok and json response recived
				
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(cities)))
				
				//Validate Response body
				
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().json(json));

	}
	
	@Test
	@DisplayName("Test Find by city id")
	
	public void testGetCityById() throws Exception {
		
		//Prepare Mock Product
		City city=new City("MUM","Mumbai");

		
		doReturn(Optional.of(city)).when(repository).findBycityId(city.getCityId());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/cities/findCity/id:{id}",city.getCityId()))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.cityId",is("MUM")))
		.andExpect(jsonPath("$.cityName",is("Mumbai")));
	
	}
	
	@Test
	@DisplayName("Test Find by city Name")
	
	public void testGetCityByName() throws Exception {
		
		//Prepare Mock Product
		City city=new City("MUM","Mumbai");

		
		doReturn(Optional.of(city)).when(repository).findBycityName(city.getCityName());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/cities/findCity/city:{name}",city.getCityName()))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.cityId",is("MUM")))
		.andExpect(jsonPath("$.cityName",is("Mumbai")));
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
