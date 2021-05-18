package com.myapp.spring.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

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

import com.myapp.spring.model.Profile;
import com.myapp.spring.repository.ProfileRepository;


@SpringBootTest
public class ProfileRepositoryTest {

	@Autowired
	private ProfileRepository repository;
	
	private static File DATA_JSON= Paths.get("src","test","resources","profile.json").toFile();
	
	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException {
	
	Profile profiles[]=new ObjectMapper().readValue(DATA_JSON,Profile[].class);
	
	//save each product to database
	Arrays.stream(profiles).forEach(repository::save);
	}
	
	@Test

	@DisplayName("Test profile added sucessfully")
	public void testProfileaddedSucessfully() {
		// given a mock product
		Profile profile = new Profile(2,"Antra V","Female","7Nov","antra598@gmail.com","Nfl1guna","madhyapradesh","GUNA","INDIA",123333,473111);
		profile.setFullName("Antra V"); // 28 --> check with sqldatabase command  "select * from hibernate_sequence;"
		
		Profile updatedProfile=repository.save(profile);
		
		
		Assertions.assertEquals(profile.getFullName(),updatedProfile.getFullName());
	}
		
	}

