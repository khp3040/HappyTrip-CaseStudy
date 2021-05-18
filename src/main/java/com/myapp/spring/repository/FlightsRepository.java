package com.myapp.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.myapp.spring.model.Flights;

@Repository
public interface FlightsRepository extends JpaRepository<Flights, Integer>{
	
	Optional <Flights> findByFlightName(String flightName);
	

}
