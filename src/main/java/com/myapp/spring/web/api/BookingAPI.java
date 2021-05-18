package com.myapp.spring.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.model.Booking;
import com.myapp.spring.model.Search;
import com.myapp.spring.repository.BookingRepository;
import com.myapp.spring.repository.SearchRepository;
import com.myapp.spring.service.BookingService;


	@RestController
	@RequestMapping("/booking")
	public class BookingAPI {
		
		//Dependency Injection
		@Autowired
		private SearchRepository repository;
		
		@Autowired
		private BookingRepository brepository;
		
		@Autowired
		private BookingService service;
		
		//@GetMapping("/{id}")
	//	public ResponseEntity<Product> findById(@PathVariable("id") Integer id ){
		
		//return new ResponseEntity<Product>(repository.findById(id).get(),HttpStatus.OK);
		//}
		@PostMapping
		public ResponseEntity<Booking> booking(@RequestBody Booking BookingInfo ){
			
			return new ResponseEntity<Booking>(service.BookingCheck(BookingInfo),HttpStatus.OK);
		}
		
	
	     
	}
		
		
