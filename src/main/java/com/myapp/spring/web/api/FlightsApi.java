package com.myapp.spring.web.api; 

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.model.Flights;
import com.myapp.spring.repository.FlightsRepository;
import com.myapp.spring.service.FlightService;

@RestController
@RequestMapping("/admin/flights")
public class FlightsApi {

	//Dependency Injection
	@Autowired
	private FlightsRepository repository;
	
	@Autowired
	private FlightService service;


	//Admin is able to view all flights
	//http://localhost:8888/admin/airlines/findAll
	@GetMapping
	public ResponseEntity<List<Flights>> findAllFlights(){
		return new ResponseEntity<List<Flights>>(repository.findAll(),HttpStatus.OK);
		
	}
	
	//Admin should be able to add a flight
	//http://localhost:8888/admin/airlines/add
	@PostMapping
	public ResponseEntity<Flights> addNewFlight(@RequestBody Flights flight){
		return new ResponseEntity<Flights>(repository.save(flight),HttpStatus.CREATED);
		
	}
	
	
	//Admin should be able to update a flight
	//http://localhost:8888/admin/airlines/update/{flight_name}
	@PutMapping("update")
	public ResponseEntity<Flights> updateFlights(@RequestBody Flights flight)
	{
		
		return new ResponseEntity<Flights>(service.updateFlight(flight),HttpStatus.CREATED);
		
	
			
	}

}
