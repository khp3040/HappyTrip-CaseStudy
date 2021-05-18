package com.myapp.spring.web.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.myapp.spring.model.Profile;
import com.myapp.spring.repository.ProfileRepository;


@RestController
@RequestMapping("/user")
public class ProfileAPI{


//Dependency Injection
	@Autowired
	private ProfileRepository repository;
	
	
	
	
	@GetMapping("profile/view/{email}")
	
	   public ResponseEntity<Profile> viewProfile(
		  @PathVariable String email){
			
			return new ResponseEntity<Profile>(repository.findByEmail(email).get(),HttpStatus.OK);
			
			}
		

	
	
	
	
@PostMapping("profile/add")
    
	public ResponseEntity<Profile> addNewProfile(@RequestBody Profile profile){
		
		return new ResponseEntity<Profile>(repository.save(profile),HttpStatus.CREATED);
		}
   
}
