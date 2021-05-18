package com.myapp.spring.web.api;

	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.model.User;
import com.myapp.spring.repository.UserRepository;

	@RestController
	@RequestMapping("/HappyTrip")
	
	public class UserAPI {
	 
	    @Autowired
	    private UserRepository userRepo;
	 // @GetMapping("register")
	  //public ResponseEntity<List<User>> Registartion(){
		  //return new  ResponseEntity<List<User>>(userRepo.findAll(),HttpStatus.OK);
	 // }
	  
	    @PostMapping("/register")
	    
	    	public ResponseEntity<User> saveNewUser(@RequestBody User user){
	    		
	    		return new ResponseEntity<User>(userRepo.save(user),HttpStatus.CREATED);
	    		}
	       
	    }
	
