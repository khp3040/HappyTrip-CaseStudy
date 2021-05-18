package com.myapp.spring.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Arrays;
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
import com.myapp.spring.model.Search;
import com.myapp.spring.repository.SearchRepository;


	@SpringBootTest

	@AutoConfigureMockMvc
	public class SearchIntegrationTest {
	
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

	@DisplayName("Test find flight by Registered User With FromCity and ToCity and Date")
	public void testfindByRegisteredUserFromCityAndToCityAndDateOrderByCostAsc() throws Exception {

	String fromCity ="Chennai";
	String toCity ="Mumbai";
	Date date=Date.valueOf("2021-01-02");

	mockMvc.perform(MockMvcRequestBuilders.get("/user/search/flight/{fromCity}/{toCity}/{date}",fromCity ,toCity,date))
	
	.andExpect(status().isOk())
	.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
	
	.andExpect(jsonPath("$[0].flightNo" , is(5)))
	.andExpect(jsonPath("$[0].airlineName" , is("Deccan-D1")))
	.andExpect(jsonPath("$[0].fromCity" , is("Chennai")))
	.andExpect(jsonPath("$[0].toCity" , is("Mumbai")))
	.andExpect(jsonPath("$[0].date" , is("2021-01-02")))
	.andExpect(jsonPath("$[0].depTime" , is("08:00:00")))
	.andExpect(jsonPath("$[0].arrTime" , is("09:00:00")))
	.andExpect(jsonPath("$[0].cost" , is(6900.00)))
	.andExpect(jsonPath("$[0].avlSeats" , is(60)));

	}
	
	@Test

	@DisplayName("Test find flight by Un-Registered User With FromCity and ToCity and Date")
	public void testfindByUnregisteredUserFromCityAndToCityAndDateOrderByCostAsc() throws Exception {

	String fromCity ="Chennai";
	String toCity ="Mumbai";
	Date date=Date.valueOf("2021-01-02");

	mockMvc.perform(MockMvcRequestBuilders.get("/search/flight/{fromCity}/{toCity}/{date}",fromCity ,toCity,date))
	
	.andExpect(status().isOk())
	.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
	
	.andExpect(jsonPath("$[0].flightNo" , is(5)))
	.andExpect(jsonPath("$[0].airlineName" , is("Deccan-D1")))
	.andExpect(jsonPath("$[0].fromCity" , is("Chennai")))
	.andExpect(jsonPath("$[0].toCity" , is("Mumbai")))
	.andExpect(jsonPath("$[0].date" , is("2021-01-02")))
	.andExpect(jsonPath("$[0].depTime" , is("08:00:00")))
	.andExpect(jsonPath("$[0].arrTime" , is("09:00:00")))
	.andExpect(jsonPath("$[0].cost" , is(6900.00)))
	.andExpect(jsonPath("$[0].avlSeats" , is(60)));

	}
}
