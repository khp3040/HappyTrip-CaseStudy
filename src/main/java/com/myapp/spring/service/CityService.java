package com.myapp.spring.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.myapp.spring.model.City;
import com.myapp.spring.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	public List<City> saveall(List<City> city) {
		
		return repository.saveAll(city);
	}
	
	public City updateCity(City city) {
		
		String id = city.getCityId();
		
		City oldCity = repository.findBycityId(id).get();
		
		BeanUtils.copyProperties(city, oldCity);
		
		return repository.save(oldCity);
	}
	
	
}
