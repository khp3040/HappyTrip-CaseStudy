package com.myapp.spring.web.api;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.User;
import com.myapp.spring.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc

public class UserAPITest {

	@MockBean
	private UserRepository repository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("Test Add New User")
	public void testAddNewUsers() throws Exception {
		
		//Prepare Mock Product
		Long id = Long.valueOf(1);
		User newuser =new User(1,"ravikumar@gmail.com","ravi2021","ravi","kumar");
		
		User mockuser =new User(1,"ravikumar@gmail.com","ravi2021","ravi","kumar");
		//mockuser.setEmail("ravikumar@gmail.com");
		
		// Prepare Mock Service Method
		
		doReturn(mockuser).when(repository).save(ArgumentMatchers.any());
		
		// Perform GET Request
		
		mockMvc.perform(post("/HappyTrip/register")
		// Validate Status should be 200 ok and json response received
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newuser)))
		
		//Validate Response body
		
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.id",is(1)))
		.andExpect(jsonPath("$.email",is("ravikumar@gmail.com")))
		.andExpect(jsonPath("$.password",is("ravi2021")))
		.andExpect(jsonPath("$.firstName",is("ravi")))
		.andExpect(jsonPath("$.lastName",is("kumar")));
		
	}
	}
