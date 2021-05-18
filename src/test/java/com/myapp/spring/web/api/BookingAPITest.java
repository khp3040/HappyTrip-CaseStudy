package com.myapp.spring.web.api;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Time;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.myapp.spring.model.Booking;
import com.myapp.spring.model.Search;
import com.myapp.spring.repository.BookingRepository;
import com.myapp.spring.repository.SearchRepository;
import com.myapp.spring.service.BookingService;


@SpringBootTest
@AutoConfigureMockMvc

public class BookingAPITest {

	@MockBean
	private BookingRepository brepository;
	
	@MockBean
	private BookingService services;
	
	@MockBean
	private SearchRepository srepository;
	
	@Autowired
	private MockMvc mockMvc;

	

	@Test
	@DisplayName("Booking Validation")
	public void testBookingValidation() throws Exception {
		
		//Prepare Mock Product
		java.sql.Date date = java.sql.Date.valueOf("2021-01-05");
		Booking newuserbooking = new Booking(3,"vibhor", "21st june", "DELHI", "MUMBAI", date, 4, 6);
				
		java.sql.Time arrtime = Time.valueOf("12:00:00");		
		Search searchuserbooking = new Search(6,"jetairways","DELHI", "MUMBAI",date,arrtime,arrtime,4000.00,50);
				
				//mockUser.setLogin();
				
				// Prepare Mock Service Method
				
				
				doReturn(newuserbooking).when(services).BookingCheck(ArgumentMatchers.any());
				
				// Perform GET Request
				
				mockMvc.perform(post("/booking")
				// Validate Status should be 200 ok and json response recived
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(newuserbooking)))
				
				//Validate Response body
				
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.name",is("vibhor")));
	}	
	
}