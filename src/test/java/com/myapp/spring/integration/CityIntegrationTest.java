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
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.City;
import com.myapp.spring.repository.CityRepository;
import com.myapp.spring.service.CityService;

@SpringBootTest
@AutoConfigureMockMvc

public class CityIntegrationTest {

	@Autowired
	private CityRepository repository;
	
	@Autowired
	private CityService service;
	
	@Autowired
	private MockMvc mockMvc;
	
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
	@DisplayName("Get City by id")
	public void testGetCityByID() throws Exception{
		String id ="MUM";
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/cities/findCity/id:{id}",id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.cityId",is("MUM")))
		.andExpect(jsonPath("$.cityName",is("Mumbai")));
		
		
		
	}
	

	@Test
	@DisplayName("Get City by name")
	public void testGetCityByName() throws Exception{
		String name ="Mumbai";
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/cities/findCity/city:{name}",name))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.cityId",is("MUM")))
		.andExpect(jsonPath("$.cityName",is("Mumbai")));

	}
	
	@Test
	@DisplayName("Test view all city information")
	public void testGetAllProductsById() throws Exception {
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/cities"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$[0].cityId",is("BAN")))
		.andExpect(jsonPath("$[0].cityName",is("Bangalore")))
		.andExpect(jsonPath("$[1].cityId",is("MUM")))
		.andExpect(jsonPath("$[1].cityName",is("Mumbai")))
		.andExpect(jsonPath("$[2].cityId",is("THA")))
		.andExpect(jsonPath("$[2].cityName",is("Thane")));

		
	}
	
	@Test
	@DisplayName("Test Add City")
	public void testAddNewCity() throws Exception{
		
		City newcity=new City("PUN","Pune");
		
		mockMvc.perform(post("/admin/cities")
				// Validate Status should be 200 ok and json response recived
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(newcity)))
				
				//Validate Response body
				
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.cityId",is("PUN")))
				.andExpect(jsonPath("$.cityName",is("Pune")));
	}
		
	@Test
	@DisplayName("Test Add list of cites")
	public void testAddAllCity() throws Exception{
		

		//Prepare Mock Product
		City city1=new City("PUN","Pune");
		City city2=new City("CHN","Chennai");
	
		List<City> cities = new ArrayList<>();
		cities.add(city1);
		cities.add(city2);
		
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
	@DisplayName("Test Update City")
	public void testUpdateCity() throws Exception{
		
		City newcity=new City("MUM","Mumbai");
		
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
}
