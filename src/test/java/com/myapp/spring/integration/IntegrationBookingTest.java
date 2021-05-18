package com.myapp.spring.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.Booking;
import com.myapp.spring.model.Search;
import com.myapp.spring.repository.BookingRepository;
import com.myapp.spring.repository.SearchRepository;
import com.myapp.spring.service.BookingService;


@SpringBootTest
@AutoConfigureMockMvc

public class IntegrationBookingTest {
	
	@Autowired
	private BookingRepository brepository;
	
	@Autowired
	private BookingService services;
	
	@Autowired
	private SearchRepository repository;
	
	@Autowired
	private MockMvc mockMvc;
	private static File DATA_JSON= Paths.get("src","test","resources","search.json").toFile();
	
	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException {
	
	Search flights[]=new ObjectMapper().readValue(DATA_JSON,Search[].class);
	
	Arrays.stream(flights).forEach(repository::save);
	}

	@AfterEach
	public void cleanUp() {
	repository.deleteAll();
}

	

	@Test
	@DisplayName("Booking Done")
	public void testBookingDone() throws Exception {
		
		//Prepare Mock Product
		java.sql.Date date = java.sql.Date.valueOf("2021-01-01");
		Booking newuserbooking = new Booking(4,"vibhor", "21st june","Delhi","Mumbai", date, 4, 4);
		
		
		String json = new ObjectMapper().writeValueAsString(newuserbooking);
				
				mockMvc.perform(post("/booking")
				// Validate Status should be 200 ok and json response recived
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(new ObjectMapper().writeValueAsString(newuserbooking)))
						
						//Validate Response body
						
						.andExpect(status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(jsonPath("$.name",is("vibhor")))
						.andExpect(jsonPath("$.dateOfBirth",is("21st june")))
						.andExpect(jsonPath("$.fromcity",is("Delhi")))
						.andExpect(jsonPath("$.tocity",is("Mumbai")));
	}	
	

	@Test
	@DisplayName("Booking not done")
	public void testBookingNotDone() throws Exception {
		
		//Prepare Mock Product
		java.sql.Date date = java.sql.Date.valueOf("2021-01-02");
		Booking newuserbooking = new Booking(4,"vibhor", "21st june","Mumbai","Delhi", date, 4, 5);
				
				
				mockMvc.perform(post("/booking")
				// Validate Status should be 200 ok and json response recived
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(new ObjectMapper().writeValueAsString(newuserbooking)))
						
						//Validate Response body
						
						.andExpect(status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(jsonPath("$.name",is("")));
	}	
}