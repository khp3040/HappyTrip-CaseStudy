package com.myapp.spring.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.sql.Date;
import java.sql.Time;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.Search;

@SpringBootTest
public class SearchRepositoryTest {

	@Autowired
	private SearchRepository repository;
	
	private static File DATA_JSON= Paths.get("src","test","resources","search.json").toFile();
	
	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException {
	
	Search flight []=new ObjectMapper().readValue(DATA_JSON,Search[].class);
	
	Arrays.stream(flight).forEach(repository::save);
	}

@AfterEach
public void cleanUp() {
	repository.deleteAll();
}

@Test

@DisplayName("Test find by FromCity and ToCity and Date")
public void testfindByFromCityAndToCityAndDateOrderByCostAsc() {
	Date date = Date.valueOf("2021-01-01");
	Time depTime = Time.valueOf("12:00:00");
	Time arrTime = Time.valueOf("01:00:00");
	Search flight = new Search(1,"AirIndia","Delhi","Mumbai",date,depTime,arrTime,5000.00,50);
	flight.getFromCity();
	flight.getToCity();
	flight.getDate();
	Search savedFlight=repository.save(flight);
	
	Assertions.assertNotNull(savedFlight.getFromCity(), "Flight should have from city");
	
	Assertions.assertNotNull(savedFlight.getToCity(), "Flight should have to city");
	
	Assertions.assertEquals(flight.getFromCity(),savedFlight.getFromCity());
	
	Assertions.assertEquals(flight.getToCity(),savedFlight.getToCity());
	
	Assertions.assertEquals(flight.getDate(),savedFlight.getDate());
}
}

