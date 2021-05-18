package com.myapp.spring.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.myapp.spring.model.Airlines;
import com.myapp.spring.repository.AirlineRepository;

@Service
public class AirlineService {

	@Autowired
	private AirlineRepository repository;
	
	public List<Airlines> saveall(List<Airlines> airline) {
		
		return repository.saveAll(airline);
	}
	
	public Airlines updateAirline(Airlines airline) {
		
		String id = airline.getAirlineCode();
		
		Airlines oldAirline = repository.findByAirlineCode(id).get();
		
		BeanUtils.copyProperties(airline, oldAirline);
		
		return repository.save(oldAirline);
	}
	
	
}
