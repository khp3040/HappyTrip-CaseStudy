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

	import org.junit.jupiter.api.DisplayName;
	import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.boot.test.mock.mockito.MockBean;
	import org.springframework.http.MediaType;
	import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.myapp.spring.model.Profile;
import com.myapp.spring.repository.ProfileRepository;
	
	@SpringBootTest
	@AutoConfigureMockMvc

	public class ProfileAPITest {

		@MockBean
		private ProfileRepository repository;
	
		@Autowired
		private MockMvc mockMvc;
		
		
		@Test
		@DisplayName("Test View by Email - GET user/profile/view/{email}")
		public void testGetProfileByEmail() throws Exception {
			
			//Prepare Mock Product
			Profile profile =new Profile(6,"Aashi V","Female","9Nov","aashi598@gmail.com","NFL guna","madhya pradesh","GUNA","INDIA",123333,473111);
			
			//profile.setEmail();
			String Email="aashi598@gmail.com";
			// Prepare Mock Service Method
			
			doReturn(Optional.of(profile)).when(repository).findByEmail(Email);
			
			// Perform GET Request
			
			mockMvc.perform(MockMvcRequestBuilders.get("/user/profile/view/{email}",Email))
			// Validate Status should be 200 ok and json response recived
			
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			
			//Validate Response body
			
			.andExpect(jsonPath("$.id",is(6)))
			.andExpect(jsonPath("$.fullName",is("Aashi V")))
			.andExpect(jsonPath("$.gender",is("Female")))
			.andExpect(jsonPath("$.dob",is("9Nov")))
			.andExpect(jsonPath("$.email",is("aashi598@gmail.com")))
			.andExpect(jsonPath("$.address",is("NFL guna")))
			.andExpect(jsonPath("$.state",is("madhya pradesh")))
			.andExpect(jsonPath("$.country",is("INDIA")))
			.andExpect(jsonPath("$.mobileNumber",is(123333)))
			.andExpect(jsonPath("$.pincode",is(473111)));
		}
		
		
		@Test
		@DisplayName("Test Add New Profile")
		public void testAddNewProfiles() throws Exception {
			
			//Prepare Mock Product
			Profile newprofile =new Profile(6,"Aashi V","Female","9Nov","aashi598@gmail.com","NFL guna","madhya pradesh","GUNA","INDIA",123333,473111);
			
			Profile mockprofile =new Profile(6,"Aashi V","Female","9Nov","aashi598@gmail.com","NFL guna","madhya pradesh","GUNA","INDIA",123333,473111);
			
			//mockprofile.setProfileid(1);
			
			// Prepare Mock Service Method
			
			doReturn(mockprofile).when(repository).save(ArgumentMatchers.any());
			
			// Perform GET Request
			
			mockMvc.perform(post("/user/profile/add")
			// Validate Status should be 200 ok and json response received
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(new ObjectMapper().writeValueAsString(newprofile)))
			
			//Validate Response body
			
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.id",is(6)))
			.andExpect(jsonPath("$.fullName",is("Aashi V")))
			.andExpect(jsonPath("$.gender",is("Female")))
			.andExpect(jsonPath("$.dob",is("9Nov")))
			.andExpect(jsonPath("$.email",is("aashi598@gmail.com")))
			.andExpect(jsonPath("$.address",is("NFL guna")))
			.andExpect(jsonPath("$.state",is("madhya pradesh")))
			.andExpect(jsonPath("$.country",is("INDIA")))
			.andExpect(jsonPath("$.mobileNumber",is(123333)))
			.andExpect(jsonPath("$.pincode",is(473111)));
			     
		}


		
		

}
